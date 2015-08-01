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
		<td> 
		
			<div class="login-content" >

			<div class="signin-block">
			<div class="login-text">Please setup your account with below information.</div>
			
			<div id="logonbox-container">
			<div id="logonbox-innerbox" >
			<div id="logonbox-logonform" >
			<form id = "registerForm" class="form insertPoint credentialform" method="post" action = "register">
			<div class="field CredentialTypeemployeeid">
			<div class="left"><label class="label plain" for="employeeid">Employee
			Id:</label></div>
			<div class="right"><input name="employeeid" id="employeeid"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypepassword">
			<div class="left"><label class="label plain" for="password">Password:</label></div>
			<div class="right"><input name="password" id="password"
				spellcheck="false" type="password" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypefirstname">
			<div class="left"><label class="label plain" for="firstname">First
			Name:</label></div>
			<div class="right"><input name="firstname" id="firstname"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypelastname">
			<div class="left"><label class="label plain" for="lastname">Last
			Name:</label></div>
			<div class="right"><input name="lastname" id="lastname"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypephnumber">
			<div class="left"><label class="label plain" for="phnumber">Mobile #:</label></div>
			<div class="right"><input name="phnumber" id="phnumber"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypeemail">
			<div class="left"><label class="label plain" for="email">Email:</label></div>
			<div class="right"><input name="email" id="email"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field CredentialTypezipcode">
			<div class="left"><label class="label plain" for="zipcode">Zip Code:</label></div>
			<div class="right"><input name="zipcode" id="zipcode"
				spellcheck="false" type="text" autocomplete="off"></div>
			</div>
			<div class="field buttonsrow">
			<div class="buttonscontainer right"><a class="button"
				id="registerBtn"  onkeypress="register();" onclick= "register();" >Register</a></div>
			<div class="spinner" style="visibility: hidden;"><img alt=""
				src="media/Loader.gif"></div>
			</div>			
			<div class="spacer"></div>
			</form>
			</div>
			</div>
			</div>
			
			</div>
			</div>
			
		</td>	
	</tr>
</table>
	
</div>
<div class="footer">
   <div class="layout">
       <div class="copyrights">&copy; 2015 Designed by Team Hackers (Surendra Ganti, Pavan Akurathi, Pavan Satya) <a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
   </div>
</div>

<script type="text/javascript">
	function register()
	{
		
		var employeeid = document.getElementById('employeeid').value;
		var password = document.getElementById("password").value;
		var firstname = document.getElementById('firstname').value;
		var lastname = document.getElementById('lastname').value;
		var phnumber = document.getElementById('phnumber').value;
		var email = document.getElementById('email').value;
		var zipcode = document.getElementById('zipcode').value;
					
		if(employeeid != null && employeeid != '' && password != null && password != '' && firstname != null && firstname != '' && lastname != null && lastname != '' &&
				phnumber != null && phnumber != '' && email != null && email != '' && zipcode != null && zipcode != ''){
			document.getElementById('registerForm').submit();
		}else{
			alert("All fields are mandatory, Please fill them");
		}
	}
</script>


</body>
</html>