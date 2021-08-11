package test.soasec.jaxws.service;

import soasec.jaxws.service.ServiceStub;
import soasec.jaxws.service.ServiceStub.*;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBBean;

public class MyServiceTest {
	
	
	public static void main(String[] args) throws AxisFault, RemoteException, Exception {
		
		addMessage(1,2,"Jello World");
		Message m = getAllMessages()[0];
		//System.out.println(m.toString());
		
		Message m1 = new Message();
		m1.setMessage("ciao");
		m1.setMessageID(0);
		m1.setUserID(0);
		
		System.out.println(m1.toString());

	}
	
	
	public static boolean addMessage(int message_ID, int user_ID, String message_text) throws Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
	    AddMessage add_message = (AddMessage)getTestObject(AddMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    add_message.setMessageID(message_ID);
	    add_message.setUserID(user_ID);
	    add_message.setMessaggio(message_text);
	            
	    return (stub.addMessage(add_message).get_return());
		
	}
	
	public static boolean deleteMessage(int message_ID) throws Exception {
			
			ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
		    DeleteMessage del_message = (DeleteMessage)getTestObject(DeleteMessage.class);
			
			// DONE : Fill in the addMessage18 here
		    del_message.setId(message_ID);
		    
		    return (stub.deleteMessage(del_message).get_return());
			
		}
	
	public static boolean editMssage(int message_ID, String message_text) throws Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
	    EditMessage edit_message = (EditMessage)getTestObject(EditMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    edit_message.setMessageID(message_ID);
	    edit_message.setMessage_text(message_text);

	    return (stub.editMessage(edit_message).get_return());
		
	}
	
	public static Message[] getAllMessages() throws AxisFault, RemoteException, Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
		GetAllMessages get_all_messages = (GetAllMessages)getTestObject(GetAllMessages.class);
      
	    return (stub.getAllMessages(get_all_messages).get_return());

	}
	
	//Create an ADBBean and provide it as the test object
    public static ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
       return (ADBBean) type.newInstance();
    }

}
