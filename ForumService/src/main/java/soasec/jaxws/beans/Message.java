package soasec.jaxws.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 536153109964153588L;
	private int messageID;
	private String username; //user that send the message
	private String message;
	private long timestamp;
	
	public Message(int messageID, String username, String message) {
		super();
		this.messageID = messageID;
		this.username = username;
		this.message = message;
		Date date = new Date();
		this.timestamp = date.getTime();
	}
	

	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}



	public String getUserName() {
		return username;
	}


	public void setUserName(String username) {
		this.username = username;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getTimeStamp() {
		return timestamp;
	}
	
	public void setTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", userID=" + username + ", message=" + message + ", timestamp="
				+ timestamp + "]";
	}
	
}