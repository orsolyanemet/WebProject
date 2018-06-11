package ro.edu.ubb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.edu.ubb.common.dao.UserDAO;
import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.entity.User;
import ro.edu.ubb.util.ConnectionManager;
import ro.edu.ubb.util.SecureData;

/**
 * Implementation of UserDAO.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcUserDAO implements UserDAO {
	
	private ConnectionManager cm;
	
	public JdbcUserDAO() {
		cm = ConnectionManager.getInstance();
	}

	@Override
	public List<User> getAllUsers() {
		Connection connection = cm.createConnection();
		List<User> users = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE roleType=\"ORGANIZER\" ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setIdUser(resultSet.getInt("idUser"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
				user.setRoleType(RoleType.valueOf(resultSet.getString("roleType")));
				users.add(user);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while getting all users from database.");
		} finally {
			cm.closeConnection(connection);
		}
		return users;
	}

	@Override
	public User findByUsername(String username) {
		Connection connection = cm.createConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		User user = new User();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username= ? ");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setIdUser(resultSet.getInt("idUser"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
				user.setRoleType(RoleType.valueOf(resultSet.getString("roleType")));
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while finding user by username.");
		} finally {
			cm.closeConnection(connection);
		}
		return user;
	}

	@Override
	public User createUser(User user) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO user(idUser, firstname, lastname, email, username, password, phoneNumber, roleType) VALUES (?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, user.getIdUser());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, SecureData.convertHexToString(SecureData.hashPassword(user.getPassword())));
			preparedStatement.setString(7, user.getPhoneNumber());
			preparedStatement.setString(8, user.getRoleType().toString());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			user.setIdUser(resultSet.getInt(1));
			preparedStatement.close();
			resultSet.close();
			return user;

		} catch (SQLException e) {
			throw new DAOException("An error occured while inserting the user.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public void updateUser(User user) {
		Connection connection = cm.createConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE user SET firstname=?, lastname=?, email=?, phoneNumber=? where username = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhoneNumber());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured while updating user.");
		} finally {
			cm.closeConnection(connection);
		}
	}

	@Override
	public boolean deleteUser(User user) {
		Connection connection = cm.createConnection();
		boolean result;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM user WHERE username = ? ");
			preparedStatement.setString(1, user.getUsername());

			ResultSet resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
			preparedStatement.close();
			resultSet.close();

		} catch (SQLException e) {

			throw new DAOException("An error occured while deleting a user.");
		} finally {
			cm.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean validateUser(User user) {
		Connection connection = cm.createConnection();
		boolean result=false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM user WHERE username = ? and password = ?");
			preparedStatement.setString(1, user.getUsername());
			//preparedStatement.setString(2, SecureData.convertHexToString(SecureData.hashPassword(user.getPassword())));
			preparedStatement.setString(2, user.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("An error occured during the validation of user.");
		} finally {
			cm.closeConnection(connection);
		}
		return result;
	}

}
