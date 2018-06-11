package ro.edu.ubb.service;

import java.util.List;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.MessageDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.Message;

/**
 * Service for message.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class MessageService {

	private MessageDAO messageDAO;
	private DAOFactory daoFactory;
	
	public MessageService() {
		daoFactory = DAOFactory.getInstance();
		messageDAO = daoFactory.getMessageDAO();
	}
	
	public List<Message> getAllMessages(String username) {
		try {
			return messageDAO.getAllMessages(username);
		} catch (DAOException e) {
			throw new ServiceException("Getting all messages failed.");
		}
	}
	
	public Message createMessage(Message message) {
		try {
			return messageDAO.createMessage(message);
		} catch (DAOException e) {
			throw new ServiceException("Insert message failed.");
		}
	}

	public boolean deleteMessage(Message message) {
		try {
			return messageDAO.deleteMessage(message);
		} catch (DAOException e) {
			throw new ServiceException("Delete message failed.");
		}
	}
}
