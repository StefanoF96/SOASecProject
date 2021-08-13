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
        Message[] getAllMessages15mtemp = ServiceMethodsImpl.getAllMessages();
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
            java.lang.Integer userID_4idTemp = null;
        if(!userID_4id.equals("")){
         userID_4idTemp  = java.lang.Integer.valueOf(userID_4id);
        }
        String messageID_5id=  request.getParameter("messageID35");
            java.lang.Integer messageID_5idTemp = null;
        if(!messageID_5id.equals("")){
         messageID_5idTemp  = java.lang.Integer.valueOf(messageID_5id);
        }
        String messaggio_6id=  request.getParameter("messaggio37");
            java.lang.String messaggio_6idTemp = null;
        if(!messaggio_6id.equals("")){
         messaggio_6idTemp  = messaggio_6id;
        }
        Boolean addMessage30mtemp = ServiceMethodsImpl.addMessage(userID_4idTemp,messageID_5idTemp,messaggio_6idTemp);
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