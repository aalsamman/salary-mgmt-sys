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
    
    <title>Diverse's Salary Manager|Calculate Salary</title>	
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
	<link href="css/st.css" media="all" rel="stylesheet">
	<link href="css/styles.css" media="all" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/main.js"></script>
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

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
<% String en=null;
String tmp=(String)request.getAttribute("saved");
if(tmp.equals("1")){
en="disabled";}else if(tmp.equals("0")){ en="enabled";}
 %>
		<!-- <div class="container"> -->
			<!-- Top Navigation -->
			<br> <br>
	<form class="field" action="<%=basePath %>/servlet/saveServlet">
	<input type="hidden" name="departmentID" id="departmentID" value="<%=request.getAttribute("departmentID") %>"/>
		<input type="submit" class="button" value="save" <%=en %>/>
	</form>
	<form class="field" action="<%=basePath %>/servlet/payServlet">
		<input type="submit" class="button" value="pay"/>
	</form>
	<div class="component">
		<%
			List sList=(List)request.getSession().getAttribute("sList"); 
			List cList=(List)request.getSession().getAttribute("cList"); 
			List tList=(List)request.getSession().getAttribute("tList"); 
		%>
		<table>
			<thead>
				<tr>
  					<th>Employee ID</th>
  					<th>Employee Name</th>
  					<% 
  						int index = ((FixedSalary)sList.get(0)).getEmployeeId();
  						for (int i = 0; i<sList.size(); i++)
  						{
  							FixedSalary sItem =(FixedSalary)sList.get(i);
  							
  							if(sItem.getEmployeeId()== index){		
    				%>
    				<th><%=sItem.getSalaryItemName()%></th>
    				<%}}%>
    				<% 
  						int index1 = ((CalculatedSalary)cList.get(0)).getEmployeeId();
  						for (int i = 0; i<cList.size(); i++)
  						{
  							CalculatedSalary sItem =(CalculatedSalary)cList.get(i);
  							
  							if(sItem.getEmployeeId()== index1){		
    				%>
    				<th><%=sItem.getItemName()%></th>
    				<%}}%>
    				<th>Total</th>
				</tr>
			</thead>
				<tbody>
				<%
					int prevId = 0;
					for (int i=0; i<sList.size();i++){
					FixedSalary sItem =(FixedSalary)sList.get(i); 
					if(prevId!=sItem.getEmployeeId()){
					prevId = sItem.getEmployeeId();
					
				%>
				<tr>
					<th><%=sItem.getEmployeeId() %></th>
					<td><%=sItem.getEmployeeName() %></td>
					<%
						for (int j=0; j<sList.size();j++){
						FixedSalary temp =(FixedSalary)sList.get(j); 
							if(temp.getEmployeeId()== sItem.getEmployeeId()){
								if(temp.getValue()!=0){
					%>
					<td><%=temp.getValue() %></td>
					
					<% }else{%>
					<td>&nbsp</td>
					<% }}} %>
					<%
						for (int j=0; j<cList.size();j++){
						CalculatedSalary temp =(CalculatedSalary)cList.get(j); 
							if(temp.getEmployeeId()== sItem.getEmployeeId()){
								if(temp.getValue()!=0){
					%>
					<td><%=temp.getValue() %></td>
					
					<% }else{%>
					<td>&nbsp</td>
					<% }}} %>
					<%
						for (int j=0; j<tList.size();j++){
						TotalSalary temp =(TotalSalary)tList.get(j); 
							if(temp.getEmployeeID()== sItem.getEmployeeId()){
								//if(temp.getValue()!=0){
					%>
					<td><%=temp.getFxSum()+temp.getIcSum()-temp.getTax() %></td>
					
					<% //}else{%>
					<!--<td>&nbsp</td>-->
					<% }} %>
				</tr>
				<%}}%>			
				</tbody>
				</table>
				</div>
				<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
		<script src="js/jquery.stickyheader.js"></script>
		<b><p align="middle">D = Days | H = Hours | [Insurance] CP = Company Paid | IP = Individual Paid</b>
		
 
  </body>
</html>
