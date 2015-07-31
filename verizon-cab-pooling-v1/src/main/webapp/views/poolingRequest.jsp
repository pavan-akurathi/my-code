<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- saved from url=(0014)about:internet -->
<!DOCTYPE html>
<html class="vz-theme" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Pooling System</title>
<link href="/resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<link href="/resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<link href="/resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<link href="/resources/custom.style.css"
	rel="stylesheet" type="text/css">

<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<%
String empid = request.getAttribute("empid")!=null?(String)request.getAttribute("empid"):"";
String firstname = request.getAttribute("firstname")!=null?(String)request.getAttribute("firstname"):"";
String lastname = request.getAttribute("lastname")!=null?(String)request.getAttribute("lastname"):"";
String email = request.getAttribute("email")!=null?(String)request.getAttribute("email"):"";
String zipcode = request.getAttribute("zipcode")!=null?(String)request.getAttribute("zipcode"):"";
String status = request.getAttribute("status")!=null?(String)request.getAttribute("status"):"Y";
String startDate = request.getAttribute("startDate")!=null?(String)request.getAttribute("startDate"):"";
String poolType = request.getAttribute("poolType")!=null?(String)request.getAttribute("poolType"):"N";
String vehicleType = request.getAttribute("vehicleType")!=null?(String)request.getAttribute("vehicleType"):"";
String capacity = request.getAttribute("capacity")!=null?(String)request.getAttribute("capacity"):"";
String location = request.getAttribute("location")!=null?(String)request.getAttribute("location"):"17.438878,78.381206";

%>
	<script>
	// Default Loc : 17.438878,78.381206
	var latLong = '<%=location%>';	
	var latitude = latLong.split(",")[0];
	var longitude = latLong.split(",")[1];
	$(document).ready(function(){
        $( "#startDate" ).datepicker({
           appendText:"(dd-mm-yy)",
           dateFormat:"dd-mm-yy"
        });
        
        $("#estStartTimeHour" ).spinner();
        $("#estStartTimeHour").attr('min', 0);
        $("#estStartTimeHour").attr('max', 24);
        
        $("#estStartTimeMins" ).spinner();
        $("#estStartTimeHour").attr('min', 0);
        $("#estStartTimeHour").attr('max', 60);
        
     });
	
	function submitRequest()
	{
		document.getElementById('userLocation').value = latLong;
		document.getElementById('poolRequest').submit();
	}

	</script>

</head>

<body class="isIE" style="cursor: default;" onload="fnOnload()">
	<div class="header">
		<div class="layout">
			<div class="VZlogo">
				<IMG title="Verizon" alt="Verizon" src="/resources/VerizonLogo.png"
					width="110%" height="110%">
			</div>
			<div class="app-name">Car Pooling System</div>
			<div class="app-support">
				&nbsp;<br> Email: <a
					href="mailto:VDSI-CarPoolingSystem@one.verizon.com">VDSI-CarPoolingSystem@one.verizon.com</a><br>
				&nbsp;<br>
			</div>
		</div>
	</div>

	<div class="mast1" style = "font-weight: bold; height: 40px; color: white; overflow: hidden; font-size: 14px; display: block; background-color: rgb(231, 28, 36); margin: auto;">&nbsp;&nbsp;
		<table style = "margin-top: 5px; float: left;">
			<tr>
				<td>
					<a href="#" id="poolingRequest" title="poolingRequest" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Pooling Request</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="/views/myPool.jsp" id="myPool" title="myPool" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">My Pool</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="/views/reports.jsp" id="reports" title="reports" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Reports</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="/views/faq.jsp" id="faq" title="FAQ's" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">FAQ's</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<form action ="update" id = "poolRequest" method="post">
	<div id="container" class="container">
		
		<div id="empInfo" class="empInfo"> 
			<fieldset class="fsempInfo">
				<legend class="legend">Employee Details</legend>
				<section class="readOnlySection">
					<label class="readOnlyLabel">Employee ID : </label> <label><%=empid%></label>
				</section >
				<section class="readOnlySection">
					<label  class="readOnlyLabel">Employee Name : </label> <label><%=firstname%>, <%=lastname%></label>
				</section>
				<section class="readOnlySection">
					<label  class="readOnlyLabel">E-mail : </label> <label><%=email%></label>
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
								 
								<input id=enrolled class=radio <%=(status.equals("Y")?"checked":"")%> type=radio value=Y name=status>
								<label style="font-weight:bold;">Enrolled  </label>

								<input id=withdraw class=radio <%=(status.equals("N")?"checked":"")%> type=radio value=N name=status>
								<label style="font-weight:bold;">Withdraw  </label>
								 
							</section>
						</td>
					</tr>
					<tr>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Start Date :  </label> 
								<input type ="date"  onclick="fnDate(this);" id="startDate" name="startDate" class="startDate" value="<%=startDate%>">
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
								<input type ="text"   id="estStartTimeHour" class="estStartTimeHour" value="00"> :
								<input type ="text"   id="estStartTimeMins" class="estStartTimeMins" value="00"> 
							</section>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<section>
							
								<input onclick="fnCheck('rdnonetime');" id="rdNeed" class=radio <%=(poolType.equals("N")?"checked":"")%> type=radio value="N" name="poolType">
								<label style="font-weight:bold;">Need  </label>
								
								<input onclick="fnCheck('rdnonetime');" id="rdProvide" class=radio <%=(poolType.equals("P")?"checked":"")%> type=radio value="P" name="poolType">
								<label style="font-weight:bold;">Provide   </label>
							
							</section>
						</td>
					</tr>
					<tr>
						<td >
							<section  class="controlSection">
								<label class="controlLabel">Vehicle Type :  </label> 
								<input type ="text"  onclick="fnVehicle(this);" id="vehicleType" name="vehicleType" class="vehicleType" value="<%=vehicleType%>">
							</section>
						</td>
						<td>
							<section  class="controlSection">
								<label class="controlLabel">Capacity :  </label> 
								<input type ="text" id="capacity" name="capacity" class="capacity" value="<%=capacity%>" >
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
	<input type= "hidden" id ="userLocation" name = "userLocation" value = "(<%=location%>)"/>
	<input type="hidden" id = "username" name = "username" value = "<%=empid%>"/>
	</form>
</body>
</html>