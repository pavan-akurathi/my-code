<!DOCTYPE html>
<html class="vz-theme">
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
<%
String message = request.getAttribute("message")!=null?(String)request.getAttribute("message"):"";
%>
<body class="isIE" style="cursor: default;">


<div class="header">
<div class="layout">
<div class="VZlogo"><IMG title="Verizon" alt="Verizon" style="margin-left: -18px;"
	src="resources/VerizonLogo.png" width = "110%" height = "110%" ></div>
<div class="app-name">Car Pooling System</div>
<div class="app-support">&nbsp;<BR>
Email: <A href="mailto:VDSI-CarPoolingSystem@one.verizon.com">vdsi.carpooling@gmail.com</A><BR>
&nbsp;<BR>
</div>
</div>
</div>

<div class="mast">
<div class="layout">
<div class="mast-content"></div>
</div>
</div>

<div class="layout" style="float:left;display:inline;">
<table style="width:100%;">
	<tr>
		<td style="width:30%;"> 
		
			<div class="login-content" >

			<div class="signin-block">
			<div class="login-text"><font color=red><%=message%></font></div>
			<div class="login-text">Sign in with your Emp ID and
			Password.</div>
			
			<div id="logonbox-container">
			<div id="logonbox-innerbox" >
			<div id="logonbox-logonform" >
			<form id = "loginForm" class="form insertPoint credentialform" action = "login" method = "post">
			<div class="field CredentialTypeusername">
			<div class="left"><label class="label plain" for="username">User
			name:</label></div>
			<div class="right"><input name="username" id="username"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypepassword">
			<div class="left"><label class="label plain" for="password">Password:</label></div>
			<div class="right"><input name="password" id="password"
				spellcheck="false" type="password" autocomplete="off"></div>
			</div>
			<div class="field buttonsrow">
			<div class="right"><a class="button"
				id="createBtn" href="createNewUser">New User</a></div>
			<div class="right"><a class="button"
				id="loginBtn"  onkeypress="login();" onclick= "login();" >Log On</a></div>
			</div>
			<div class="spacer"></div>
			</form>
			</div>
			</div>
			</div>
			
			
			<div id="logonbox-choices"><!-- placeholder where the first-time-use choices screen will be generated -->
			</div>
			</div>
			</div>
			
			
			<!-- Start Quick Links  -->
			<div class="block">
			<h1>Support Numbers</h1>
			<div class="text">
			<ul class="quick-links">
				<li><b>HelpDesk Support (HYDERABAD):</b>
				<div style="padding-left: 100px;">040 - 4400 5949 (Titus)</div>
				<div style="padding-left: 100px;">040 - 4400 7100 (Orion)</div>
				</li>
				<li><b>HelpDesk Support (CHENNAI): </b>
				<div style="padding-left: 100px;">044 - 4394 0700 (Olympia)</div>
				<div style="padding-left: 100px;">044 - 6682 6200 (Rmz)</div>
				</li>
			</ul>
			<br>
			<br>
			</div>
			</div>
			<!-- End Quick Links -->
		</td>
	
		<td style="width:70%;">
			<div class="block" >
			<h1>VDSI Car Pooling System</h1><br>
			Dear All, Car Pooling saves money and reduces congestion on our roads and highways.
			It also gives you the opportunity to develop new friendships with co workers.
			There are a number of benefits when two or more people share a ride in one vehicle.
			
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
<div class="layout">   
       <div class="copyrights"><b>&copy; 2015 Designed by Team Hackers (Surendra Ganti, Pavan Akurathi, Pavan Satya) </b><a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
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