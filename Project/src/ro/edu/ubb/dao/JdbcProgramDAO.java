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
			preparedStatement = connection.prepareStatement("SELECT * FROM program ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Program program = new Program();
				program.setIdProgram(resultSet.getInt("idProgram"));
				program.setNameProgram(resultSet.getString("nameProgram"));
				program.setDescriptionProgram(resultSet.getString("descriptionProgram"));
				program.setTargetDate(resultSet.getDate("targetDate"));
				program.setLocation(resultSet.getString("location"));
				program.setProgramType(ProgramType.valueOf(resultSet.getString("programType")));
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
				program.setProgramType(ProgramType.valueOf(resultSet.getString("programType")));
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
					"INSERT INTO program(idProgram, nameProgram, descriptionProgram, targetDate, location, programType) VALUES (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, program.getIdProgram());
			preparedStatement.setString(2, program.getNameProgram());
			preparedStatement.setString(3, program.getDescriptionProgram());
			preparedStatement.setDate(4, program.getTargetDate());
			preparedStatement.setString(5, program.getLocation());
			preparedStatement.setString(6, program.getProgramType().toString());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
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
