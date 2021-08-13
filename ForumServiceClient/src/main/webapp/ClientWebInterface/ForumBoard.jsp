<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="soasec.jaxws.service.ServiceStub" %>
<%@ page import="soasec.jaxws.service.ServiceStub.*" %>
<%@ page import="soasec.jaxws.service.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
	<div class="inner-main-body p-2 p-sm-3 collapse forum-content">
		
		<%
			Message[] getAllMessages_temp = ServiceMethodsImpl.getAllMessages();
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
							            <a href="javascript:void(0)" class="card-link">
							                <img src="https://bootdey.com/img/Content/avatar/avatar2.png" class="rounded-circle" width="50" alt="User" />
							                <small class="d-block text-center text-muted">Pro</small>
							            </a>
							            <div class="media-body ml-3">
							                <a href="javascript:void(0)" class="text-secondary"><%=getAllMessages_temp[i].getUserID()%></a>
							                <small class="text-muted ml-2">1 hour ago</small>
							                <div class="mt-3 font-size-sm">
							                	<%=getAllMessages_temp[i].getMessage()%> 
							                </div>
							            </div>
							        </div>
							    </div>
							</div>             
			        <%
			        	}
			}
		%>
		
		
		<div class="card mb-2">
		    <div class="card-body">
		        <div class="media forum-item">
		            <a href="javascript:void(0)" class="card-link">
		                <img src="https://bootdey.com/img/Content/avatar/avatar1.png" class="rounded-circle" width="50" alt="User" />
		                <small class="d-block text-center text-muted">Newbie</small>
		            </a>
		            <div class="media-body ml-3">
		                <a href="javascript:void(0)" class="text-secondary">Mokrani</a>
		                <small class="text-muted ml-2">1 hour ago</small>
		                <h5 class="mt-1">Realtime fetching data</h5>
		                <div class="mt-3 font-size-sm">
		                    <p>Hellooo :)</p>
		                    <p>
		                        I'm newbie with laravel and i want to fetch data from database in realtime for my dashboard anaytics and i found a solution with ajax but it dosen't work if any one have a simple solution it will be
		                        helpful
		                    </p>
		                    <p>Thank</p>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="card mb-2">
		    <div class="card-body">
		        <div class="media forum-item">
		            <a href="javascript:void(0)" class="card-link">
		                <img src="https://bootdey.com/img/Content/avatar/avatar2.png" class="rounded-circle" width="50" alt="User" />
		                <small class="d-block text-center text-muted">Pro</small>
		            </a>
		            <div class="media-body ml-3">
		                <a href="javascript:void(0)" class="text-secondary">drewdan</a>
		                <small class="text-muted ml-2">1 hour ago</small>
		                <div class="mt-3 font-size-sm">
		                    <p>What exactly doesn't work with your ajax calls?</p>
		                    <p>Also, WebSockets are a great solution for realtime data on a dashboard. Laravel offers this out of the box using broadcasting</p>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="card mb-2">
		    <div class="card-body">
		        <div class="media forum-item">
		            <a href="javascript:void(0)" class="card-link">
		                <img src="https://bootdey.com/img/Content/avatar/avatar2.png" class="rounded-circle" width="50" alt="User" />
		                <small class="d-block text-center text-muted">Pro</small>
		            </a>
		            <div class="media-body ml-3">
		                <a href="javascript:void(0)" class="text-secondary">drewdan</a>
		                <small class="text-muted ml-2">1 hour ago</small>
		                <div class="mt-3 font-size-sm">
		                    <p>What exactly doesn't work with your ajax calls?</p>
		                    <p>Also, WebSockets are a great solution for realtime data on a dashboard. Laravel offers this out of the box using broadcasting</p>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>

</BODY>
</HTML>