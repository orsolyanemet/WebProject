package ro.edu.ubb.service;

import java.util.List;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.TaskDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.Task;

/**
 * Service for task.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class TaskService {

	private TaskDAO taskDAO;
	private DAOFactory daoFactory;

	public TaskService() {
		daoFactory = DAOFactory.getInstance();
		taskDAO = daoFactory.getTaskDAO();
	}

	public Task createTask(Task task) {
		try {
			return taskDAO.createTask(task);
		} catch (DAOException e) {
			throw new ServiceException("Create task failed.");
		}
	}

	public List<Task> getAllUnresolvedTasks(String username) {
		try {
			return taskDAO.getAllUnresolvedTasks(username);
		} catch (DAOException e) {
			throw new ServiceException("Getting all unresolved tasks failed.");
		}
	}

	public List<Task> getAllResolvedTasks(String programName) {
		try {
			return taskDAO.getAllResolvedTasks(programName);
		} catch (DAOException e) {
			throw new ServiceException("Getting all resolved tasks failed.");
		}
	}
	
	public List<Task> getAllUnresolvedTasksForAnEvent(String programName) {
		try {
			return taskDAO.getAllUnresolvedTasksForAnEvent(programName);
		} catch (DAOException e) {
			throw new ServiceException("Getting all unresolved tasks for an event failed.");
		}
	}

	public List<Task> getAllLateTasks(Program program) {
		try {
			return taskDAO.getAllLateTasks(program);
		} catch (DAOException e) {
			throw new ServiceException("Getting all late tasks failed.");
		}
	}

	public Task getAllInformationAboutTask(Task task) {
		try {
			return taskDAO.getAllInformationAboutTask(task);
		} catch (DAOException e) {
			throw new ServiceException("Getting all information about task failed.");
		}
	}

	public List<Task> getAllUserTasks(String username) {
		try {
			return taskDAO.getAllUserTasks(username);
		} catch (DAOException e) {
			throw new ServiceException("Getting all user's tasks failed.");
		}
	}
	
	public void updateTask(Task task) {
		try {
			 taskDAO.updateTask(task);
		} catch (DAOException e) {
			throw new ServiceException("Update task failed.");
		}
	}
	
	public List<Task> getAllOthersTasks(String username) {
		try {
			return taskDAO.getAllOthersTasks(username);
		} catch (DAOException e) {
			throw new ServiceException("Getting all other user's tasks failed.");
		}
	}
}
