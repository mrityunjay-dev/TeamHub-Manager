<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>System Generated Page</title>
</head>
<body>
<h3><%=(String)request.getAttribute("status")%></h3>
<h4><%=(String)request.getAttribute("message") %></h4>
<a href="<%=(String)request.getAttribute("redirectUrl")%>"> Click Here to Redirect</a>

</body>
</html>