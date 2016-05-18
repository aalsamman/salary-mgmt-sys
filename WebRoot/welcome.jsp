<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Welcome to Diverse Salary Management System</title>
		<link href="css2/welcome.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		</script>
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,500,600,700,800,900' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<script src="js2/jquery.min.js"></script>
		<script type="text/javascript" src="js2/move-top.js"></script>
		<script type="text/javascript" src="js2/easing.js"></script>
		
	</head>
	<body>
		<!----start-wrap---->

		
		<!----start-image-slider---->
		<div class="img-slider" id="home">
			<!-- start slider -->
		    <div id="fwslider">
		            <div class="slide"> 
		                <!-- Texts container -->
		                <div class="slide_content">
		                    <div class="slide_content_wrap">
		                        <!-- Text title -->
					
		                        <h4 class="title">The Diverse Team</h4>
		                        <!-- /Text title -->
		                        <!-- Text description -->
		                        <p class="description">Salary Management System</p>
								
		                        <!-- /Text description -->
		                        <div class="slide-btns description">
		                        	<img src="images/divice-screen1.png" title="Diverse" />
		                        </div>
		                    </div>
		                </div>
		                 <!-- /Texts container -->
		            </div>
		        <div class="slidePrev"><span> </span></div>
		        <div class="slideNext"><span> </span></div>
		    </div>
		    <!---slider---->
		    <link rel="stylesheet" href="css2/fwslider.css" media="all">
			<script src="js2/fwslider.js"></script>
			<!---//slider---->
		    <!--/slider -->
		</div>
		<div class="clear"> </div>
		<!----//End-image-slider---->
		<div class="top-grids" id="services">
				<div class="wrap">
					<div class="top-grid">
						<a class="icon add" href="addItem.jsp"> </a>
						<a href="addItem.jsp">Add New Salary Item</a>
						<p><font color="white" align="center">You have new salary item? Add here.</p>
						
					</div>
					<div class="top-grid">
						<a class="icon management" href="querySalaryItems.jsp"> </a>
						<a href="querySalaryItems.jsp">Configure Salary Item</a>
						<p><font color="white" align="center">Configure and Manage your employee salary item.</p>
					</div>
					<div class="top-grid">
						<a class="icon calculate" href="queryCalculate.jsp"> </a>
						<a href="queryCalculate.jsp">Calculate Salary</a>
						<p><font color="white" align="center">It is time to calculate your employee salary</p>
					</div>
					<div class="top-grid">
						<label> </label>
						<a class="icon pay" href="querytest.jsp"> </a>
						<a href="querytest.jsp">Show</a>
						<p><font color="white" align="center">Show Individual Salaries</p>
					</div>
					<div class="top-grid">
						<label> </label>
						<a class="icon features" href="queryDepartmentsLog.jsp"> </a>
						<a href="queryDepartmentsLog.jsp">History</a>
						<p><font color="white" align="center">Previous Paid Salaries</p>
					</div>
					<div class="top-grid">
						<label> </label>
						<a class="icon logout" href="<%=basePath %>/servlet/LogoutServlet"> </a>
						<a href="/servlet/LogoutServlet">Log Out</a>
						<p><font color="white" align="center">Disconnect.</p>
					</div>
					<div class="clear"> </div>
				</div>
			</div>
					<div class="clear"> </div><br><br>

			</div>
		<!----//End-content--->
		<!----//End-wrap---->
	</body>
</html>

