package soasec.jaxws.service;

import soasec.jaxws.service.ServiceStub.*;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.databinding.ADBBean;

public class ServiceMethodsImpl {
		
public static ServiceStub getStub() throws AxisFault {
	ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF" , "C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\\\main\\webapp\\WEB-INF\\conf\\axis2.xml");
	ServiceStub stub = new ServiceStub(ctx);//the default implementation should point to the right endpoint
	ServiceClient sc = stub._getServiceClient();
	sc.engageModule("rampart");
	return stub;
}
	
public static boolean addUser(int userID, String username, int priv_level) throws Exception {

		ServiceStub stub = getStub();
	    AddUser add_user = (AddUser)getForumObject(AddUser.class);
		
		// DONE : Fill in the addMessage18 here
	    add_user.setUsername(username);
	    add_user.setUserID(userID);
	    add_user.setPriv_level(priv_level);
	    
	    return (stub.addUser(add_user).get_return());
		
	}

	
	public static boolean addMessage(String user_ID, String message_text) throws Exception {
		
		ServiceStub stub = getStub();
	    AddMessage add_message = (AddMessage)getForumObject(AddMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    add_message.setUsername(user_ID);
	    add_message.setMessaggio(message_text);
	            
	    return (stub.addMessage(add_message).get_return());
		
	}
	
	public static boolean deleteMessage(int message_ID) throws Exception {
			
		ServiceStub stub = getStub();
		DeleteMessage del_message = (DeleteMessage)getForumObject(DeleteMessage.class);
			
			// DONE : Fill in the addMessage18 here
		    del_message.setId(message_ID);
		    
		    return (stub.deleteMessage(del_message).get_return());
			
		}
	
	public static boolean editMssage(int message_ID, String message_text) throws Exception {
		
		ServiceStub stub = getStub();
		EditMessage edit_message = (EditMessage)getForumObject(EditMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    edit_message.setMessageID(message_ID);
	    edit_message.setMessage_text(message_text);

	    return (stub.editMessage(edit_message).get_return());
		
	}
	
	public static MessUserPair[] getAllMessages() throws AxisFault, RemoteException, Exception {
		
		ServiceStub stub = getStub();
		GetAllMessages get_all_messages = (GetAllMessages)getForumObject(GetAllMessages.class);
      
	    return (stub.getAllMessages(get_all_messages).get_return());

	}

	//Create an ADBBean and provide it as the test object
    public static ADBBean getForumObject(java.lang.Class type) throws java.lang.Exception{
       return (ADBBean) type.newInstance();
    }
	
}
