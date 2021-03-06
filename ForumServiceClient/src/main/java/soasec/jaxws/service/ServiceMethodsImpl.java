package soasec.jaxws.service;

import soasec.jaxws.service.ServiceStub.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.xml.ws.WebServiceClient;

import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.databinding.ADBBean;
import org.apache.axis2.description.MessageContextListener;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.rampart.RampartMessageData;
import org.apache.xmlbeans.xml.stream.XMLStreamException;


public class ServiceMethodsImpl {
	
	/**
     * Load policy file from classpath.
	 * @throws javax.xml.stream.XMLStreamException 
     */

	private String user;
	private String password;
	private String latestSoapResponse;
	private String request;
	private String response;
	
	public ServiceMethodsImpl() {
		super();
	}
	
	public ServiceMethodsImpl(String user, String password) {
		super();
		this.user = user.toLowerCase();
		this.password = password;
	}
	
	public void setUser(String user){
		this.user = user.toLowerCase();
	}
	
	public void setPassword(String password){
		this.password = password;
	}
		
	public ServiceStub getStub() throws AxisFault {
		
		// Prepare the client
		ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF" , "C:\\Users\\Stefa\\eclipse-works\\ForumServiceClient\\src\\main\\webapp\\WEB-INF\\conf\\axis2.xml");
		ServiceStub stub = new ServiceStub(ctx);
        
        // configure and engage Rampart
        ServiceClient client = stub._getServiceClient();
        Options options = client.getOptions();
        
        StAXOMBuilder builder = null;
		
		try {
			builder = new StAXOMBuilder("C:/Users/Stefa/eclipse-works/ForumServiceClient/src/main/webapp/WEB-INF/conf/policy.xml");
		} catch (FileNotFoundException | javax.xml.stream.XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        final Policy policy = PolicyEngine.getPolicy(builder.getDocumentElement());
	    options.setProperty(RampartMessageData.KEY_RAMPART_POLICY,policy);
	    options.setUserName(this.user);
        options.setPassword(this.password);
	    client.setOptions(options);	    
	    client.engageModule("rampart");
	    
	    //for getting soap envelops
	    stub._getServiceClient().getAxisService().addMessageContextListener(
			new MessageContextListener() {
			    public void attachServiceContextEvent(ServiceContext sc,
			        MessageContext mc) {}
			    public void attachEnvelopeEvent(MessageContext mc) {
			        try {		              	
						mc.getEnvelope().cloneOMElement().serialize(System.out);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						mc.getEnvelope().cloneOMElement().serialize(baos); 
						latestSoapResponse=baos.toString();
						//System.out.println(latestSoapResponse);
						
					} catch (javax.xml.stream.XMLStreamException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});

		return stub;
	}
	
	public boolean addUser(int userID, String username, String password, int priv_level) throws Exception {
	
			ServiceStub stub = this.getStub();
		    AddUser add_user = (AddUser)getForumObject(AddUser.class);
			// DONE : Fill in the addMessage18 here
		    add_user.setUsername(username);
		    add_user.setUserID(userID);
		    add_user.setPassword(password);
		    add_user.setPriv_level(priv_level);
		    
		    boolean res = stub.addUser(add_user).get_return();
			this.setEnvelopeReqResp(stub);

		    return (res);
	}

	
	public boolean addMessage(String message_text) throws RemoteException, Exception{
		
		ServiceStub stub = this.getStub();
	    AddMessage add_message = (AddMessage)getForumObject(AddMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    add_message.setMessaggio(message_text);
	    
	    boolean res = stub.addMessage(add_message).get_return();
		this.setEnvelopeReqResp(stub);
        
	    return (res);
		
	}
	
	public  boolean deleteMessage(int message_ID) throws RemoteException, Exception {
			
		ServiceStub stub = this.getStub();
		DeleteMessage del_message = (DeleteMessage)getForumObject(DeleteMessage.class);
			
			// DONE : Fill in the addMessage18 here
		    del_message.setId(message_ID);
		    
		    boolean res = stub.deleteMessage(del_message).get_return();
			this.setEnvelopeReqResp(stub);

		    return (res);
			
		}
	
	public boolean editMssage(int message_ID, String message_text) throws RemoteException, Exception {
		
		ServiceStub stub = this.getStub();
		EditMessage edit_message = (EditMessage)getForumObject(EditMessage.class);
		
		// DONE : Fill in the addMessage18 here
	    edit_message.setMessageID(message_ID);
	    edit_message.setMessage_text(message_text);
	    
	    boolean res = stub.editMessage(edit_message).get_return();
		this.setEnvelopeReqResp(stub);
	    
	    return (res);
		
	}
	
	public MessUserPair[] getAllMessages() throws AxisFault, RemoteException, Exception {
		
		ServiceStub stub = this.getStub();
		GetAllMessages get_all_messages = (GetAllMessages)getForumObject(GetAllMessages.class);
		
		MessUserPair [] res = stub.getAllMessages(get_all_messages).get_return();
		this.setEnvelopeReqResp(stub);
		
	    return (res);
	}
	

	public void setEnvelopeReqResp(ServiceStub stub) {
		try {
			this.request = stub._getServiceClient().getLastOperationContext().getMessageContext("Out").getEnvelope().toString();
			this.response = latestSoapResponse;
			//this.response = stub._getServiceClient().getLastOperationContext().getMessageContext("In").getEnvelope().getText();
	              
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("error in ServiceMethodsImpl, setting the current values of envelops");
			e.printStackTrace();
		}
	}
	
	//Create an ADBBean and provide it as the test object
    public static ADBBean getForumObject(java.lang.Class type) throws java.lang.Exception{
       return (ADBBean) type.newInstance();
    }

	public String getResponseEnvelope() {
		return StringEscapeUtils.escapeHtml(response);
	}

	public String getRequestEnvelope() {
		return StringEscapeUtils.escapeHtml(request);
	}
	
}
