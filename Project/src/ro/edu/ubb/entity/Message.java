package ro.edu.ubb.entity;

/**
 * Entity for the Message.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class Message {

	private Integer idMessage;

	private String sendTo;

	private String mess;

	public Message() {
		super();
	}

	public Message(Integer idMessage, String sendTo, String mess) {
		super();
		this.idMessage = idMessage;
		this.sendTo = sendTo;
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

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", sendTo=" + sendTo + ", message=" + mess + "]";
	}

}
