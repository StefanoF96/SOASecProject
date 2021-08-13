package soasec.jaxws.service;

import soasec.jaxws.service.ServiceStub.*;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBBean;

public class ServiceMethodsImpl {
	
	public static boolean addMessage(int message_ID, int user_ID, String message_text) throws Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
	    AddMessage add_message = (AddMessage)getForumObject(AddMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    add_message.setMessageID(message_ID);
	    add_message.setUserID(user_ID);
	    add_message.setMessaggio(message_text);
	            
	    return (stub.addMessage(add_message).get_return());
		
	}
	
	public static boolean deleteMessage(int message_ID) throws Exception {
			
			ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
		    DeleteMessage del_message = (DeleteMessage)getForumObject(DeleteMessage.class);
			
			// DONE : Fill in the addMessage18 here
		    del_message.setId(message_ID);
		    
		    return (stub.deleteMessage(del_message).get_return());
			
		}
	
	public static boolean editMssage(int message_ID, String message_text) throws Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
	    EditMessage edit_message = (EditMessage)getForumObject(EditMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    edit_message.setMessageID(message_ID);
	    edit_message.setMessage_text(message_text);

	    return (stub.editMessage(edit_message).get_return());
		
	}
	
	public static Message[] getAllMessages() throws AxisFault, RemoteException, Exception {
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
		GetAllMessages get_all_messages = (GetAllMessages)getForumObject(GetAllMessages.class);
      
	    return (stub.getAllMessages(get_all_messages).get_return());

	}

	//Create an ADBBean and provide it as the test object
    public static ADBBean getForumObject(java.lang.Class type) throws java.lang.Exception{
       return (ADBBean) type.newInstance();
    }
	
}
