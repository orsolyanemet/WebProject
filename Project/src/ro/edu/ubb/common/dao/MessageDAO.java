package ro.edu.ubb.common.dao;

import java.util.List;

import ro.edu.ubb.entity.Message;

/**
 * DAO interface for message.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public interface MessageDAO {
	List<Message> getAllMessages(String username);
	Message createMessage(Message msg);
    boolean deleteMessage(Integer idMessage);
}
