<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="soasec.jaxws.service.ServiceStub" %>
<%@ page import="soasec.jaxws.service.ServiceStub.*" %>
<%@ page import="soasec.jaxws.service.*" %>
<%@ page import="soasec.jaxws.utils.ClientUtils" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link rel="stylesheet" href="../css/style.css" />

<%@ page import="org.apache.axis2.client.ServiceClient" %>
<%@ page import="org.apache.axis2.context.ConfigurationContext" %>
<%@ page import="org.apache.axis2.context.ConfigurationContextFactory" %>
<%@ page import="org.apache.axis2.description.PolicyInclude" %>
<%@ page import="org.apache.neethi.Policy" %>
<%@ page import="org.apache.rampart.policy.model.CryptoConfig" %>
<%@ page import="org.apache.rampart.policy.model.RampartConfig" %>


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
if (new_message != null)
	ServiceMethodsImpl.addMessage(user, new_message);
%>

<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
	<div style = "padding:0" class="inner-main-body p-2 p-sm-3 collapse forum-content">
		
		<%
			MessUserPair[] getAllMessages_temp = ServiceMethodsImpl.getAllMessages();
			if(getAllMessages_temp == null){
				%>
				<%=getAllMessages_temp %>
				no messages
				<%
			}else{
			        String list_of_msg_res = null;
			        getAllMessages_temp[0].getMessage();
			        %>
			        		<% for(int i = 0; i < getAllMessages_temp.length; i+=1) { %>
							<div class="card mb-2">
							    <div class="card-body">
							        <div class="media forum-item">
							            <div  class="card-link">
							                <img src="https://bootdey.com/img/Content/avatar/avatar<%
							                int rand = ((int)(1+Math.random()*7)); 
							                %><%=rand%>.png" class="rounded-circle" width="50" alt="User" />
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
							                </div>
							            </div>
							        </div>
							    </div>
							</div> 
			        <%
			        	}
			}
		%>
		
		
		

</BODY>
</HTML>