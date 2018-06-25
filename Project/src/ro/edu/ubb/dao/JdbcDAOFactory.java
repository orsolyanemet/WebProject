package ro.edu.ubb.dao;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.MessageDAO;
import ro.edu.ubb.common.dao.ProgramDAO;
import ro.edu.ubb.common.dao.ProgramTypeDAO;
import ro.edu.ubb.common.dao.ReportDAO;
import ro.edu.ubb.common.dao.TaskDAO;
import ro.edu.ubb.common.dao.UserDAO;

/**
 * Extension of DAOFactory.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class JdbcDAOFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		return new JdbcUserDAO();
	}

	@Override
	public MessageDAO getMessageDAO() {
		return new JdbcMessageDAO();
	}

	@Override
	public TaskDAO getTaskDAO() {
		return new JdbcTaskDAO();
	}

	@Override
	public ProgramDAO getProgramDAO() {
		return new JdbcProgramDAO();
	}
	
	@Override
	public ProgramTypeDAO getProgramTypeDAO() {
		return new JdbcProgramTypeDAO();
	}
	
	@Override
	public ReportDAO getReportDAO() {
		return new JdbcReportDAO();
	}

}
