package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.edu.ubb.common.dao.ProgramTypeDAO;
import ro.edu.ubb.entity.ProgramTypes;
import ro.edu.ubb.util.ConnectionManager;

/**
 * Implementation of ProgramTypeDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcProgramTypeDAO implements ProgramTypeDAO {

	private ConnectionManager cm;

	public JdbcProgramTypeDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public List<ProgramTypes> getAllProgramTypes() {
		Connection connection = cm.createConnection();
		List<ProgramTypes> programTypes = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM programtype ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProgramTypes program = new ProgramTypes();
				program.setIdProgramType(resultSet.getInt("idProgramType"));
				program.setProgramTypeName(resultSet.getString("programTypeName"));
				programTypes.add(program);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all program types from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return programTypes;
	}
}