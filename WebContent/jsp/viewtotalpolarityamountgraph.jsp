<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Research Graph</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all-gray.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/graph.css" />
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/extjs41/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/viewtotalpolaritytypeamount.js"></script>


</head>
<body>
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>


<div id="confirmationMessage" color="black" background="yellow" ></div>
<div id="ErrorDiv" />
<div id="buttonDiv"/>

<div id="contentDiv" />
<div id="AddDiv" />
<div id="keyContainer">
</div>
</div>

<div id="reviewGridContainer">
</div>
<div id="content">
</div>
<div id="negativecontent">
</div>
<div id="neutralcontent">
</div>

</body>
</html>