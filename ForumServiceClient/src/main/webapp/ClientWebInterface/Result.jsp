<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="soasec.jaxws.service.ServiceStub" %>
<%@ page import="soasec.jaxws.service.ServiceStub.*" %>
<%@ page import="soasec.jaxws.service.*" %>

<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<%-- <jsp:useBean id="sampleServicePortTypeProxyid" scope="session" class="soasec.jaxws.service.ServicePortTypeProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleServicePortTypeProxyid.setEndpoint(request.getParameter("endpoint"));
%>--%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 

case 15:
        gotMethod = true;
        MessUserPair[] getAllMessages15mtemp = ServiceMethodsImpl.getAllMessages();
		if(getAllMessages15mtemp == null){
		%>
		<%=getAllMessages15mtemp %>
		<%
		}else{
		        String tempreturnp16 = null;
		        if(getAllMessages15mtemp != null){
		        java.util.List listreturnp16= java.util.Arrays.asList(getAllMessages15mtemp);
		        tempreturnp16 = listreturnp16.toString();
		        }
		        %>
		        <%=tempreturnp16%>
		        <%
		}
		break;
case 18:
        gotMethod = true;
        String messageID_1id=  request.getParameter("messageID21");
            java.lang.Integer messageID_1idTemp = null;
        if(!messageID_1id.equals("")){
         messageID_1idTemp  = java.lang.Integer.valueOf(messageID_1id);
        }
        String message_text_2id=  request.getParameter("message_text23");
            java.lang.String message_text_2idTemp = null;
        if(!message_text_2id.equals("")){
         message_text_2idTemp  = message_text_2id;
        }
        Boolean editMessage18mtemp = ServiceMethodsImpl.editMssage(messageID_1idTemp, message_text_2idTemp);
		if(editMessage18mtemp == null){
		%>
		<%=editMessage18mtemp %>
		<%
		}else{
		        String tempResultreturnp19 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(editMessage18mtemp));
		        %>
		        <%= tempResultreturnp19 %>
		        <%
		}
		break;
case 25:
        gotMethod = true;
        String id_3id=  request.getParameter("id28");
            java.lang.Integer id_3idTemp = null;
        if(!id_3id.equals("")){
         id_3idTemp  = java.lang.Integer.valueOf(id_3id);
        }
        Boolean deleteMessage25mtemp = ServiceMethodsImpl.deleteMessage(id_3idTemp);
		if(deleteMessage25mtemp == null){
		%>
		<%=deleteMessage25mtemp %>
		<%
		}else{
		        String tempResultreturnp26 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteMessage25mtemp));
		        %>
		        <%= tempResultreturnp26 %>
		        <%
		}
		break;
case 30:
        gotMethod = true;
        String userID_4id=  request.getParameter("userID33");
            java.lang.String userID_4idTemp = null;
        if(!userID_4id.equals("")){
         userID_4idTemp  = userID_4id;
        }
        String messaggio_6id=  request.getParameter("messaggio37");
            java.lang.String messaggio_6idTemp = null;
        if(!messaggio_6id.equals("")){
         messaggio_6idTemp  = messaggio_6id;
        }
        Boolean addMessage30mtemp = ServiceMethodsImpl.addMessage(userID_4idTemp,messaggio_6idTemp);
		if(addMessage30mtemp == null){
		%>
		<%=addMessage30mtemp %>
		<%
		}else{
		        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(addMessage30mtemp));
		        %>
		        <%= tempResultreturnp31 %>
		        <%
		}
		break;
case 35:
    gotMethod = true;
    String userID_5id=  request.getParameter("id35");
     java.lang.Integer userID_5idTemp = null;
    if(!userID_5id.equals("")){
    	userID_5idTemp  = Integer.parseInt(userID_5id);
    }
    String username35=  request.getParameter("username35");
        java.lang.String username35Temp = null;
    if(!username35.equals("")){
    	username35Temp  = username35;
   	}
    String privilege35=  request.getParameter("privilege35");
    java.lang.Integer privilege35Temp = null;
    if(!userID_5id.equals("")){
    	privilege35Temp  = Integer.parseInt(privilege35);
    }
    
    Boolean addUser35mtemp = ServiceMethodsImpl.addUser(userID_5idTemp,username35Temp,privilege35Temp);
	if(addUser35mtemp == null){
	%>
	<%=addUser35mtemp %>
	<%
	}else{
	        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(addUser35mtemp));
	        %>
	        <%= tempResultreturnp31 %>
	        <%
	}
	break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>