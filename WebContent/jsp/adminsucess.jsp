<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.model.AJAXResponse"%>
<%@page import="com.constants.StockMarketConstantsIF"%>
<%@page import="java.util.List"%>


<%@page import="com.model.Message"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sucess</title>
</head>
<body>
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>

<%
	AJAXResponse ajax=(AJAXResponse)request.getAttribute(StockMarketConstantsIF.Keys.OBJ);
if(null==ajax)
{
	
}
else
{
	
	List<Message> errMsgList=ajax.getEbErrors();
	if(null==errMsgList)
	{
		
	}
	else
	{
		Message m=errMsgList.get(0);
%>
<div class="sucess">

<%=m.getMsg()%>
</div>

<% 		
		
	}
	
}
%>
</body>
</html>