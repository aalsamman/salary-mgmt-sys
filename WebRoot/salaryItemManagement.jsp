<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="diverse.object.*"%>
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
		<link rel="stylesheet" type="text/css" href="css/form_control.css" />
		<link href="css/st.css" media="all" rel="stylesheet">
		<link href="css/styles.css" media="all" rel="stylesheet">
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.1/modernizr.min.js">
</script>
		<script src="https://code.jquery.com/jquery-1.10.2.min.js">
</script>
		<script src="js/main.js">
</script>
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link type="text/css" rel="stylesheet" href="css/lightbox-form.css">
		<script src="js/lightbox-form.js" type="text/javascript">
</script>


		<script type="text/javascript">
function dodelete(id) {
	var myform = document.getElementById("myform");
	myform.id.value = id;

	myform.action = " <%=basePath%>servlet/deleteSalaryItemServlet " ;
	myform.submit();
}
</script>
	</head>

	<body>
		<!--<form action="<%=basePath%>/servlet/QueryServlet">-->
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
		<!-- Top Navigation-->
		<br>
		<br>
		<!--<form class="field">  
		<input type="button" class="button" value="New"/>
			</form>-->

		<form id="myform" action="<%=basePath%>/addItem.jsp">
			<div class="field">
				<input type="hidden" name="id" />
			<!--  <input type="submit" class="button" value="New" />-->
			</div>
		</form>
<% String x="";
if(request.getAttribute("error")!= null){
		x=(String)request.getAttribute("error");}  %>
		<div class="component">
		<a><font color="red"><%=x%></font></a>
			<table>
				<thead>
					<tr>
						<th>
							Category
						</th>
						<th>
							Name
						</th>
						<th colspan="2">
							&nbsp
						</th>
					</tr>
				</thead>
				<tbody>
					<%
						List tempList = (List) request.getAttribute("tempList");
						for (int i = 0; i < tempList.size(); i++) {
							SalaryItem item = (SalaryItem) tempList.get(i);
					%>
					<tr>
						<th><%=item.getCategory()%></th>
						<td><%=item.getName()%></td>
						<td>
							<a href="javascript:void(0)"	onClick="openbox('Edit Item',1,<%=item.getId()%>,'<%=item.getName()%>');"><input
									type="button" value="modify" name="modify" class="button">
							</a>
						</td>
						<td>
							<a href="javascript:void(0)"	onclick="dodelete('<%=item.getId()%>');"><input class="button" type="button" value="delete" name="delete"></a>
						</td>

					</tr>
					<%
						}
					%>


				</tbody>
			</table>
		</div>

		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js">
</script>
		<script
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js">
</script>
		<script src="js/jquery.stickyheader.js">
</script>

		<!-- Here comes the Lightbox form :) -->
		<div id="shadowing"></div>
		<div id="box">
			<span id="boxtitle"></span>
			<form action="<%=basePath %>/servlet/modifySalaryItemServlet" target="_parent">
				<table>
					<tbody>
						<tr>
							<td>
								<input type="hidden" id="id" name="id" value=""/>
							</td>
						</tr>
						<tr>
							<td>
								Category
								<font color="red">*</font>
							</td>
							<td>
								<select name="category" id="category" class="styled-select">
									<option value="basic">
										Basic Salary
									</option>
									<option value="imported">
										Imported Salary
									</option>
									<option value="calculated">
										Calculated Salary
									</option>
								</select>
							</td>
						</tr>

						<tr>
							<td>
								Name
								<font color="red">*</font>
							</td>
							<td>
								<input type="text" class="input" id="name" name="name" />
							</td>
						</tr>
						<tr>
							<td>
								Displayed
								<font color="red">*</font>&nbsp &nbsp
							</td>
							<td>
								<input type="checkbox" name="displayed" id="displayed"
									value="displayed" />
								&nbsp
							</td>
						</tr>
						<tr>
							<td>
								Type
								<font color="red">*</font>
							</td>
							<td>
								<select name="type" id="type" class="styled-select">
									<option value="increase">
										Increase
									</option>
									<option value="decrease">
										Decrease
									</option>
									<option value="nothing">
										Do Nothing
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Comment
							</td>
							<td>
								<textarea name="comment" class="input" id="comment"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table>
					<tbody>
						<tr>
							<td>
								Formula &nbsp &nbsp &nbsp &nbsp
							</td>
							<td>
								<select name="baseItemId" class="styled">
									<!-- db connection here-->
									<%
										List tempList2 = (List) request.getAttribute("tempList");
										for (int i = 0; i < tempList2.size(); i++) {
											SalaryItem item = (SalaryItem) tempList2.get(i);
									%>
									<option value="<%=item.getId()%>"><%=item.getName()%></option>
									<%
										}
									%>
								</select>
							</td>
							<td>
								<select name="operator" class="styled">
									<option value=""></option>
									<option value="+">
										+
									</option>
									<option value="-">
										-
									</option>
									<option value="*">
										*
									</option>
									<option value="/">
										/
									</option>
								</select>
							</td>
							<td>
								<input type="text" class="input" name="precedent" value="" />
							</td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="button" value="Modify"> <input type="button" class="button" value="Cancel" onClick="closebox()">
			</form>
		</div>
	</body>
</html>

