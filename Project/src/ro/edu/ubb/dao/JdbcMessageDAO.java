package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.edu.ubb.common.dao.MessageDAO;
import ro.edu.ubb.entity.Message;
import ro.edu.ubb.util.ConnectionManager;

/**
 * Implementation of MessageDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcMessageDAO implements MessageDAO {

	private ConnectionManager cm;

	public JdbcMessageDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public List<Message> getAllMessages(String username) {
		Connection connection = cm.createConnection();
		List<Message> messages = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			PreparedStatement preparedStatement1 = connection
					.prepareStatement("SELECT idUser FROM user WHERE username=?");
			preparedStatement1.setString(1, username);
			ResultSet resultSet1 = preparedStatement1.executeQuery();
			resultSet1.next();
			Integer userId = resultSet1.getInt(1);
			preparedStatement = connection.prepareStatement("SELECT * FROM message WHERE sendTo=? ORDER BY idMessage DESC ");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setIdMessage(resultSet.getInt("idMessage"));
				message.setSendTo(username);
				message.setSubject(resultSet.getString("subject"));
				message.setDate(resultSet.getDate("date"));
				message.setMess(resultSet.getString("mess"));
				messages.add(message);
			}
			preparedStatement1.close();
			resultSet1.close();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all messages from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return messages;
	}

	@Override
	public Message createMessage(Message msg) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT idUser FROM user WHERE email=?");
			preparedStatement.setString(1, msg.getSendTo());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO message( sendTo, subject, date, mess) VALUES (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, msg.getSubject());
			preparedStatement.setDate(3, msg.getDate());
			preparedStatement.setString(4, msg.getMess());
			preparedStatement.execute();
			ResultSet rSet = preparedStatement.getGeneratedKeys();
			rSet.next();
			msg.setIdMessage(rSet.getInt(1));
			preparedStatement.close();
			resultSet.close();
			return msg;

		} catch (SQLException e) {
			throw new DAOException("An error occured while inserting the message.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public boolean deleteMessage(Integer idMessage) {
		Connection connection = cm.createConnection();
		boolean result=false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM message WHERE idMessage= ? ");
			preparedStatement.setInt(1, idMessage);
			preparedStatement.execute();
			preparedStatement.close();
			result=true;
		} catch (SQLException e) {
			throw new DAOException("An error occured while deleting a message.");
		} finally {
			cm.closeConnection(connection);
		}
		return result;
	}

}
