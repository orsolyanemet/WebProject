package ro.edu.ubb.common.dao;

import java.util.List;

import ro.edu.ubb.entity.User;

/**
 * DAO interface for user.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public interface UserDAO {
	List<User> getAllUsers();
	User findByUsername(String username);
	User createUser(User user);
	void updateUser(User user);
    boolean deleteUser(User user);
    boolean validateUser(User user);
}
