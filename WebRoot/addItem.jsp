<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="diverse.object.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
    	<base href="<%=basePath%>">
    
    <title>Diverse's SalaryManager|New Salary Item</title>
    
	

  </head>
  <jsp:forward page="/servlet/QuerySalaryItemsServlet"></jsp:forward>
  <body>	
		
  </body>
</html>