<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="soasec.jaxws.service.ServiceStub" %>
<%@ page import="soasec.jaxws.service.ServiceStub.*" %>
<%@ page import="soasec.jaxws.service.*" %>
<%@ page import="soasec.jaxws.utils.ClientUtils" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="org.apache.axis2.client.ServiceClient" %>
<%@ page import="org.apache.axis2.context.ConfigurationContext" %>
<%@ page import="org.apache.axis2.context.ConfigurationContextFactory" %>
<%@ page import="org.apache.axis2.description.PolicyInclude" %>
<%@ page import="org.apache.neethi.Policy" %>
<%@ page import="org.apache.rampart.policy.model.CryptoConfig" %>
<%@ page import="org.apache.rampart.policy.model.RampartConfig" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.nio.charset.StandardCharsets" %>

<HTML>
<HEAD>
<link rel="stylesheet" href="../css/style.css" />
<script src="../js/script.js"></script>

<%
/*
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.rampart.policy.model.CryptoConfig;
import org.apache.rampart.policy.model.RampartConfig;
*/

String new_message = request.getParameter("message");
String user = request.getParameter("user");
String password = request.getParameter("pswd");
String operation = request.getParameter("op");
int message_ID = 0;
if(request.getParameter("msg_id") != null){
	message_ID = Integer.parseInt(request.getParameter("msg_id"));
}

//debug
System.out.println("new msg = " + new_message + " - user = " + user + " - password = " + password + " - op = " + operation);


//I have to hash the password because service will compare it with an hased password in DB
//(Didn't found any better way to do this with axis2/rampart)
if (password != null){
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	password = String.format("%064x", new BigInteger(1, digest.digest(password.getBytes(StandardCharsets.UTF_8))));
}

if (user == null){
	user = "client";
	password = "client";
}

//debug
System.out.println("new msg = " + new_message + " - user = " + user + " - password = " + password );

ServiceMethodsImpl methods = new ServiceMethodsImpl(user, password);


//check what operation is requested by user
if (operation != null){
	if (operation.equals("add")){
		if (new_message != null){
			try{
				methods.addMessage(new_message);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	if (operation.equals("delete")){
		try{
			if (!methods.deleteMessage(message_ID)){
				System.out.println("no permissions to delete messages");	
				%>
				<script>alert("you don't have permissions to delete messages");</script>
				<%
			}
		} catch(Exception e){
			e.printStackTrace();
			%> 
			<script>alert("Something gone wrong, or you don't have permissions to delete messages");</script>
			<%
		}
	}
	
	if (operation.equals("edit")){
		try{
			if (!methods.editMssage(message_ID, new_message)){
				System.out.println("no permissions to edit messages");	
				%>
				<script>alert("you don't have permissions to edit messages");</script>
				<%
			}
		} catch(Exception e){
			e.printStackTrace();
			%> 
			<script>alert("Something gone wrong, or don't have permissions to edit messages");</script>
			<%
		}
	}
}

%>


<TITLE>Result</TITLE>
</HEAD>
<BODY>
	<div style = "padding:0" class="inner-main-body p-2 p-sm-3 collapse forum-content">
		<%
			MessUserPair[] getAllMessages_temp = null;
			try{
				getAllMessages_temp = methods.getAllMessages();
			
				if(getAllMessages_temp == null){
					%>
					Still no messages on this forum board!
					</br>no messages
					<%
				}else{
				        String list_of_msg_res = null;
				        try{
				        	getAllMessages_temp[0].getMessage();
				        } catch(Exception e){
				    		e.printStackTrace();
				    	}
				        %>
				        		<% for(int i = 0; i < getAllMessages_temp.length; i+=1) { %>
								<div class="card mb-2">
								    <div class="card-body">
								        <div class="media forum-item">
								            <div  class="card-link">
								                <img src="https://bootdey.com/img/Content/avatar/avatar<%=
								                getAllMessages_temp[i].getUser().getProfile_img() 
								                %>.png" class="rounded-circle" width="50" alt="User" />
								                <a href="javascript:void(0)" id="username-displ" class="text-secondary"><%=getAllMessages_temp[i].getUser().getUsername() %></a>
								                <small id="user-role-displ" class="d-block text-center text-muted">
								                <%
								                	int priv_lev = getAllMessages_temp[i].getUser().getPrivilege_level();
								                	String user_role = "unknown";
								                	switch(priv_lev){
								                	case 0:
								                		user_role = "user";
								                		break;
								                	case 1:
								                		user_role = "moderator";
								                		break;
								                	case 2:
								                		user_role = "forum admin";
								                		break;
								                	}
								                %>
								                <%=user_role%>
								                </small>
								                <small class="text-muted ml-2">posted 
								                <%
								                long curr_time = new Date().getTime();
								                long post_time = getAllMessages_temp[i].getMessage().getTimeStamp();
								                String time_diff = ClientUtils.getTimeDiffasString(ClientUtils.computeTsDiff(post_time, curr_time));
								                if (time_diff.isEmpty() || time_diff.equals(""))
								                	time_diff = "0 seconds";
								                %>
								                <%=time_diff%> 
								                ago</small>
								            </div>
								            <div class="media-body ml-3">
								                
								                <div class="mt-3 font-size-sm" id="user-message-displ">
								                	<%=getAllMessages_temp[i].getMessage().getMessage() %> 
								                	<div class="message_bottom_links">
									                	<a onclick="openForm('edit',   <%=getAllMessages_temp[i].getMessage().getMessageID() %>)">edit</a>
									                	<a onclick="openForm('delete', <%=getAllMessages_temp[i].getMessage().getMessageID() %>)">delete</a>
								                	</div>
								                </div>
								            </div>
								        </div>
								    </div>
								</div> 
				        <%
				        	} //end for loop
				        %>		
				        
				        <!-- Login form popup for edit and delete messages -->
						<div class="form-popup" id="myForm">
						  <form method="post" target="forumboard" action="ForumBoard.jsp" class="form-container">
						    <h1>Login</h1>
							<textarea id="editmsg_text" style = "float: left;" rows = "5" cols = "40" name = "message" placeholder="Insert Your Message Here"></textarea>
							
						    <label for="user"><b>Username</b></label>
						    <input type="text" placeholder="Username" name="user" required>
						
						    <label for="pswd"><b>Password</b></label>
						    <input type="password" placeholder="Password" name="pswd" required>
						
							<input id="soap_op" type="hidden" name="op" value="null">
							<input id=msg_id type="hidden" name="msg_id" value="0">
						
						    <button type="submit" class="btn">Login</button>
						    <button type="button" class="btn cancel" onclick="closeForm()">Cancel</button>    
						  </form>
						</div>
				        <!-- Login form popup for edit and delete messages -->
				        
				        <%
					}//end else
				
				//end try : possible Exception caused by : getAllMessages_temp = methods.getAllMessages(); on line 80
				} catch(Exception e){
					System.err.println("Exception catched on ForumBoard, line 147");
					e.printStackTrace();
					if (e.getLocalizedMessage().contains("security processing failed")){
						System.out.println("security process failed");
						%>
						Something gone wrong during the Security verification of your user token, maybe you inserted wrong username or password
						<%
					}
					if (e.getLocalizedMessage().contains("No user")){
						System.out.println("no user inserted");
						%>
						Something gone wrong during the Security verification of your user token, maybe you inserted wrong username or password
						<%
					}
				}
		%>
		
	</div>
		
		
		
		

</BODY>
</HTML>