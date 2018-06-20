package ro.edu.ubb.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity for the Message.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5426019659479959837L;

	private Integer idMessage;

	private String sendTo;
	
	private String subject;
	
	private Date date;

	private String mess;

	public Message() {
		super();
	}

	public Message(Integer idMessage, String sendTo, String subject, Date date, String mess) {
		super();
		this.idMessage = idMessage;
		this.sendTo = sendTo;
		this.subject = subject;
		this.date = date;
		this.mess = mess;
	}

	public Integer getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Integer idMessage) {
		this.idMessage = idMessage;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", sendTo=" + sendTo + ", subject=" + subject + ", date=" + date
				+ ", mess=" + mess + "]";
	}

}
