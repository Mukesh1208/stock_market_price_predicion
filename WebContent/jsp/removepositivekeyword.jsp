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
<title>Remove Positive Keyword</title>
<script type="text/javascript">
	validatePK = function() {
		var stopWord = document.getElementById('positivekeyword').value;

		if (stopWord <= 0) {
			alert("Please Enter a Value for Positive Keyword");
			return false;
		}
	}
</script>
</head>
<body background="<%=request.getContextPath()%>/images/background.jpg">
	<jsp:include page="/jsp/customer.jsp"></jsp:include>
	<jsp:include page="/jsp/menu.jsp"></jsp:include>
<div class="gap">

</div>
	<%
		AJAXResponse ajaxResponse = (AJAXResponse) request
		.getAttribute(StockMarketConstantsIF.Keys.OBJ);
		if (null == ajaxResponse) {

		} else {
			boolean status = ajaxResponse.isStatus();
	%>
	<%
		if (!status) {
				List<Message> msg = ajaxResponse.getEbErrors();
	%>
	<%
		for (int i = 0; i < msg.size(); i++) {
					Message tempMsg = msg.get(i);
	%>

	<div class="errMsg"><%=tempMsg.getMsg()%></div>

	<%
		}

			}
		}
	%>


	<form
		action="<%=request.getContextPath()%>/review/removePositiveKeyword.do"
		method="post">


		<table>
			<tr>
				<td><label>Enter Positive Keyword:</label></td>
				<td><input name="positivekeyword" id="positivekeyword"
					type="text" size="50" maxlength="50" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Remove Positive Keyword"
					onclick="validatePK()" /></td>
			</tr>

		</table>
	</form>
</body>
</html>