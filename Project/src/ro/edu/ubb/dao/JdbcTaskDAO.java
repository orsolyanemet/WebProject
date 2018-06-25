package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.edu.ubb.common.dao.TaskDAO;
import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.Task;
import ro.edu.ubb.util.ConnectionManager;

/**
 * Implementation of TaskDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcTaskDAO implements TaskDAO {

	private ConnectionManager cm;

	public JdbcTaskDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public boolean checkDeadline(Date deadline,Integer programId) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT targetDate FROM program WHERE idProgram= ?");
			preparedStatement.setInt(1, programId);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Date targetDate = resultSet.getDate(1);
			if(deadline.before(targetDate)){
				return true;
			}
			preparedStatement.close();
			resultSet.close();
			return false;
		} catch (SQLException e) {
			throw new DAOException("An error occured while checking deadline of task.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public Task createTask(Task task) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT idUser FROM user WHERE username= ?");
			preparedStatement.setString(1, task.getAssignedTo());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT idProgram FROM program WHERE nameProgram= ?");
			preparedStatement.setString(1, task.getPartOf());
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer programId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO user_program(fk_user,fk_program) SELECT * FROM(SELECT ?,?) AS tmp WHERE NOT EXISTS(SELECT fk_user,fk_program FROM user_program WHERE fk_user=? AND fk_program=?)LIMIT 1");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, programId);
			preparedStatement.setInt(3, userId);
			preparedStatement.setInt(4, programId);
			preparedStatement.execute();
			if (checkDeadline(task.getDeadline(), programId)) {
				preparedStatement = connection.prepareStatement(
						"INSERT INTO task( taskName, details, isSolved, deadline, assignedTo, partOf) VALUES (?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, task.getTaskName());
				preparedStatement.setString(2, task.getDetails());
				preparedStatement.setBoolean(3, task.getIsSolved());
				preparedStatement.setDate(4, task.getDeadline());
				preparedStatement.setInt(5, userId);
				preparedStatement.setInt(6, programId);
				preparedStatement.execute();
				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				task.setIdTask(resultSet.getInt(1));
			} else {
				throw new DAOException("Error event date can not be earlier than the task deadline.");
			}
			preparedStatement.close();
			resultSet.close();
			return task;

		} catch (SQLException e) {
			throw new DAOException("An error occured while inserting the task.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public List<Task> getAllUnresolvedTasks(String username) {
		Connection connection = cm.createConnection();
		List<Task> tasks = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idUser FROM user WHERE username=?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer idUser=resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE isSolved=0 AND assignedTo=?");
			preparedStatement.setInt(1, idUser);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setIdTask(resultSet.getInt("idTask"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setDetails(resultSet.getString("details"));
				task.setIsSolved(resultSet.getBoolean("isSolved"));
				task.setDeadline(resultSet.getDate("deadline"));
				task.setAssignedTo(username);
				PreparedStatement ps = connection.prepareStatement("SELECT nameProgram FROM program WHERE idProgram= ?");
				ps.setInt(1, resultSet.getInt("partOf"));
				ps.execute();
				ResultSet result = ps.executeQuery();
				result.next();
				String programName= result.getString(1);
				task.setPartOf(programName);
				tasks.add(task);
				ps.close();
				result.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all unresolved task from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return tasks;
	}

	@Override
	public List<Task> getAllResolvedTasks(String programName) {
		Connection connection = cm.createConnection();
		List<Task> tasks = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idProgram FROM program WHERE nameProgram= ?");
			preparedStatement.setString(1,programName);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer programId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE isSolved=1 AND partOf=? ");
			preparedStatement.setInt(1, programId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setIdTask(resultSet.getInt("idTask"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setDetails(resultSet.getString("details"));
				task.setIsSolved(resultSet.getBoolean("isSolved"));
				task.setDeadline(resultSet.getDate("deadline"));
				PreparedStatement ps = connection.prepareStatement("SELECT username FROM user WHERE idUser= ?");
				ps.setInt(1, resultSet.getInt("assignedTo"));
				ps.execute();
				ResultSet result = ps.executeQuery();
				result.next();
				String username = result.getString(1);
				task.setAssignedTo(username);
				task.setPartOf(programName);
				tasks.add(task);
				ps.close();
				result.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all resolved task from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return tasks;
	}

	@Override
	public List<Task> getAllUnresolvedTasksForAnEvent(String programName) {
		Connection connection = cm.createConnection();
		List<Task> tasks = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idProgram FROM program WHERE nameProgram= ?");
			preparedStatement.setString(1,programName);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer programId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE isSolved=0 AND partOf=? ");
			preparedStatement.setInt(1, programId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setIdTask(resultSet.getInt("idTask"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setDetails(resultSet.getString("details"));
				task.setIsSolved(resultSet.getBoolean("isSolved"));
				task.setDeadline(resultSet.getDate("deadline"));
				PreparedStatement ps = connection.prepareStatement("SELECT username FROM user WHERE idUser= ?");
				ps.setInt(1, resultSet.getInt("assignedTo"));
				ps.execute();
				ResultSet result = ps.executeQuery();
				result.next();
				String username = result.getString(1);
				task.setAssignedTo(username);
				task.setPartOf(programName);
				tasks.add(task);
				ps.close();
				result.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all unresolved task for an event from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return tasks;
	}
	
	@Override
	public List<Task> getAllLateTasks(Program program) {
		// TODO
		return null;
	}

	@Override
	public Task getAllInformationAboutTask(Task task) {
		Connection connection = cm.createConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE taskName=? ");
			preparedStatement.setString(1, task.getTaskName());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				task.setIdTask(resultSet.getInt("idTask"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setDetails(resultSet.getString("details"));
				task.setIsSolved(resultSet.getBoolean("isSolved"));
				task.setDeadline(resultSet.getDate("deadline"));
				PreparedStatement ps = connection.prepareStatement("SELECT username FROM user WHERE idUser= ?");
				ps.setInt(1, resultSet.getInt("assignedTo"));
				ps.execute();
				ResultSet result = ps.executeQuery();
				String username = result.getString(1);
				task.setAssignedTo(username);
				ps = connection.prepareStatement("SELECT nameProgram FROM program WHERE idProgram= ?");
				ps.setInt(1, resultSet.getInt("partOf"));
				ps.execute();
				result = ps.executeQuery();
				String programName = result.getString(1);
				task.setPartOf(programName);
				ps.close();
				result.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all information about task from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return task;
	}

	@Override
	public List<Task> getAllUserTasks(String username) {
		Connection connection = cm.createConnection();
		List<Task> tasks = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idUser FROM user WHERE username= ?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE assignedTo=? ");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setIdTask(resultSet.getInt("idTask"));
				task.setTaskName(resultSet.getString("taskName"));
				task.setDetails(resultSet.getString("details"));
				task.setIsSolved(resultSet.getBoolean("isSolved"));
				task.setDeadline(resultSet.getDate("deadline"));
				task.setAssignedTo(username);
				PreparedStatement ps = connection
						.prepareStatement("SELECT nameProgram FROM program WHERE idProgram= ?");
				ps.setInt(1, resultSet.getInt("partOf"));
				ps.execute();
				ResultSet result = ps.executeQuery();
				result.next();
				String program = result.getString(1);
				task.setPartOf(program);
				tasks.add(task);
				ps.close();
				result.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all user's task from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return tasks;
	}

	@Override
	public void updateTask(Task task) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE task SET isSolved=? where taskName = ?", PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setBoolean(1, task.getIsSolved());
			preparedStatement.setString(2, task.getTaskName());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while updating task.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public List<Task> getAllOthersTasks(String username) {
		Connection connection = cm.createConnection();
		List<Task> tasks = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idUser FROM user WHERE username= ?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT fk_program FROM user_program WHERE fk_user=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PreparedStatement ps = connection.prepareStatement(
						"SELECT taskName,isSolved,assignedTo FROM task WHERE partOf= ? AND assignedTo!=?");
				ps.setInt(1, resultSet.getInt("fk_program"));
				ps.setInt(2, userId);
				ps.execute();
				ResultSet rSet = ps.executeQuery();
				while (rSet.next()) {
					Task task = new Task();
					task.setTaskName(rSet.getString("taskName"));
					task.setIsSolved(rSet.getBoolean("isSolved"));
					PreparedStatement prep = connection
							.prepareStatement("SELECT firstname,lastname FROM user WHERE idUser= ?");
					prep.setInt(1, rSet.getInt("assignedTo"));
					prep.execute();
					ResultSet result = prep.executeQuery();
					result.next();
					String userName = result.getString(1) + " " + result.getString(2);
					task.setAssignedTo(userName);
					prep = connection.prepareStatement("SELECT nameProgram FROM program WHERE idProgram= ?");
					prep.setInt(1, resultSet.getInt("fk_program"));
					prep.execute();
					result = prep.executeQuery();
					result.next();
					String programName = result.getString(1);
					task.setPartOf(programName);
					tasks.add(task);
					prep.close();
					result.close();
				}
				ps.close();
				rSet.close();
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all other users task from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return tasks;
	}

}
