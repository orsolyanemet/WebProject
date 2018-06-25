package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.edu.ubb.common.dao.ProgramDAO;
import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.ProgramType;
import ro.edu.ubb.util.ConnectionManager;

/**
 * Implementation of ProgramDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcProgramDAO implements ProgramDAO {

	private ConnectionManager cm;

	public JdbcProgramDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public List<Program> getAllPrograms() {
		Connection connection = cm.createConnection();
		List<Program> programs = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM program ORDER BY nameProgram");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Program program = new Program();
				program.setIdProgram(resultSet.getInt("idProgram"));
				program.setNameProgram(resultSet.getString("nameProgram"));
				program.setDescriptionProgram(resultSet.getString("descriptionProgram"));
				program.setTargetDate(resultSet.getDate("targetDate"));
				program.setLocation(resultSet.getString("location"));
				PreparedStatement prepStat=connection.prepareStatement("SELECT programTypeName FROM programtype WHERE idProgramType=? ");
				prepStat.setInt(1, resultSet.getInt("programType"));
				prepStat.execute();
				ResultSet result=prepStat.executeQuery();
				result.next();
				program.setProgramType(ProgramType.valueOfIgnoreCase(result.getString("programTypeName")));
				programs.add(program);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all programs from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return programs;
	}
	
	@Override
	public List<Program> getAllUsersPrograms(String username) {
		Connection connection = cm.createConnection();
		List<Program> programs = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT idUser FROM user WHERE username=?");
			preparedStatement.setString(1, username);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer idUser=resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT fk_program FROM user_program WHERE fk_user=?");
			preparedStatement.setInt(1, idUser);
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PreparedStatement prepStat=connection.prepareStatement("SELECT * FROM program WHERE idProgram=? ");
				prepStat.setInt(1, resultSet.getInt("fk_program"));
				prepStat.execute();
				ResultSet result=prepStat.executeQuery();
				result.next();
				Program program = new Program();
				program.setIdProgram(result.getInt("idProgram"));
				program.setNameProgram(result.getString("nameProgram"));
				program.setDescriptionProgram(result.getString("descriptionProgram"));
				program.setTargetDate(result.getDate("targetDate"));
				program.setLocation(result.getString("location"));
				prepStat=connection.prepareStatement("SELECT programTypeName FROM programtype WHERE idProgramType=? ");
				prepStat.setInt(1, result.getInt("programType"));
				prepStat.execute();
				result=prepStat.executeQuery();
				result.next();
				program.setProgramType(ProgramType.valueOfIgnoreCase(result.getString("programTypeName")));
				programs.add(program);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new DAOException("An error occured while getting all user's programs from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return programs;
	}

	@Override
	public Program findProgramByName(String programName) {
		Connection connection = cm.createConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		Program program = new Program();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM program WHERE nameProgram= ? ");
			preparedStatement.setString(1, programName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				program.setIdProgram(resultSet.getInt("idProgram"));
				program.setNameProgram(resultSet.getString("nameProgram"));
				program.setDescriptionProgram(resultSet.getString("descriptionProgram"));
				program.setTargetDate(resultSet.getDate("targetDate"));
				program.setLocation(resultSet.getString("location"));
				PreparedStatement preState=connection.prepareStatement("SELECT programTypeName FROM programtype WHERE idProgramType=?");
				preState.setInt(1,resultSet.getInt("programType"));
				preState.execute();
				ResultSet result=preState.executeQuery();
				result.next();
				String programTypeName= result.getString(1);
				program.setProgramType(ProgramType.valueOfIgnoreCase(programTypeName));
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while finding program by name.");
		} finally {
			cm.closeConnection(connection);
		}
		return program;
	}

	@Override
	public Program createProgram(Program program) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT idProgramType FROM programtype WHERE programTypeName= ?");
			preparedStatement.setString(1, program.getProgramType().toString());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer programTypeId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO program(nameProgram, descriptionProgram, targetDate, location, programType) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, program.getNameProgram());
			preparedStatement.setString(2, program.getDescriptionProgram());
			preparedStatement.setDate(3, program.getTargetDate());
			preparedStatement.setString(4, program.getLocation());
			preparedStatement.setInt(5, programTypeId);
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			program.setIdProgram(resultSet.getInt(1));
			preparedStatement.close();
			resultSet.close();
			return program;

		} catch (SQLException e) {
			throw new DAOException("An error occured while inserting a program.");
		} finally {
			cm.closeConnection(connection);
		}
	}
	
	@Override 
	public String createCheck(Program program) {
		createProgram(program);
		Program created=new Program();
		created=findProgramByName(program.getNameProgram());
		if(created!=null) {
			return "OK";
		}
		return "NULL";		
	}

	@Override
	public void updateProgram(Program program) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE user SET descriptionProgram=?, targetDate=?, location=? where nameProgram = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, program.getDescriptionProgram());
			preparedStatement.setDate(2, program.getTargetDate());
			preparedStatement.setString(3, program.getLocation());
			preparedStatement.setString(4, program.getNameProgram());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while updating program.");
		} finally {
			cm.closeConnection(connection);
		}
	}

}
