package com.verizon.cab.management.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.verizon.cab.management.domain.User;
import com.verizon.cab.management.domain.UserRoute;
import com.verizon.cab.management.repositories.mongodb.CabRepository;
import com.verizon.cab.management.util.CommonUtil;

/**
 * Controller for the Cloud Foundry workshop - Spring MVC version.
 * 
 */
@Controller
public class CabController {
	
	private static final Logger logger = LoggerFactory.getLogger(CabController.class);
		
	@Autowired
	private CabRepository cabRepository;
	
	@Autowired (required=false) Cloud cloud;

	/**
	 * Gets basic environment information.  This is the application's
	 * default action.
	 * @param model The model for this action.
	 * @return The path to the view.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
			
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String serverTime = dateFormat.format(date);
		model.addAttribute("serverTime", serverTime);
		
		String port = System.getenv("PORT");
		model.addAttribute("port", port);

		String vcapServices = System.getenv("VCAP_SERVICES");
		model.addAttribute("vcapServices", vcapServices);
		
		if (cloud == null ){
			model.addAttribute("isCloudEnvironment",false);
		} else {
			model.addAttribute("isCloudEnvironment",true);
			model.addAttribute("vcapApplication", cloud.getApplicationInstanceInfo().getProperties());
			logger.info("VCAP_SERVICES [{}] ", vcapServices);
			logger.info("VCAP_APPLICATION [{}] ", System.getenv("VCAP_APPLICATION"));
		}
		
		logger.info("Current date and time = [{}], port = [{}].", serverTime, port);
		
		User update = new User();
		update.setId("2548579");	    
	    update.setEmail("pavan.akurathi@gmail.com");	    
	    update.setFirstName("Pavan");	    
	    update.setLastName("Kumar");	    
	    update.setPhoneNumber("8332898007");
	    update.setZipCode("500050");	    
	    /*update.setPoolMode("P");
	    update.setVehicleType("4 Wheeler");
	    update.setVehicleCapacity("4");
	    update.setIsEnrolled("Y");
	    String plocation[] =  {"78.340129","17.493686"};
	    update.setLocation(plocation);
	    update.setStartDateTime("30-07-2015");	*/    
	    cabRepository.save(update);	    
	    
