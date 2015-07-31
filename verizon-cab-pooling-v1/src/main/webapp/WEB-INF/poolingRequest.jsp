<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- saved from url=(0014)about:internet -->
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<jsp:include page="main.html" /> 
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
	$(document).ready(function(){
        $( "#startDate" ).datepicker({
           appendText:"(dd-mm-yy)",
           dateFormat:"dd-mm-yy"
        });
        
        /*
        $( "#endDate" ).datepicker({
            appendText:"(dd-mm-yy)",
            dateFormat:"dd-mm-yy"
         });
        */
        
        $("#estStartTimeHour" ).spinner();
        $("#estStartTimeHour").attr('min', 0);
        $("#estStartTimeHour").attr('max', 24);
        
        $("#estStartTimeMins" ).spinner();
        $("#estStartTimeHour").attr('min', 0);
        $("#estStartTimeHour").attr('max', 60);
        
     });
	
	function submitRequest()
	{
		document.getElementById('poolRequest').submit();
	}

	</script>

</head>

<body class="isIE" style="cursor: default;" onload="fnOnload()">
	<form action = "raiseRequest.do" id = "poolRequest" method="get">
	<div id="container" class="container">
		
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
					<label  class="readOnlyLabel">Supervisor Name : </label> <label>Preetha,Philip</label>
				</section>
			</fieldset>
		</div>
			
		
		<div id="poolingInfo" class="poolingInfo">
			
			<fieldset class="piempInfo">
				<legend class="legend">Car Pooling Details</legend>
				
				<table style="width:100%" class="poolingInfo">
					<tr>
						<td colspan="2" >
							<section >
								 
								<input id=enrolled class=radio checked type=radio value=enrolled name=status>
								<label style="font-weight:bold;">Enrolled  </label>

								<input id=withdraw class=radio type=radio value=withdraw name=status>
								<label style="font-weight:bold;">Withdraw  </label>
								 
							</section>
						</td>
					</tr>
					<tr>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Start Date :  </label> 
								<input type ="date"  onclick="fnDate(this);" id="startDate" class="startDate" >
							</section>
						</td>
						<!--
						<td >
							<section  class="controlSection">
								<label class="controlLabel">End Date :  </label> 
								<input type ="date" onclick="fnDate(this);" id="endDate" class="endDate" >
							</section>
						</td>
						-->
						
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Estimated Start Time (hh:mm) :  </label> 
								<input type ="text"   id="estStartTimeHour" class="estStartTimeHour" value="0"> :
								<input type ="text"   id="estStartTimeMins" class="estStartTimeMins" value="0"> 
							</section>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<section>
							
								<input onclick="fnCheck('rdnonetime');" id="rdNeed" class=radio checked type=radio value="rdNeed" name="poolType">
								<label style="font-weight:bold;">Need  </label>
								
								<input onclick="fnCheck('rdnonetime');" id="rdProvide" class=radio type=radio value="rdProvide" name="poolType">
								<label style="font-weight:bold;">Provide   </label>
							
							</section>
						</td>
					</tr>
					<tr>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Vehicle Type :  </label> 
								<input type ="text"  onclick="fnVehicle(this);" id="vehicleType" class="vehicleType" >
							</section>
						</td>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Capacity :  </label> 
								<input type ="text" id="capacity" class="capacity" >
							</section>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<section >
								 
								<input onclick="fnCheck('rdnonetime');" id=pick class=radio checked type=radio value=pic name=requestType>
								<label style="font-weight:bold;">Pick   </label>
								 
								<input onclick="fnCheck('rdnweekly');" id=drop class=radio type=radio value=drop name=requestType>
								<label style="font-weight:bold;">Drop  </label>
							</section>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<jsp:include page="GMaps.jsp" />
						</td>
					</tr>
					
					<!--  <tr>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Source :  </label> 
								<input type ="text"  onclick="fnDisplayMapWindow(this);" id="source" class="source" >
							</section>
						</td>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Destination :  </label> 
								<input type ="text" onclick="fnDisplayMapWindow(this);" id="destination" class="destination" >
							</section>
						</td>
					</tr> -->
					
				
				</table>
				
			</fieldset>
			
			
		</div>
		<div id="btnContainer" class="btnContainer">
			<div>
				<input type = "button" value="Submit" onclick = "javascript: submitRequest();"style = "background-color: rgb(231, 28, 36); color : white; FLOAT: left; MARGIN-LEFT: 5px;">
			</div>
		</div>
		
		<div id="avlVehicles" class="avlVehicles"> </div>
		
	
	</div>
	<input type= "hidden" id ="userLocation" name = "userLocation">
	
	</form>
</body>
</html>