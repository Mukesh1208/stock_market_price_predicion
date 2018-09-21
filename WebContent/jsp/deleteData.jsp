<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>

<%@page import="com.constants.StockMarketConstantsIF"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

	<div class="errMsg"><%=tempMsg.getMsg()%></div>

	<%
		}
		
		}
	}
	%>


	<form action="<%=request.getContextPath()%>/review/deleteData.do"
		method="post">

<div>

</div>

<div></div>

<div></div>

	<div align="center">
		<table>
			<tr>
				<td><label>Remove Data:</label></td>
				<td>
				<select name="tableDataToRemove">
					<option value="review">Review</option>
					<option value="cleanreviews">Clean Review</option>
					<option value="tokens">Tokens</option>
					<option value="freq">Freq</option>
					<option value="featureVector">FeatureVector</option>
					<option value="bestFv">Best Feature Vector</option>
				</select>
				</td>

			</tr>
			<tr>
				<td><input type="submit" value="Remove Data"
					/></td>
			</tr>

		</table>
		</div>
	</form>
</body>
</html>