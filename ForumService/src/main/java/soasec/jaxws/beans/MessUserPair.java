package soasec.jaxws.beans;

public class MessUserPair {
	
	public MessUserPair(Message m, User u) {
		super();
		this.u = u;
		this.m = m;
	}
	
	private User u;
	private Message m;

	
	public User getUser() {
		return u;
	}
	public Message getMessage() {
		return m;
	}

}
