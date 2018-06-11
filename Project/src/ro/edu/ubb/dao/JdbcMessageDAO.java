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
			preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE sendTo=? ");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setIdMessage(resultSet.getInt("idMessage"));
				message.setMess(resultSet.getString("mess"));
				message.setSendTo(resultSet.getString("sendTo"));
				messages.add(message);
			}
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
					.prepareStatement("SELECT idUser FROM user WHERE username=?");
			preparedStatement.setString(1, msg.getSendTo());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			Integer userId = resultSet.getInt(1);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO message(idMessage, mess, idUser,sendTo ) VALUES (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, msg.getIdMessage());
			preparedStatement.setString(2, msg.getMess());
			preparedStatement.setInt(3, userId);
			preparedStatement.setString(4, msg.getSendTo());
			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			msg.setIdMessage(resultSet.getInt(1));
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
	public boolean deleteMessage(Message msg) {
		Connection connection = cm.createConnection();
		boolean result;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM message WHERE sendTo = ? AND idMessage= ? ");
			preparedStatement.setString(1, msg.getSendTo());
			preparedStatement.setInt(2, msg.getIdMessage());
			ResultSet resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
			preparedStatement.close();
			resultSet.close();

		} catch (SQLException e) {

			throw new DAOException("An error occured while deleting a message.");
		} finally {
			cm.closeConnection(connection);
		}
		return result;
	}

}
