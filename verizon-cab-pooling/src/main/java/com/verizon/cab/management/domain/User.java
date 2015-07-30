package com.verizon.cab.management.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
 public class User {
  
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String email;
  private String[] location;  
  private String startDate;
  private String isRepeatable;
  private String travelMode;
  private String vehicleCapacity;

  public String getTravelMode() {
	return travelMode;
}

public void setTravelMode(String travelMode) {
	this.travelMode = travelMode;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

public String getStartDate() {
	return startDate;
}

public void setStartDate(String startDate) {
	this.startDate = startDate;
}

public String getIsRepeatable() {
	return isRepeatable;
}

public void setIsRepeatable(String isRepeatable) {
	this.isRepeatable = isRepeatable;
}

  
  
 }
