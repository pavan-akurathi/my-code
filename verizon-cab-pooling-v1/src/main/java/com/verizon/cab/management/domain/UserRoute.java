package com.verizon.cab.management.domain;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class UserRoute {  
  
  @GeoSpatialIndexed	
  private double[] location;
  private int sequence;  

public double[] getLocation() {
	return location;
}
public void setLocation(double[] location) {
	this.location = location;
}
public int getSequence() {
	return sequence;
}
public void setSequence(int sequence) {
	this.sequence = sequence;
}
  
  
  
 }
