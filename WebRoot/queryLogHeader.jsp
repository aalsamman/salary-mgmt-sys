<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="diverse.object.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<link rel="stylesheet" type="text/css" href="css/button.css" />	
		<link rel="stylesheet" type="text/css" href="css/select.css" />		
		<link href="css/st.css" media="all" rel="stylesheet">
		<link href="css/styles.css" media="all" rel="stylesheet">
		<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js"></script>-->
		<!--<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>-->
    	<script src="js/main.js"></script>
    	<script src="js/jquery-1.11.1.js"></script>
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
    <base href="<%=basePath%>">
    
    <title>Diverse's Salary Manager|Log Query</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <header class="clearfix">
      <a class="menu pull-left" data-position="left" href="#">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
      </a>
    </header>
	<nav class="left">
      <ul>
        <li>
         <a href="welcome.jsp">Home</a>
        </li>
        <li>
          <a href="addItem.jsp">Add Salary Items</a>
        </li>
        <li>
          <a href="queryCalculate.jsp">Calculate/Pay Salaries</a>
        </li>
        <li>
          <a href="querySalaryItems.jsp">Manage Salary Items</a>
        </li>
        <li>
          <a href="querytest.jsp">Show Salaries Details</a>
        </li>
      </ul>
    </nav>

		<!-- <div class="container"> -->
			<!-- Top Navigation -->
			<br> <br>
		<form class="field" action="<%=basePath %>/servlet/queryLogServlet">
		<select class="styled-select" name="department">
		<%
    		List tempList=(List)request.getAttribute("tempList"); 
    		for(int i=0;i<tempList.size();i++)
    		{
    			Department department =(Department)tempList.get(i);
    	%>
			<option value="<%=department.getId()%>"><%=department.getName()%></option> 
		<%} %>
		</select>  
		<select class="styled" name="month">
		<%for (int i=1; i<=12; i++){
		 %>
			<option value="<%=i %>"><%=i %></option>
		<%} %>
		</select>
		<input type="submit" class="button" value="get log"/> 
		<!--<input type="button" class="button" value="Save"/>-->
  </form>
  
		<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>-->
		<!--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>-->
		<script src="js/jquery.stickyheader.js"></script>
 
  </body>
</html>
