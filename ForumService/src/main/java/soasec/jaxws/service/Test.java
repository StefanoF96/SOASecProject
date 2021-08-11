package soasec.jaxws.service;

import soasec.jaxws.beans.Message;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Service s = new Service();
		Message m = new Message(1,2,"ciao");
		
		System.out.println(1);
		//System.out.println(s.addMessage(m));
		
		for(int i =0; i< s.getAllMessages().length; i++){
			System.out.println(s.getAllMessages()[i]);
		}
		
	}

}
