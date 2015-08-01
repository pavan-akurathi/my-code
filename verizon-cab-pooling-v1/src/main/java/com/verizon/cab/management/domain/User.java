package com.verizon.cab.management.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
 public class User {
  
  @Id
  private String id;
  private String firstName;
  private String lastName; 
  private String phoneNumber;
  private String email;
  private String zipCode;
  private String addressDesc;
  private String[] location;  
  private String startDate;
  private String startTimeHr;
  private String startTimeMin;
  private String poolMode;  
  private String vehicleType;
  private String vehicleCapacity;
  private String isEnrolled;
  private String pickCount; 
  private String providerUserId;
  private UserRoute[] points;
  private String[] availableUserId;
   
  
  
public String getStartDate() {
	return startDate;
}

public void setStartDate(String startDate) {
	this.startDate = startDate;
}

public String getStartTimeHr() {
	return startTimeHr;
}

public void setStartTimeHr(String startTimeHr) {
	this.startTimeHr = startTimeHr;
}

public String getStartTimeMin() {
	return startTimeMin;
}

public void setStartTimeMin(String startTimeMin) {
	this.startTimeMin = startTimeMin;
}

public String getAddressDesc() {
	return addressDesc;
}

public void setAddressDesc(String addressDesc) {
	this.addressDesc = addressDesc;
}

public String[] getAvailableUserId() {
	return availableUserId;
}

public void setAvailableUserId(String[] availableUserId) {
	this.availableUserId = availableUserId;
}

public UserRoute[] getPoints() {
	return points;
}

public void setPoints(UserRoute[] points) {
	this.points = points;
}

public String getProviderUserId() {
	return providerUserId;
}

public void setProviderUserId(String providerUserId) {
	this.providerUserId = providerUserId;
}

public String getVehicleType() {
	return vehicleType;
}

public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getPoolMode() {
	return poolMode;
}

public void setPoolMode(String poolMode) {
	this.poolMode = poolMode;
}

public String getIsEnrolled() {
	return isEnrolled;
}

public void setIsEnrolled(String isEnrolled) {
	this.isEnrolled = isEnrolled;
}

public String getPickCount() {
	return pickCount;
}

public void setPickCount(String pickCount) {
	this.pickCount = pickCount;
}

public String getVehicleCapacity() {
	return vehicleCapacity;
}

public void setVehicleCapacity(String vehicleCapacity) {
	this.vehicleCapacity = vehicleCapacity;
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }  

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public String[] getLocation() {
	return location;
}

public void setLocation(String[] location) {
	this.location = location;
}
  
 }
