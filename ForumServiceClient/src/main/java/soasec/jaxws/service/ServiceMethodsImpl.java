package soasec.jaxws.service;

import soasec.jaxws.service.ServiceStub.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.xml.ws.WebServiceClient;

import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.databinding.ADBBean;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.rampart.RampartMessageData;
import org.apache.xmlbeans.xml.stream.XMLStreamException;


public class ServiceMethodsImpl {
	
	/**
     * Load policy file from classpath.
	 * @throws javax.xml.stream.XMLStreamException 
     */
	private static Policy loadPolicy(String name) throws XMLStreamException {
        ClassLoader loader = WebServiceClient.class.getClassLoader();
        InputStream resource = loader.getResourceAsStream(name);
        StAXOMBuilder builder = null;
		try {
			builder = new StAXOMBuilder(resource);
		} catch (javax.xml.stream.XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return PolicyEngine.getPolicy(builder.getDocumentElement());
    }
    
		
	public static ServiceStub getStub() throws AxisFault {
		// Prepare the client
		/* OLD VERSION WITH axis2.xml file
		ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Users\\Stefa\\eclipse-works\\ForumService\\src\\main\\webapp\\WEB-INF" , "C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF\\conf\\axis2.xml");
		ServiceStub stub = new ServiceStub(ctx);//the default implementation should point to the right endpoint
		ServiceClient sc = stub._getServiceClient();
		sc.engageModule("rampart");
		stub._getServiceClient().engageModule("rampart");
		*/
	
	
	/* very basic solution °raw° without rampart 
		String username = "test";
		String password = "lasciam perd";
		
		ServiceStub stub = new ServiceStub();//the default implementation should point to the right endpoint
		ServiceClient client = stub._getServiceClient();
		SOAP11Factory factory = new SOAP11Factory();
		OMNamespace SecurityElementNamespace = factory.createOMNamespace("http://schemas.xmlsoap.org/ws/2002/04/secext", "wlm");
	
		OMElement usernameTokenEl = factory.createOMElement("UsernameToken", SecurityElementNamespace);
		OMElement usernameEl = factory.createOMElement("Username", SecurityElementNamespace);
		OMElement passwordEl = factory.createOMElement("PasswordDigest", SecurityElementNamespace);
		usernameEl.setText(username);
		passwordEl.setText(password);
		usernameTokenEl.addChild(usernameEl);
		usernameTokenEl.addChild(passwordEl);
	
		SOAPHeaderBlockImpl block = new SOAP11HeaderBlockImpl("Security", SecurityElementNamespace, factory);
		block.addChild(usernameTokenEl);
	
		client.addHeader(block);
	*/
		
		// Prepare the client
		ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF" , "C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF\\conf\\axis2.xml");
		
		ServiceStub stub = new ServiceStub(ctx);
        
        // configure and engage Rampart
        ServiceClient client = stub._getServiceClient();
        Options options = client.getOptions();
        
        /*
        try {
			options.setProperty(RampartMessageData.KEY_RAMPART_POLICY, loadPolicy("policy.xml"));
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
        */
        
        StAXOMBuilder builder = null;
		
		try {
			builder = new StAXOMBuilder("C:/Users/Stefa/eclipse-works/ForumServiceClient/src/main/webapp/WEB-INF/conf/policy.xml");
		} catch (FileNotFoundException | javax.xml.stream.XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        final Policy policy = PolicyEngine.getPolicy(builder.getDocumentElement());
	    options.setProperty(RampartMessageData.KEY_RAMPART_POLICY,policy);
	    options.setUserName("jon");
        options.setPassword("password1");
	    client.setOptions(options);
	    
	    client.engageModule("rampart");

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
