package ro.edu.ubb.common.dao;

import java.util.List;

import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.Task;
import ro.edu.ubb.entity.User;

/**
 * DAO interface for task.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public interface TaskDAO {
	Task createTask(Task task);
	List<Task> getAllUnresolvedTasks(Program program);
	List<Task> getAllResolvedTasks(Program program);
	List<Task> getAllLateTasks(Program program);
	Task getAllInformationAboutTask(Task task);
	List<Task> getAllUserTasks(User user);
	void updateTask(Task task);
	List<Task> getAllOthersTasks(User user);
	
}

