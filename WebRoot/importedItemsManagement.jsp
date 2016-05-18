<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="diverse.object.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  	<base href="<%=basePath%>">
    
    <title>Salary Items Management</title> 	
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
		<link rel="stylesheet" type="text/css" href="css/select.css" />
<link rel="stylesheet" type="text/css" href="css/login_style.css" />			
	<link href="css/st.css" media="all" rel="stylesheet">
	<link href="css/styles.css" media="all" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/main.js"></script>
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link type="text/css" rel="stylesheet" href="css/lightbox-form2.css">
<script src="js/lightbox-form2.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/lightbox-form3.css">
<script src="js/lightbox-form3.js" type="text/javascript"></script>

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
		
	<div class="component">
		<%List ImportedSalariesList=(List)request.getAttribute("ImportedSalariesList"); 
		%>
		<table>
			<thead>
				<tr>
  					<th>Employee ID</th>
  					<th>Employee Name</th> 
  					<% 
  						int index = ((ImportedSalary)ImportedSalariesList.get(0)).getEmployeeId();
  						for (int i = 0; i<ImportedSalariesList.size(); i++)
  						{
  							ImportedSalary importedItem =(ImportedSalary)ImportedSalariesList.get(i);
  							
  							if(importedItem.getEmployeeId()==index){		
    				%>
    				<th><a href="javascript:void(0)" onClick="openboxx('Edit Value', 1,<%=request.getAttribute("departmentID") %>,<%=importedItem.getSalaryItemId() %>)"><%=importedItem.getSalaryItemName()%></a></th>
    				<%}}%>
				</tr>
			</thead>
				<tbody>
				<%
					int prevId = 0;
					for (int i=0; i<ImportedSalariesList.size();i++){
					ImportedSalary importedItem =(ImportedSalary)ImportedSalariesList.get(i); 
					if(prevId!=importedItem.getEmployeeId()){
					prevId = importedItem.getEmployeeId();
					
				%>
				<tr>
					<th><%=importedItem.getEmployeeId() %></th>
					<td><%=importedItem.getEmployeeName() %></td>
					<%
						for (int j=0; j<ImportedSalariesList.size();j++){
						ImportedSalary temp =(ImportedSalary)ImportedSalariesList.get(j); 
							if(temp.getEmployeeId()==importedItem.getEmployeeId()){
								if(temp.getValue()!=0){
					%>
					<td><a href="javascript:void(0)" onClick="openbox('Edit Value', 1,<%=temp.getEmployeeId() %>,<%=temp.getSalaryItemId() %>,'michel')"><%=temp.getValue() %></a></td>
					
					<% }else{%>
					<td><a href="javascript:void(0)" onClick="openbox('Edit Value', 1,<%=temp.getEmployeeId() %>,<%=temp.getSalaryItemId() %>,'michel')">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </a></td>
					<% }}} %>
				</tr>
				<%}}%>			
				</tbody>
				</table>
				</div>
				<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
		<script src="js/jquery.stickyheader.js"></script>
		<b><p align="middle">D = Days | H = Hours | [Insurance] CP = Company Paid | IP = Individual Paid</b>
		
		<div id="shadowing"></div>
<div id="box">
  <span id="boxtitle"></span>
  <form action="<%=basePath %>/servlet/modifySalaryValueServlet" >
      <table>
					<tbody>
					<tr><td><input type="hidden" id="departmentID" name="departmentID" value="<%=(String)request.getAttribute("departmentID") %>"/></td></tr>
					<tr><td><input type="hidden" id="employeeID" name="employeeID"/></td></tr>
					<tr><td><input type="hidden" id="salaryItemID" name="salaryItemID"/></td></tr>
					<tr><td><input type="hidden" id="editor" name="editor"/></td></tr>	
				 <tr> <td>New Value </td><td><input type="text" class="input" id="value" name="value" /></td></tr>
				  	<tr><td><input type="submit" class="button" value="save"></td><td><input type="button" class="button" value="Cancel" onClick="closebox()"></td></tr>
		</tbody>
		</table>				     
  </form>
</div>

<div id="shadowing3"></div>
<div id="box3">
  <span id="boxtitle3"></span>
  <form action="<%=basePath %>/servlet/modifyItemValuesServlet">
      <table>
					<tbody>
					<tr><td><input type="hidden" id="departmentID2" name="departmentID2" /></td></tr>
					<tr><td><input type="hidden" id="salaryItemID2" name="salaryItemID2" /></td></tr>	
				 <tr> <td>Item Values </td><td><input type="text" class="input" id="value2" name="value2" /></td></tr>
				  	<tr><td><input type="submit" class="button" value="Save""></td><td><input type="button" class="button" value="Cancel" onClick="closeboxx()"></td></tr>
		</tbody>
		</table>				     
  </form>
</div>
 
  </body>
</html>
