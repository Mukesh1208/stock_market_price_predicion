<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.StockMarketConstantsIF"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ranking</title>

<script type="text/javascript">

validatesearch=function()
{
	var words=document.getElementById('query').value;

	if(words<=0)
	{
		alert("Please Enter a Value for Product Search");
		return false;
	}
}
</script>

</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>

<%
	AJAXResponse ajaxResponse=(AJAXResponse) request.getAttribute(StockMarketConstantsIF.Keys.OBJ);
if(null==ajaxResponse)
{
	
}
else
{
	boolean status=ajaxResponse.isStatus();
%>
<%
	if(!status)
	{
		List<Message> msg=ajaxResponse.getEbErrors();
%>
<%
		for(int i=0;i<msg.size();i++)
		{
			Message tempMsg=msg.get(i);
	%>

<div class="errMsg">
<%= tempMsg.getMsg()%>
</div>

<%		}
	
	}
}
%>


<form action="<%=request.getContextPath()%>/review/rankProducts.do"
	method="post">


<table>
	<tr>
		<td><label>Search Contents:</label></td>
		<td><input name="type" id="type" type="hidden" value="1" size="100" maxlength="100" /></td>
		<td><input name="query" id="query" type="text" size="100" maxlength="100" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Search" onclick="validatesearch()" /></td>
	</tr>
</table>
</form>
</body>
</html>