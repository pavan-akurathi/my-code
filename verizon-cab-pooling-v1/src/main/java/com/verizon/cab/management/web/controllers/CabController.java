package com.verizon.cab.management.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import com.verizon.cab.management.repositories.mongodb.CabRepository;

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
		
		/*User update = new User();
		update.setId("2548579");
	    update.setAddress("Madinaguda Home");
	    update.setEmail("pavan.akurathi@gmail.com");	    
	    update.setFirstName("Pavan");	    
	    update.setLastName("Kumar");	    
	    update.setPhoneNumber("8332898007");
	    update.setIsRepeatable("Y");
	    update.setTravelMode("have");
	    update.setVehicleCapacity("4");
	    String plocation[] =  {"78.340129","17.493686"};
	    update.setLocation(plocation);
	    update.setStartDate("07/30/2015");	    
	    cabRepository.save(update);	    
	    
	    update = new User();
	    update.setId("2548580");
	    update.setAddress("Hafeezpet Home");
	    update.setEmail("satyapavan@gmail.com");	    
	    update.setFirstName("Satya");	    
	    update.setLastName("Pavan");	    
	    update.setPhoneNumber("121313123");
	    update.setIsRepeatable("Y");
	    update.setTravelMode("need");
	    String slocation[] =  {"78.360294","17.484168"};
	    update.setLocation(slocation);
	    update.setStartDate("07/30/2015");	    
	    cabRepository.save(update);	
	    
	    
	    update = new User();
	    update.setId("2548581");
	    update.setAddress("Malkajgiri Home");
	    update.setEmail("surenganti@gmail.com");	    
	    update.setFirstName("Surendra");	    
	    update.setLastName("Ganti");	    
	    update.setPhoneNumber("8332898007");
	    update.setIsRepeatable("Y");
	    update.setTravelMode("need");
	    String glocation[] =  {"78.533762","17.449104"};
	    update.setLocation(glocation);
	    update.setStartDate("07/30/2015");	    
	    cabRepository.save(update);	
	    */	
		
		double lat1 = 17.438891;
		   double lon1 = 78.381100;
		   double lat2 = 17.493686;
		   double lon2 = 78.340129;

		   String url = "http://maps.googleapis.com/maps/api/directions/json?";

		   List<NameValuePair> params = new LinkedList<NameValuePair>();
		   params.add(new BasicNameValuePair("origin", lat1 + "," + lon1));
		   params.add(new BasicNameValuePair("destination", lat2 + "," + lon2));		   

		   String paramString = URLEncodedUtils.format(params, "utf-8");
		   url += paramString;			 
		   HttpClient httpclient = new DefaultHttpClient();
		   try{
		   HttpResponse response = httpclient.execute(new HttpGet(url));
		   HttpEntity entity = response.getEntity();
		   String responseString = EntityUtils.toString(entity, "UTF-8");
		   logger.info("response:: "+responseString);
		   }catch(Exception e){
			   logger.info("exception in httpget");
		   }
		
		return "index";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("username") String userId,			
			@RequestParam("loc") String location,
			@RequestParam("address") String address,
			@RequestParam("tm") String travelMode,
			@RequestParam("vc") String vehicleCapacity,
			@RequestParam("startDate") String startDate,
			@RequestParam("isRepeatable") String isRepeatable,
			Model model) {
		
		User user = cabRepository.findOne(userId);		
		if(user!=null && !user.getId().equals(""))
		{
			user.setAddress(address);
			user.setLocation(location.split("|"));
			user.setTravelMode(travelMode);
			user.setVehicleCapacity(vehicleCapacity);
			user.setStartDate(startDate);
			user.setIsRepeatable(isRepeatable);
			cabRepository.save(user);	
			
			if(travelMode!=null && travelMode.equals("need"))
			{
				//78.381100, 17.438891
				// call the path finding algo and send email
				
				
				/*Vcapenv vcapenv = new Vcapenv();
				String sendgrid_username = vcapenv.SENDGRID_USERNAME();
				String sendgrid_password = vcapenv.SENDGRID_PASSWORD();

				SendGrid sendgrid = new SendGrid(sendgrid_username, sendgrid_password);				
				Email email = new Email();				
				email.addTo("pavan.akurathi@gmail.com");
				email.setFrom("pavan.akurathi@gmail.com");
				email.setSubject("Hello World");
				email.setText("My first email through SendGrid");
				try{
					sendgrid.send(email);
				}catch(Exception e)
				{
					System.out.println("exception in sending email:");
					e.printStackTrace();
				}*/
			}
			
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("location", user.getLocation());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("travelMode", user.getTravelMode());
			model.addAttribute("vehicleCapacity", user.getVehicleCapacity());
			model.addAttribute("startDate", user.getStartDate());
			model.addAttribute("isRepeatable", user.getIsRepeatable());		
			return "main";
		}
		else
			return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("username") String userId, Model model) {
		
		User user = cabRepository.findOne(userId);		
		if(user!=null && !user.getId().equals(""))
		{
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("location", user.getLocation());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("travelMode", user.getTravelMode());
			model.addAttribute("vehicleCapacity", user.getVehicleCapacity());
			model.addAttribute("startDate", user.getStartDate());
			model.addAttribute("isRepeatable", user.getIsRepeatable());		
			return "main";
		}
		else
			return "index";
	}
}
