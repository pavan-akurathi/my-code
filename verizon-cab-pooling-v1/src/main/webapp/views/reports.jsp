<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- saved from url=(0014)about:internet -->
<!DOCTYPE html>
<html class="vz-theme" lang="en"> 
<head>
<title>Car Pooling System</title>
<link href="resources/jquery-contextmenu.css"
	rel="stylesheet" type="text/css">
<link href="resources/jquery.jscrollpane.css"
	rel="stylesheet" type="text/css">
<link href="resources/Default.htm.style.min.css"
	rel="stylesheet" type="text/css">
<link href="resources/custom.style.css"
	rel="stylesheet" type="text/css">
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.1.min.js"></script>

<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

</head>
<%
String username = request.getAttribute("username")!=null?(String)request.getAttribute("username"):"";
String providers = request.getAttribute("providers")!=null?(String)request.getAttribute("providers"):"[]";
String takers = request.getAttribute("takers")!=null?(String)request.getAttribute("takers"):"[]";
String mappedusers = request.getAttribute("mappedusers")!=null?(String)request.getAttribute("mappedusers"):"[]";

%>
<body class="isIE" style="cursor: default;" >
<div class="header">
		<div class="layout">
			<div class="VZlogo">
				<IMG title="Verizon" alt="Verizon" src="/resources/VerizonLogo.png"
					width="110%" height="110%">
			</div>
			<div class="app-name">Car Pooling System</div>
			<div class="app-support">&nbsp;<BR>
			Email: <A href="mailto:VDSI-CarPoolingSystem@one.verizon.com">vdsi.carpooling@gmail.com</A><BR>
			&nbsp;<BR>
			</div>
		</div>
	</div>

	<div class="mast1" style = "font-weight: bold; height: 40px; color: white; overflow: hidden; font-size: 14px; display: block; background-color: rgb(231, 28, 36); margin: auto;">&nbsp;&nbsp;
		<table style = "margin-top: 5px; float: left;">
			<tr>
				<td>
					<a href="login?username=<%=username%>" id="poolingRequest" title="poolingRequest" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Pooling Request</a>&nbsp;&nbsp;
				</td>				
				<td>
					<a href="#" id="reports" title="reports" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Report</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="faq?username=<%=username%>" id="faq" title="FAQ's" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">FAQ's</a>
				</td>
				<td>
					<a href="/" id="logout" title="Log Out" style = "padding-right : 5px; font-weight: bold; border-right : 2px solid white; color : white; font-size : 14px;">Log Out</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="map" style="width: 99%; height: 80%;"></div>
	  <script type="text/javascript">
    var providerLocations = <%=providers%>; 
    /*	[
      ['Surendra Ganti', 17.439037,78.381209, "P"],
      ['Pavan Satya', 17.431234,78.383459, "P"],
      ['Pavan Akruthi', 17.456778,78.38456, "P"],
      ['Vijaya Bhaskar', 17.475207,78.3827053209, "P"]
    ];*/
    
    var utilizerLocations = <%=takers%>;
    	/*[
                             ['Potnuri Hari', 17.4931265737,78.3457689, "U"],
                             //['Ramana', 17.76842337,78.96543309, "U"],
                             ['Arpitha', 17.4931265737,78.34806457, "U"]
                             //['Jai Lakshmi Narasimha Swamy', 17.456737677,79.3854735679, "U"]
                           ];*/

     var mappedUserLocs = <%=mappedusers%>;

      var map = new google.maps.Map(document.getElementById('map'), {
                             zoom: 12,
                             mapTypeId: google.maps.MapTypeId.ROADMAP
                           });

                           var infowindow = new google.maps.InfoWindow();

                           var providerMarker, reciverMarker, mappedUserMarker, i;
                           var markers = new Array();

                           if(utilizerLocations.length > 0){
                           	 for (i = 0; i < utilizerLocations.length; i++) {
                          	    	reciverMarker = new google.maps.Marker({
                          	          position: new google.maps.LatLng(utilizerLocations[i][1], utilizerLocations[i][2]),
                          	          map: map,
                          	     	  //animation : google.maps.Animation.BOUNCE
                          	          icon: "resources/marker1.png"
                          	        });

                          	        markers.push(reciverMarker);

                          	        google.maps.event.addListener(reciverMarker, 'click', (function(reciverMarker, i) {
                          	          return function() {
                          	            infowindow.setContent(utilizerLocations[i][0]);
                          	            infowindow.open(map, reciverMarker);
                          	          }
                          	        })(reciverMarker, i));
                          	      }
                           }
                           	
                           if(providerLocations.length > 0){
                           	for (i = 0; i < providerLocations.length; i++) {
                          	      providerMarker = new google.maps.Marker({
                          	        position: new google.maps.LatLng(providerLocations[i][1], providerLocations[i][2]),
                          	        map: map,
                          	        //animation : google.maps.Animation.BOUNCE
                          	        icon: "resources/marker2.png"
                          	      });

                          	      markers.push(providerMarker);

                          	      google.maps.event.addListener(providerMarker, 'click', (function(providerMarker, i) {
                          	        return function() {
                          	          infowindow.setContent(providerLocations[i][0]);
                          	          infowindow.open(map, providerMarker);
                          	        }
                          	      })(providerMarker, i));
                          	    }
                           }
                           
                           if(mappedUserLocs.length > 0){
                          	 for (i = 0; i < mappedUserLocs.length; i++) {
                          			mappedUserMarker = new google.maps.Marker({
                         	          position: new google.maps.LatLng(mappedUserLocs[i][1], mappedUserLocs[i][2]),
                         	          map: map,
                         	     	  //animation : google.maps.Animation.BOUNCE
                         	          icon: "resources/marker3.png"
                         	        });

                         	        markers.push(mappedUserMarker);

                         	        google.maps.event.addListener(mappedUserMarker, 'click', (function(mappedUserMarker, i) {
                         	          return function() {
                         	            infowindow.setContent(mappedUserLocs[i][0]);
                         	            infowindow.open(map, mappedUserMarker);
                         	          }
                         	        })(mappedUserMarker, i));
                         	      }
                          }
                           
                           function AutoCenter() {
                             //  Create a new viewpoint bound
                             var bounds = new google.maps.LatLngBounds();
                             //  Go through each...
                             $.each(markers, function (index, reciverMarker) {
                             bounds.extend(reciverMarker.position);
                             });
                             //  Fit these bounds to the map
                             map.fitBounds(bounds);
                           }
                           AutoCenter();

  </script>
  <div class="layout">   
       <div class="copyrights"><b>&copy; 2015 Designed by Team Hackers (Surendra Ganti, Pavan Akurathi, Pavan Satya) </b><a href="#">FAQ</a> |  <a href="#">Feedback</a></div>
	</div>
</body>
</html>