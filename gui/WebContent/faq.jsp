<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<jsp:include page="main.jsp" /> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Pooling System</title>
<LINK href="resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<LINK href="resources/custom.style.css"
	rel="stylesheet" type="text/css">

</head>
<body class="isIE" style="cursor: default;">

<div class="layout" style="float:left;display:inline;">
<table style="width:100%;">
	<tr>
		<td style="width:99%;">
			<div class="block" >
			
			<h4>Car Pooling is better for the environment</h4>
			Having fewer cars on the road means reduced Greenhouse Gas (GHG) emissions and
			improved air quality. 
			<br>
			<h4>It is good for your health</h4>
			According to Environment Canada, air pollution caused by vehicular travel is linked
			to a number of health concerns including respiratory diseases, cardiovascular disease,
			allergies and neurological effects.  By car Pooling, you help reduce these health risks
			for yourself and everyone else. Research also suggests that car Pooling is less
			stress full than commuting alone.  
			<br>
			<h4>Car Pooling is convenient</h4>
			Car Pooling provides commuting convenience comparable to driving alone, with less
			stress and with the added bonus of companionship while you are commuting.  Car Pooling
			partners establish their own unique rules that best meet the needs of their car pool. 
			<br>
			<h4>Car Pooling improves your commuting options</h4>
			Car Pooling offers a commuter option that may work better than other methods of
			transportation. Car Pooling works best for people who live where transit service
			may be limited or non-existent and compared to other options, car pooling may better
			fit your schedule. 
			<br>
			<div class="VZFooter"><img title="Go Green" alt="Go Green" style="margin-left: -18px;"
			src="resources/goGreen.gif" width = "110%" height = "110%" ></div>
			<h4>Make new friends</h4>
			Car Pooling is a great way to make new friends!
			
			<br>
			<h3 style = "font-weight: bold; color: green;">SAVE FUEL. SAVE ENVIRONMENT. GO GREEN.</h3>
			
			<hr>
		</td>
	</tr>
</table>
	
</div>
<div class="footer">
   <div class="layout">
       <div class="copyrights">&copy; 2015 Designed by Team Hackers (Surendra Ganti, Pavan Akurathi, Pavan Sathya) <a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
   </div>
</div>

<script type="text/javascript">
	function login()
	{
		
		var userName = document.getElementById('username').value;
		var password = document.getElementById("password").value;
		
		if(userName != null && userName != '' && password != null && password != ''){
			document.getElementById('loginForm').submit();
		}else if(userName == null || userName == ''){
			alert("Please enter your User Name");
		}else{
			alert("Please enter your Password");
		}
	}
</script>


</body>
</html>