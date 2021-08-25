package soasec.jaxws.service;

import java.util.Date;

import soasec.jaxws.beans.MessUserPair;
import soasec.jaxws.beans.Message;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Service s = new Service();
		//Message m = new Message(1,2,"ciao");
		//System.out.println(1);
		System.out.println(s.addUser(1, "Ugo", 2));
		System.out.println(s.addMessage("Ugo", "ciao"));
		System.out.println(s.addMessage("Pane", "messaggio 2"));
		
		Date date;
		
		for(int i =0; i< s.getAllMessages().length; i++){
			MessUserPair m = s.getAllMessages()[i];
			System.out.println(m.getMessage().toString());
			System.out.println("timestamp: " + m.getMessage().getTimeStamp());
			System.out.println(m.getMessage().getTimeStamp());
			date = new Date(m.getMessage().getTimeStamp());
			System.out.println(date);
			
		}
		
	}

}
