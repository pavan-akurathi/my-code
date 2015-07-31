<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- saved from url=(0014)about:internet -->
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<jsp:include page="main.jsp" /> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Pooling System</title>
<link href="resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<link href="resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<link href="resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<link href="resources/custom.style.css"
	rel="stylesheet" type="text/css">

<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>
	function submitRequest()
	{
		document.getElementById('poolRequest').submit();
	}
</script>

</head>

<body class="isIE" style="cursor: default;" >
	<form action = "submitRequest.do" id = "poolRequest" method="get">
	<div id="container" class="container" >
		
		<div id="empInfo" class="empInfo"> 
			<fieldset class="fsempInfo">
				<legend class="legend">Employee Details</legend>
				<section class="readOnlySection">
					<label class="readOnlyLabel">Employee ID : </label> <label>2434459</label>
				</section >
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Employee Name : </label> <label>Surendra X Ganti</label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">E-mail : </label> <label>surendra.ganti@one.verizon.com</label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Current Pool : </label> <label>Pavan</label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Start Date : </label> <label>01/08/2015</label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Estimated Start Time : </label> <label>09:30</label>
				</section>
			</fieldset>
		</div>
			
			
		<div  class="poolingAvlDetails"> 
			<table style="width:100%;">
				<tr>
					<td class="poolAvlMap">
						<div  >
							<jsp:include page="GMapsWithMultipleMarkers.jsp" />
						</div >
					</td>
					<td class="poolAvlGrid">
						<div  style="height:450px;overflow:auto;">
							<%for(int index=0; index<10; index++) { if(index%2==0){%>
								
								<div id="avlVehicle" class="avlVehicleEven">
								<%}else{ %>
									<div id="avlVehicle" class="avlVehicleOdd">
								<%} %>
									<table style="width:100%;">
										<tr><td><label  class="readOnlyLabel">Employee Name:</label> Employee1 <input type="radio" id="<%=index %>" style="float:right;" name="avlVehicleChk"></td></tr>
										<tr><td><label  class="readOnlyLabel">Employee Email:</label> Employee@vz.com</td></tr>
										<tr><td><label  class="readOnlyLabel">Contact Number:</label> 123456789</td></tr>
										<tr><td><label  class="readOnlyLabel">Vehicle Name:</label> Honda Amaze</td></tr>
										
									</table>
								</div>
								<%}%>
						</div >
					</td>
				</tr>
			</table>
		</div>
	
		<div id="btnContainer" class="btnContainer">
			<input type = "button" value="Submit Request" onclick = "javascript: submitRequest();"style = "background-color: rgb(231, 28, 36); color : white; FLOAT: left; MARGIN-LEFT: 5px;">
		</div>
	
	</div>
	<input type= "hidden" id ="userLocation" name = "userLocation" value = "">
	
	</form>
</body>
</html>