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
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<link rel="stylesheet" type="text/css" href="css/button.css" />
		<link rel="stylesheet" type="text/css" href="css/login_style.css" />
		<link rel="stylesheet" type="text/css" href="css/form_control.css" />
		<link rel="stylesheet" type="text/css" href="css/select.css" />
		<link href="css/st.css" media="all" rel="stylesheet">
		<link href="css/styles.css" media="all" rel="stylesheet">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
       	<script src="js/main.js"></script>
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

    	<base href="<%=basePath%>">
    
    	<title>Diverse's SalaryManager|New Salary Item</title>
  
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

	<br><br><br>
	<div class="field">
				
	<h2> New Salary Item </h2>

	<form action="<%=basePath %>/servlet/addSalaryItemServlet">
	  <table>
		<tbody>
		 <tr>
  		   <td> Category<font color="red">*</font> </td>
  		   <td> 
  		   	<select name="category" class="styled-select">
				  <option value="fixed">Fixed Salary</option>
				  <option value="imported">Imported Salary</option>
				  <option value="calculated">Calculated Salary</option>
			</select>
			</td>
		</tr>
		<tr> 
		<td>Name <font color="red">*</font> </td>
		<td><input type="text" class="input" name="name" value=""/></td>
		</tr>
		<tr> 
		<td>Displayed<font color="red">*</font>&nbsp &nbsp </td>
		<td><input type="checkbox" name="displayed" value="displayed"/> &nbsp</td>
		</tr>
		<tr> 
		<td>Type <font color="red">*</font> </td>
		<td>
			<select name="type" class="styled-select">
				<option value="increase">Increase</option>
				<option value="decrease">Decrease</option>
				<option value="nothing">Do Nothing</option>
			</select>
		</td>
		</tr>
		<tr> 
		<td>Comment </td>
		<td> <textarea name="comment" class="input"></textarea></td>
		</tr>
		</tbody>
		</table>
		<table>
			<tbody>
				<tr> 
					<td>Formula &nbsp &nbsp &nbsp &nbsp</td>
				  	<td> 
				  	<select name="baseItemId" class="styled">
				  	<!-- db connection here-->
				  	<%
    				List tempList=(List)request.getAttribute("tempList"); 
    				for(int i=0;i<tempList.size();i++)
    				{
    					SalaryItem item =(SalaryItem)tempList.get(i);
    			  	%>
				  	<option value="<%=item.getId()%>"><%=item.getName()%></option>
				  <%} %>
				  </select>
				  </td>
				  <td>
				  <select name="operator" class="styled">
				  	<option value=""></option>
				  	<option value="+">+</option>
				  	<option value="-">-</option>
				  	<option value="*">*</option>
				  	<option value="/">/</option>
				  </select>
				  </td>
				  <td><input type="text" class="input" name="precedent" value=""/></td>
				</tr>
		</tbody>
      </table>	
      <br>			  		 
	  <div><input type="submit" class="button" value="save"></div>		 
	</form>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
	<script src="js/jquery.stickyheader.js"></script>
  </body>
</html>