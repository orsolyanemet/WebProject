package ro.edu.ubb.common.dao;

import java.sql.Date;
import java.util.List;

import ro.edu.ubb.entity.Task;

/**
 * DAO interface for task.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public interface TaskDAO {
	boolean checkDeadline(Date deadline,Integer programId);
	Task createTask(Task task);
	List<Task> getAllUnresolvedTasks(String username);
	List<Task> getAllResolvedTasks(String programName);
	List<Task> getAllUnresolvedTasksForAnEvent(String programName);
	List<Task> getAllUserTasks(String username);
	Integer updateTask(Task task);
	List<Task> getAllOthersTasks(String username);
	
}

