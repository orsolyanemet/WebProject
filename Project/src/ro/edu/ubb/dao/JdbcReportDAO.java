package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ro.edu.ubb.common.dao.ReportDAO;
import ro.edu.ubb.entity.Report;
import ro.edu.ubb.util.ConnectionManager;

/**
 * Implementation of ReportDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcReportDAO implements ReportDAO {

	private ConnectionManager cm;

	public JdbcReportDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public Report createReport(Report report) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT idUser FROM user WHERE username= ?");
			preparedStatement.setString(1, report.getWrittenBy());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT idTask FROM task WHERE taskName= ?");
			preparedStatement.setString(1, report.getPartOf());
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer taskId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO report( report, fk_task, reportDate, writtenBy) VALUES (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, report.getReport());
			preparedStatement.setInt(2, taskId);
			preparedStatement.setDate(3, report.getReportDate());
			preparedStatement.setInt(4, userId);
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			report.setIdReport(resultSet.getInt(1));
			preparedStatement.close();
			resultSet.close();
			return report;
		} catch (SQLException e) {
			throw new DAOException("An error occured while inserting the report.");
		} finally {
			cm.closeConnection(connection);
		}
	}

}
