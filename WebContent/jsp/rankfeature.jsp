<%@page import="com.model.PolarityOrderInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>

<%@page import="com.constants.StockMarketConstantsIF"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>
<%@page import="com.model.CompanyModel"%>
<%@page import="com.model.CompanyRankInformation"%>
<%@page import="com.model.ReviewCorrelationVO"%>




<%@page import="com.model.FeatureVO"%>
<%@page import="com.model.CompanyRankInfo"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
	<jsp:include page="/jsp/customer.jsp"></jsp:include>
	<jsp:include page="/jsp/menu.jsp"></jsp:include>

	<div id="gap"></div>

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

			CompanyRankInfo collegeRankInformation = (CompanyRankInfo) ajaxResponse
			.getModel();

			if (null == collegeRankInformation) {
	%>
	<font color="red">Ranking of Colleges Could not be Done at this
		point of Time</font>
	<%
		} else {

			List<PolarityOrderInfo> collegeRankInfoList = collegeRankInformation
					.getPolarityOrderInfos();
	%>
	<div id="info">
		Ranking of Colleges with Respect to Features is the Following
	</div>
	<table class="tableCls" border="1">
		<tr>
			<td>College Name</td>
			<td>College Id</td>
			<td>Total Feature</td>
			<td>Total Negative</td>
			<td>Total Neutral</td>
			<td>Total Positive</td>
		</tr>
		<%
			for (PolarityOrderInfo polarityModel : collegeRankInfoList) {
		%>
		<tr>
			<td><%=polarityModel.getCompanyName()%></td>
			<td><%=polarityModel.getCompanyId()%></td>
		
			<td><%=polarityModel.getTotalFeature()%></td>

			<td><%=polarityModel.getTotalNegative()%></td>

			<td><%=polarityModel.getTotalNeutral()%></td>

			<td><%=polarityModel.getTotalPositive()%></td>

		</tr>

		<%
			}
		%>
	</table>
	<%
		}
	%>

</body>
</html>