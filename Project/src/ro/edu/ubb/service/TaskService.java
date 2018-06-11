package ro.edu.ubb.service;

import java.util.List;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.TaskDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.Task;
import ro.edu.ubb.entity.User;

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

	public List<Task> getAllUnresolvedTasks(Program program) {
		try {
			return taskDAO.getAllUnresolvedTasks(program);
		} catch (DAOException e) {
			throw new ServiceException("Getting all unresolved tasks failed.");
		}
	}

	public List<Task> getAllResolvedTasks(Program program) {
		try {
			return taskDAO.getAllResolvedTasks(program);
		} catch (DAOException e) {
			throw new ServiceException("Getting all resolved tasks failed.");
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

	public List<Task> getAllUserTasks(User user) {
		try {
			return taskDAO.getAllUserTasks(user);
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
	
	public List<Task> getAllOthersTasks(User user) {
		try {
			return taskDAO.getAllOthersTasks(user);
		} catch (DAOException e) {
			throw new ServiceException("Getting all other user's tasks failed.");
		}
	}
}
