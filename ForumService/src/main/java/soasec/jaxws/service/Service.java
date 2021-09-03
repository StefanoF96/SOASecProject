package soasec.jaxws.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.axis2.context.MessageContext;

import soasec.jaxws.beans.MessUserPair;
import soasec.jaxws.beans.Message;
import soasec.jaxws.beans.User;
import soasec.jaxws.db.DbConnection;

public class Service {
	
	private static Map<Integer,Message> messages = new HashMap<Integer,Message>();
	private DbConnection db = new DbConnection();
	private MessageContext contexteMessage = MessageContext.getCurrentMessageContext();
	private String username = contexteMessage.getEnvelope().getHeader()
			.getFirstChildWithName((new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security")))
			.getFirstChildWithName((new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","UsernameToken")))
			.getFirstChildWithName((new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Username"))).getText();
	
	
	
	
	public boolean addMessage(String messaggio) throws SQLException {
		//ho dovuto modificare, perchè non riesco ad inviare la classe Message serializata via SOAP
		
		//String username_contex = MessageContext.getCurrentMessageContext().getEnvelope().getHeader().getFirstChildWithName(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Username","wsse")).getText();
        //System.out.println("usernameeeeeeeee from Service = " + username_contex);
		
		System.out.println("user = = =" + username);
		
		// create new message_id
		Set<Integer> message_id_set = messages.keySet();
		int messageID = 1;
		if (!messages.isEmpty()) 
			messageID = Collections.max(message_id_set)+1;
		if(messages.get(messageID) != null) return false;
		//check if user specified exist, else return false
		String query = "SELECT username FROM users WHERE username = \"" + username + "\"";
		ResultSet result = db.executeQuery(query);
		if (!result.isBeforeFirst()) return false;
		
		Message m = new Message(messageID, username, messaggio);
		messages.put(m.getMessageID(), m);
		return true;
	}
	
	
	public boolean addUser(int userID, String username, String password, int priv_level) {
		username = username.toLowerCase();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return false;
		}
		
		
		try {
			//db query to find out if user already exists
			String query = "SELECT username FROM users WHERE username = \"" + username + "\"";
			ResultSet result = db.executeQuery(query);
			if (result.isBeforeFirst()) return false;
			
			String password_hash = String.format("%064x", new BigInteger(1, digest.digest(password.getBytes(StandardCharsets.UTF_8))));
			System.out.println(password_hash);
			//db query to insert the new user into database
			query = "INSERT INTO users(username, password, priv_level, profile_img)"
					+ " VALUES (\"" + username + "\", \"" + password_hash + "\", " + priv_level + ", " + ((int)(1+Math.random()*7)) + ");";
			db.executeUpdate(query);
			
			System.out.println("fatto");
			return true;
			
		}catch (Exception e) {
			System.err.println("Error adding new User, maybe the query is wrong, or cannot establish connection to DB");
			e.printStackTrace();
			return false;
		} 

		//if(users.get(u.getUsername()) != null || users.containsValue(u) != false) return false;
		//users.put(u.getUsername(), u);
	}
	
	
	public boolean deleteMessage(int id) {
		//retrive user privileges
		String query = "SELECT priv_level FROM users WHERE username = \"" + username + "\"";
		ResultSet result;
		try {
			result = db.executeQuery(query);
			result.next();
			int priv_level = result.getInt("priv_level");
			if(priv_level < 2) return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(messages.get(id) == null) return false;
		
		messages.remove(id);
		return true;
	}
	
	
	public boolean editMessage(int messageID, String message_text) {
		//retrive user privileges
		String query = "SELECT priv_level FROM users WHERE username = \"" + username + "\"";
		ResultSet result;
		try {
			result = db.executeQuery(query);
			result.next();
			int priv_level = result.getInt("priv_level");
			if(priv_level < 1) return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(messages.get(messageID) == null) return false;
		
		messages.get(messageID).setMessage(message_text);
		return true;
	}
	
	
	
	//return the pair (message - user that send it)
	public MessUserPair[] getAllMessages() throws SQLException {
		Set<Integer> m_ids = messages.keySet();
		MessUserPair[] result = new MessUserPair[m_ids.size()];
		
		Message m;
		User u;
		int i=0;
		for(Integer id : m_ids){
			m = messages.get(id);
			String query = "SELECT * FROM users WHERE username = \"" + m.getUserName() + "\"";
			ResultSet query_result = db.executeQuery(query);
			query_result.next();
			u = new User(i, query_result.getString("username"), query_result.getInt("priv_level"));
			u.setProfile_img(query_result.getInt("profile_img"));
			
			result[i] = new MessUserPair(m,u);
			i++;
		}
		return result;
	}

}
