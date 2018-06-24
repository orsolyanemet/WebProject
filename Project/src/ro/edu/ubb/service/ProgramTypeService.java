package ro.edu.ubb.service;

import java.util.List;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.ProgramTypeDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.ProgramTypes;

/**
 * Service for program type.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ProgramTypeService {
	
	private ProgramTypeDAO programTypeDAO;
	private DAOFactory daoFactory;
	
	public ProgramTypeService() {
		daoFactory = DAOFactory.getInstance();
		programTypeDAO = daoFactory.getProgramTypeDAO();
	}
	
	public List<ProgramTypes> getAllProgramTypes() {
		try {
			return programTypeDAO.getAllProgramTypes();
		} catch (DAOException e) {
			throw new ServiceException("Getting all program types failed.");
		}
	}
}
