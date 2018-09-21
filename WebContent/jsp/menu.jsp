<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/welcome.jsp"><span>Home</span></a></li>

			<li class='has-sub '><a href='#'>HashTag<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/hashtagsubmission.jsp'><span>
								Submit HashTag</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewhashtags.jsp'><span>
								View Hash Tags </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Twitter<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/tweetsubmission.jsp'><span>
								Tweet Submission</span></a></li>
					<li><a href='<%=request.getContextPath()%>/jsp/viewtweets.jsp'><span>
								Tweet Collection </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Keyword Analyzer<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/addpositivekeyword.jsp'><span>
								Add Positive Keyword</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewpositivekeywords.jsp'><span>
								View Positive Keywords </span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/removepositivekeyword.jsp'><span>
								Remove Positive Keyword </span></a></li>

					<li><a
						href='<%=request.getContextPath()%>/jsp/addnegativekeyword.jsp'><span>
								Add Negative Keyword </span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewnegativekeywords.jsp'><span>
								View Negative Keyword </span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/removenegativekeyword.jsp'><span>
								Remove Negative Keyword </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Polarity Algorithm<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/review/rankPolarity.do?type=1'><span>Rank
								Company Polarity</span></a></li>

					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewpolarity.jsp"><span>View
								Company Tweet Polarity</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolarity.jsp"><span>Total
								Company Polarity</span></a></li>
				</ul></li>
				
			<li class='has-sub '><a href='#'>Graphs<span></span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolarityvolumegraph.jsp"><span>Volume</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolarityclosegraph.jsp"><span>Close
								Price</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolarityamountgraph.jsp"><span>Amount</span></a></li>
				</ul></li>
				
			<li class='has-sub '><a href='#'>Finance<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/jsp/finance.jsp'><span>
								Finance</span></a></li>
					<li><a
						href='<%=request.getContextPath()%>/jsp/viewfinance.jsp'><span>
								View Finance </span></a></li>

				</ul></li>

			<li class='has-sub '><a href='#'>Sentiment Index<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/review/performSentiments.do'><span>Rank
								Sentiments</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewsentimentindex.jsp"><span>View
								Sentiments</span></a></li>
				</ul></li>
				
				<li class='has-sub '><a href='#'>Prediction<span></span></a>
				<ul>
					<li><a
						href='<%=request.getContextPath()%>/review/performPrediction.do'><span>Perform
								Prediction</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewprediction.jsp"><span>View
								Prediction</span></a></li>
				</ul></li>
				
			
				
				<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/viewrankstock.jsp"><span>Ranking Stocks</span></a></li>

			
</body>
</html>