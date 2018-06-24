package ro.edu.ubb.common.dao;

import ro.edu.ubb.dao.JdbcDAOFactory;

/**
 * DAO factory abstract class.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public abstract class DAOFactory {

	public static DAOFactory getInstance() {
		return new JdbcDAOFactory();
	}

	public abstract UserDAO getUserDAO();

	public abstract MessageDAO getMessageDAO();

	public abstract TaskDAO getTaskDAO();

	public abstract ProgramDAO getProgramDAO();
	
	public abstract ProgramTypeDAO getProgramTypeDAO();

}
