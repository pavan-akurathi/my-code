package com.verizon.cab.management.util;

public class MapUtil {

	private List<LatLng> decodePoly(String encoded) {

	    List<LatLng> poly = new ArrayList<LatLng>();
	    int index = 0, len = encoded.length();
	    int lat = 0, lng = 0;

	    while (index < len) {
	        int b, shift = 0, result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lat += dlat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lng += dlng;

	        LatLng p = new LatLng((double) lat / 1E5, (double) lng / 1E5);
	        poly.add(p);
	    }
	    return poly;
	}


	String url = "http://maps.googleapis.com/maps/api/directions/json?origin=19.5217608,-99.2615823&destination=19.531224,-99.248262&sensor=false";

	HttpPost httppost = new HttpPost(url);
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();
	InputStream is = null;
	is = entity.getContent();
	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
	StringBuilder sb = new StringBuilder();
	sb.append(reader.readLine() + "\n");
	String line = "0";
	while ((line = reader.readLine()) != null) {
	    sb.append(line + "\n");
	}
	is.close();
	reader.close();
	String result = sb.toString();
	JSONObject jsonObject = new JSONObject(result);
	JSONArray routeArray = jsonObject.getJSONArray("routes");
	JSONObject routes = routeArray.getJSONObject(0);
	JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
	String encodedString = overviewPolylines.getString("points");
	List<GeoPoint> pointToDraw = decodePoly(encodedString);
	
}
