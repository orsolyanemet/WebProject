package ro.edu.ubb.service;

import java.util.List;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.ProgramDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.Program;

/**
 * Service for program.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ProgramService {
	
	private ProgramDAO programDAO;
	private DAOFactory daoFactory;
	
	public ProgramService() {
		daoFactory = DAOFactory.getInstance();
		programDAO = daoFactory.getProgramDAO();
	}
	
	public List<Program> getAllPrograms() {
		try {
			return programDAO.getAllPrograms();
		} catch (DAOException e) {
			throw new ServiceException("Getting all programs failed.");
		}
	}
	
	public Program findProgramByName(String programName) {
		try {
			return programDAO.findProgramByName(programName);
		} catch (DAOException e) {
			throw new ServiceException("Finding program by name failed.");
		}
	}
	
	
	public Program createProgram(Program program) {
		try {
			return programDAO.createProgram(program);
		} catch (DAOException e) {
			throw new ServiceException("Insert program failed.");
		}
	}

	public void updateProgram(Program program) {
		try {
			programDAO.updateProgram(program);
		} catch (DAOException e) {
			throw new ServiceException("Update program failed.");
		}
	}
}
