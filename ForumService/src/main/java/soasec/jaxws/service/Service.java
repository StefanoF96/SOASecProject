package soasec.jaxws.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import soasec.jaxws.beans.MessUserPair;
import soasec.jaxws.beans.Message;
import soasec.jaxws.beans.User;

public class Service {
	
	private static Map<Integer,Message> messages = new HashMap<Integer,Message>();
	private static Map<String, User> users = new HashMap<String,User>();
	
	/*
	public boolean addMessage(int messageID, String username, String messaggio) {
		//ho dovuto modificare, perchè non riesco ad inviare la classe Message serializata via SOAP
		
		if(users.get(username) == null) return false;
		Message m = new Message(messageID, username, messaggio);
		if(messages.get(m.getMessageID()) != null) return false;
		
		messages.put(m.getMessageID(), m);
		return true;
	}
	*/
	
	public boolean addMessage(String username, String messaggio) {
		//ho dovuto modificare, perchè non riesco ad inviare la classe Message serializata via SOAP
		
		if(users.get(username) == null) return false;
		// create new message_id
		Set<Integer> message_id_set = messages.keySet();
		int messageID = 1;
		if (!messages.isEmpty()) 
			messageID = Collections.max(message_id_set)+1;
		if(messages.get(messageID) != null) return false;
		
		Message m = new Message(messageID, username, messaggio);
		messages.put(m.getMessageID(), m);
		return true;
	}
	
	public boolean addUser(int userID, String username, int priv_level) {
		User u = new User(userID, username, priv_level);
		if(users.get(u.getUsername()) != null || users.containsValue(u) != false) return false;
		users.put(u.getUsername(), u);
		return true;
	}
	
	
	public boolean deleteMessage(int id) {
		if(messages.get(id) == null) return false;
		if(users.get(messages.get(id).getUserName()).getPrivilege_level() < 2) return false;
		messages.remove(id);
		return true;
	}
	
	
	public boolean editMessage(int messageID, String message_text) {
		if(messages.get(messageID) == null) return false;
		if(users.get(messages.get(messageID).getUserName()).getPrivilege_level() < 1) return false;
		messages.get(messageID).setMessage(message_text);
		
		return true;
	}
	
	//return the pair (message - user that send it)
	public MessUserPair[] getAllMessages() {
		Set<Integer> m_ids = messages.keySet();
		MessUserPair[] result = new MessUserPair[m_ids.size()];
		
		Message m;
		User u;
		int i=0;
		for(Integer id : m_ids){
			m = messages.get(id);
			u = users.get(m.getUserName());
			result[i] = new MessUserPair(m,u);
			i++;
		}
		return result;
	}

}
