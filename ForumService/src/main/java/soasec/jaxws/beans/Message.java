package soasec.jaxws.beans;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 536153109964153588L;
	private int messageID;
	private int userID; //user that send the message
	private String message;
	
	
	public Message(int messageID, int userID, String message) {
		super();
		this.messageID = messageID;
		this.userID = userID;
		this.message = message;
	}
	

	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}



	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", userID=" + userID + ", message=" + message + "]";
	}
	
}