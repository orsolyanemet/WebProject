package ro.edu.ubb.common.dao;

import java.util.List;

import ro.edu.ubb.entity.Program;

/**
 * DAO interface for program.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public interface ProgramDAO {
	List<Program> getAllUsersPrograms(String username);
	List<Program> getAllPrograms();
	Program findProgramByName(String programName);
	Program createProgram(Program program);
	String createCheck(Program program);
	void updateProgram(Program program);
}
