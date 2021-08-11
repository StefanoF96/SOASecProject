package soasec.jaxws.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import soasec.jaxws.beans.Message;

public class Service {
	
	private static Map<Integer,Message> messages = new HashMap<Integer,Message>();
	
	public boolean addMessage(int userID, int messageID, String messaggio) {
		//ho dovuto modificare, perchè non riesco ad inviare la classe Message serializata via SOAP
		Message m = new Message(messageID, userID, messaggio);
		if(messages.get(m.getMessageID()) != null) return false;
		messages.put(m.getMessageID(), m);
		return true;
	}
	
	
	public boolean deleteMessage(int id) {
		if(messages.get(id) == null) return false;
		messages.remove(id);
		return true;
	}
	
	
	public boolean editMessage(int messageID, String message_text) {
		if(messages.get(messageID) == null) return false;
		
		messages.get(messageID).setMessage(message_text);
		
		return true;
	}
	
	
	public Message[] getAllMessages() {
		Set<Integer> ids = messages.keySet();
		Message[] m = new Message[ids.size()];
		int i=0;
		for(Integer id : ids){
			m[i] = messages.get(id);
			i++;
		}
		return m;
	}

}





/*
package com.journaldev.jaxws.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.journaldev.jaxws.beans.Person;

public class PersonServiceImpl implements PersonService {

	private static Map<Integer,Person> persons = new HashMap<Integer,Person>();
	
	@Override
	public boolean addPerson(Person p) {
		if(persons.get(p.getId()) != null) return false;
		persons.put(p.getId(), p);
		return true;
	}

	@Override
	public boolean deletePerson(int id) {
		if(persons.get(id) == null) return false;
		persons.remove(id);
		return true;
	}

	@Override
	public Person getPerson(int id) {
		return persons.get(id);
	}

	@Override
	public Person[] getAllPersons() {
		Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = persons.get(id);
			i++;
		}
		return p;
	}

}
*/