	    update = new User();
	    update.setId("2548580");	    
	    update.setEmail("satyapavan@gmail.com");	    
	    update.setFirstName("Satya");	    
	    update.setLastName("Pavan");	    
	    update.setPhoneNumber("121313123");
	    update.setZipCode("500050");
	    /* update.setPoolMode("N");
	    String slocation[] =  {"78.360294","17.484168"};
	    update.setLocation(slocation);
	    update.setStartDateTime("07/30/2015");	*/    
	    cabRepository.save(update);	
	    
	    
	    update = new User();
	    update.setId("2548581");	    
	    update.setEmail("surenganti@gmail.com");	    
	    update.setFirstName("Surendra");	    
	    update.setLastName("Ganti");	    
	    update.setPhoneNumber("8332898007");	  
	    update.setZipCode("500050");
	    /*update.setPoolMode("N");
	    String glocation[] =  {"78.533762","17.449104"};
	    update.setLocation(glocation);
	    update.setStartDateTime("07/30/2015");	*/    
	    cabRepository.save(update);	
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String userId, Model model) {
		
		User user = cabRepository.findOne(userId);		
		if(user!=null && !user.getId().equals(""))
		{
			model.addAttribute("empid", userId);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("zipcode", user.getZipCode());
			model.addAttribute("status", user.getIsEnrolled());
			model.addAttribute("startDate", user.getStartDateTime());
			model.addAttribute("poolType", user.getPoolMode());
			model.addAttribute("vehicleType", user.getVehicleType());
			model.addAttribute("capacity", user.getVehicleCapacity());
			String[] geoData = user.getLocation();		
			if(geoData!=null && geoData.length == 2)
			{
				model.addAttribute("location", (geoData[1]+","+geoData[0]));
				// set near users
			}
								
			return "poolingRequest";
		}
		else
			return "index";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("username") String userId,			
			@RequestParam("userLocation") String location,
			@RequestParam("status") String enrolledStatus,
			@RequestParam("poolType") String poolMode,
			@RequestParam("capacity") String vehicleCapacity,
			@RequestParam("startDate") String startDate,
			@RequestParam("vehicleType") String vehicleType,
			Model model) {
		
		User user = cabRepository.findOne(userId);		
		if(user!=null && !user.getId().equals(""))
		{
			logger.info("update Data:: "+location+","+enrolledStatus+","+poolMode+","+vehicleCapacity+","+startDate+","+vehicleType);			
			boolean isLocUpdate = false;
			boolean isStatusUpdate = false;
			boolean isPoolModeUpdate = false;
			boolean isStartDateUpdate = false;
			if(location!=null && location.contains(","))
			{
				location = location.substring(1, location.length()-1);
				// flip lat and long for mongo
				String [] loc = new String[2];				
				loc[0] = location.split(",")[1].trim();
				loc[1] = location.split(",")[0].trim();
				String [] prevLoc = user.getLocation();
				if(!(prevLoc!=null && prevLoc[0].equals(loc[0]) && prevLoc[1].equals(loc[1])))
				{
					user.setLocation(loc);
					isLocUpdate = true;					
				}				
			}
			isStatusUpdate = !(user.getIsEnrolled()!= null && enrolledStatus.equals(user.getIsEnrolled()));
			if(isStatusUpdate)
			{
				user.setIsEnrolled(enrolledStatus);
				// If enrolled - N, then remove the providerUserId and send email
				// remove from availableUserId
				// If enrolled - Y, 
			}
			isPoolModeUpdate = !(user.getPoolMode()!= null && poolMode.equals(user.getPoolMode()));
			if(isPoolModeUpdate)
			{
				user.setPoolMode(poolMode);				
				if(poolMode.equals("P") && vehicleCapacity!=null && !vehicleCapacity.trim().equals(""))
					user.setVehicleCapacity(vehicleCapacity);
				if(poolMode.equals("P") && vehicleType!=null && !vehicleType.trim().equals(""))
					user.setVehicleType(vehicleType);
				// If poolMode changed from P to N, then remove the providerUserId and send email
				// remove from availableUserId
				// If poolMode changed from N to P, then remove the providerUserId of N and send email to P user
				// Take each routepoint and check if any users with mode N and with no providerUserId fall within range
				// if any found add P user in availableUserId list if pickCount < vehicleCapacity
				// Send email to P with list of N users
				// Send email to N with list of P users
			}	
			isStartDateUpdate = !(user.getStartDateTime()!= null && startDate.equals(user.getStartDateTime()));
			if(isStartDateUpdate)
			{
				user.setStartDateTime(startDate);				
			}
			if(poolMode!=null && poolMode.equals("P") && isLocUpdate)
			{					
				UserRoute[] routepoints = CommonUtil.getRoutePoints(user.getLocation());
				if(routepoints!=null)
					user.setPoints(routepoints);
				user.setPickCount(null); // set pick count to null
				// clear matching N users providerUserId and send email to them that his provider changed location
				// clear matching N users availableUserId
				// Take each routepoint and check if any users with mode N and with no providerUserId fall within range
				// if any found add P user in availableUserId list if pickCount < vehicleCapacity
				// Send email to P with list of N users
				// Send email to N with list of P users
			}
			else if(poolMode!=null && poolMode.equals("N") && isLocUpdate)
			{
				
			}			
			if(isStatusUpdate || isLocUpdate || isPoolModeUpdate || isStartDateUpdate)
			{				
				cabRepository.save(user);	
				model.addAttribute("message", "Your Car Pool request is registered");
			}
			else
			{
				model.addAttribute("message", "Your Car Pool request failed to register");
				
			}
			model.addAttribute("empid", userId);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("zipcode", user.getZipCode());
			model.addAttribute("status", user.getIsEnrolled());
			model.addAttribute("startDate", user.getStartDateTime());
			model.addAttribute("poolType", user.getPoolMode());
			model.addAttribute("vehicleType", user.getVehicleType());
			model.addAttribute("capacity", user.getVehicleCapacity());
			String[] geoData = user.getLocation();		
			if(geoData!=null && geoData.length == 2)
			{
				model.addAttribute("location", (geoData[1]+","+geoData[0]));
				// set near users
			}			
			
			return "poolingRequest";
		}
		else
			return "index";
	}
	
}
