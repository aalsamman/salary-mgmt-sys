<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Diverse's Salary Manager|Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="../favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<!--<link rel="stylesheet" type="text/css" href="css/demo.css" />-->
	<link rel="stylesheet" type="text/css" href="css/component.css" />
	<link rel="stylesheet" type="text/css" href="css/button.css" />
	<link rel="stylesheet" type="text/css" href="css/form_control.css" />
	<link rel="stylesheet" type="text/css" href="css/select.css" />
	<link href="css/st.css" media="all" rel="stylesheet">
	<link href="css/styles.css" media="all" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/login_style.css">
	<link rel="stylesheet" type="text/css" href="css/login_style.css" />
	<link rel="stylesheet" type="text/css" href="css/button.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/main.js"></script>
  </head>
  <body>
  
  	<%
//allow access only if session exists
String user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
  
    <header class="clearfix">
      <a class="menu pull-left" data-position="left" href="#">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
      </a>
    </header>
	
		<!--<div class="field">
			<h4 align="center"> Welcome to Diverse's Salary Management System </h4>
		</div>-->
		
		<% String x="";
if(request.getAttribute("error")!= null){
		x=(String)request.getAttribute("error");}  %>
		<div class="component">
		<a><font color="red"><%=x%></font></a></div>
		
		<div id="login">
			<form action="<%=basePath %>/servlet/loginServlet" method="POST">
				<fieldset>
					<input type="text" name="username" id="username" required class="input" value="Username" onBlur="if(this.value=='')this.value='Username'" onFocus="if(this.value=='Username')this.value='' ">
					<input type="password" name="password" id="password" required class="input" value="Password" onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' ">
					<input type="submit" class="button" value="Login">
				</fieldset>
			</form>
		</div> <!-- end login -->
  </body>
</html>
