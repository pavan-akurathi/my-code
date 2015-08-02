package com.verizon.cab.management.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.verizon.cab.management.util.EmailTemplate;

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
	    update.setEmail("pavan.akurathi@gmail.com");	    
	    update.setFirstName("Pavan");	    
	    update.setLastName("Kumar");	    
	    update.setPhoneNumber("8332898007");
	    update.setZipCode("500050");	    
	    update.setPoolMode("P");
	    update.setVehicleType("4 Wheeler");
	    update.setVehicleCapacity("4");
	    update.setIsEnrolled("Y");
	    String plocation[] =  {"78.340129","17.493686"};
	    update.setLocation(plocation);
	    update.setStartDateTime("30-07-2015");	   
	    cabRepository.save(update);	    
	    
	    update = new User();
	    update.setId("2548580");	    
	    update.setEmail("satyapavan@gmail.com");	    
	    update.setFirstName("Satya");	    
	    update.setLastName("Pavan");	    
	    update.setPhoneNumber("121313123");
	    update.setZipCode("500050");
	     update.setPoolMode("N");
	    String slocation[] =  {"78.360294","17.484168"};
	    update.setLocation(slocation);
	    update.setStartDateTime("07/30/2015");	   
	    cabRepository.save(update);	
	    
	    
	    update = new User();
	    update.setId("2548581");	    
	    update.setEmail("surenganti@gmail.com");	    
	    update.setFirstName("Surendra");	    
	    update.setLastName("Ganti");	    
	    update.setPhoneNumber("8332898007");	  
	    update.setZipCode("500050");
	    update.setPoolMode("N");
	    String glocation[] =  {"78.533762","17.449104"};
	    update.setLocation(glocation);
	    update.setStartDateTime("07/30/2015");	    
	    cabRepository.save(update);	*/
		
		return "index";
	}
	
	@RequestMapping(value = "/createNewUser", method = RequestMethod.GET)
	public String createNewUser(Model model) {
		return "createNewUser";
	}
	
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String faq(@RequestParam("username") String userId, Model model) {
		model.addAttribute("username", userId);
		return "faq";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("employeeid") String employeeid,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("phnumber") String phnumber,
			@RequestParam("email") String email,
			@RequestParam("zipcode") String zipcode,			
			Model model) {
		
		User update = new User();
		update.setId(employeeid);	    
	    update.setEmail(email);	    
	    update.setFirstName(firstname);	    
	    update.setLastName(lastname);	    
	    update.setPhoneNumber(phnumber);
	    update.setZipCode(zipcode);
	    cabRepository.save(update);	    
	    model.addAttribute("message", "Registration completed.");
		return "index";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
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
			Date startDate = user.getStartDate();
			if(startDate!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
				String sd = sdf.format(startDate);
				model.addAttribute("startDate", sd.split("~")[0]);
				model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
				model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
			}
			model.addAttribute("addressDesc", user.getAddressDesc());
			model.addAttribute("poolType", user.getPoolMode());
			model.addAttribute("vehicleType", user.getVehicleType());
			model.addAttribute("capacity", user.getVehicleCapacity());
			if(user.getPoolMode()!=null && user.getPoolMode().equals("P"))
			{	
				List<User> tUsers = cabRepository.findByProviderUserId(userId);
				if(tUsers!=null)
				{
					StringBuilder takers = new StringBuilder();
					StringBuilder currentPool = new StringBuilder();
					takers.append("[");
					for(User u: tUsers)
					{
						if(u.getIsEnrolled().equals("Y") && u.getPoolMode().equals("N") && u.getLocation()!=null && u.getLocation().length == 2)
						{
							
							if(takers.toString().length() > 1)
								takers.append(",");
							takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
							.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
							
							if(currentPool.toString().length()>0)
								currentPool.append(",");
							currentPool.append(u.getFirstName()).append(" ").append(u.getLastName()).append(" | ").append(u.getPhoneNumber()).append(" | ").append(u.getEmail());
						}
												
					}
					takers.append("]");				
					model.addAttribute("takers", takers.toString());
					model.addAttribute("currentPool",currentPool.toString());
				}
				
			}
			else if(user.getPoolMode()!=null && user.getPoolMode().equals("N"))
			{
				if(user.getProviderUserId()!=null)
				{
					StringBuilder providers = new StringBuilder();					
					providers.append("[");
					User u = cabRepository.findOne(user.getProviderUserId());
					
						if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
						{		
								providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
								.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");	
								String currentPool = u.getFirstName()+ " "+u.getLastName()+" | "+u.getPhoneNumber()+" | "+u.getEmail();
								model.addAttribute("currentPool",currentPool);
						}
					
					providers.append("]"); 
					model.addAttribute("providers", providers.toString());
					
				}
			}
			String[] geoData = user.getLocation();		
			if(geoData!=null && geoData.length == 2)
			{
				model.addAttribute("location", (geoData[1]+","+geoData[0]));
				// set near users
			}
								
			return "poolingRequest";
		}
		else
		{
			model.addAttribute("message","Login failed. If you are a new user please register first.");
			return "index";
		}
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(@RequestParam("username") String userId, Model model) {
		
		if(userId!=null)
		{
			List<User> users = cabRepository.findAll();	
			StringBuilder providers = new StringBuilder();
			StringBuilder takers = new StringBuilder();
			providers.append("["); takers.append("[");
			for(User u: users)
			{
				if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
				{
					if(u.getPoolMode().equals("P"))
					{
						if(providers.toString().length() > 1)
							providers.append(",");
						providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
						.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
					}
					else
					{
						if(takers.toString().length() > 1)
							takers.append(",");
						takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
						.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
					}
				}
			}
			providers.append("]"); takers.append("]");
			model.addAttribute("providers", providers.toString());
			model.addAttribute("takers", takers.toString());
			model.addAttribute("username", userId);
			return "reports";
		}
		else
		{
			model.addAttribute("message", "Session expired!! please login again");
			return "index";
		}
	}
	
	@RequestMapping(value = "/submitRequest", method = RequestMethod.POST)
	public String submitRequest(@RequestParam("userId") String userId,
			@RequestParam("avlVehicleChk") String avlVehicleChk,
			Model model) {
		
		if(userId!=null)
		{
			User user = cabRepository.findOne(userId);
			String prevProviderUserId = user.getProviderUserId();
			if(prevProviderUserId!=null && !prevProviderUserId.equals(avlVehicleChk))
			{
				User prevProvider = cabRepository.findOne(user.getProviderUserId());
				prevProvider.setPickCount(String.valueOf(Integer.parseInt(prevProvider.getPickCount()!=null?prevProvider.getPickCount():"1")-1));
				cabRepository.save(prevProvider);
				// send email to old provider that user de-tagged from his pool
				String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, prevProvider.getFirstName())
						.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
						.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
						.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
				CommonUtil.sendEmail(prevProvider.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);
				// send email to the user that he is de-tagged from his current pool
				emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, user.getFirstName())
						.replace(EmailTemplate.FIRST_NAME, prevProvider.getFirstName()).replace(EmailTemplate.LAST_NAME, prevProvider.getLastName())
						.replace(EmailTemplate.MOBILE, prevProvider.getPhoneNumber()).replace(EmailTemplate.EMAIL, prevProvider.getEmail())
						.replace(EmailTemplate.ADDRESS, prevProvider.getAddressDesc());
				CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
			}
			user.setProviderUserId(avlVehicleChk);
			cabRepository.save(user);
			model.addAttribute("empid", userId);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("zipcode", user.getZipCode());
			model.addAttribute("status", user.getIsEnrolled());
			Date startDate = user.getStartDate();
			if(startDate!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
				String sd = sdf.format(startDate);
				model.addAttribute("startDate", sd.split("~")[0]);
				model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
				model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
			}			
			model.addAttribute("addressDesc", user.getAddressDesc());
			model.addAttribute("poolType", user.getPoolMode());
			model.addAttribute("vehicleType", user.getVehicleType());
			model.addAttribute("capacity", user.getVehicleCapacity());
			if(user.getProviderUserId()!=null)
			{				
				User providerUser = cabRepository.findOne(user.getProviderUserId());
				if(prevProviderUserId!=null && !prevProviderUserId.equals(avlVehicleChk))
				{
					providerUser.setPickCount(String.valueOf(Integer.parseInt(providerUser.getPickCount()!=null?providerUser.getPickCount():"0")+1));
					cabRepository.save(providerUser);
					// send email to new provider that user tagged to his pool
					String emailBody = EmailTemplate.TEXT_CAR_POOL_ENROLLED_PROVIDER.replace(EmailTemplate.RECEIPIENT, providerUser.getFirstName())
							.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
							.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
							.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
					CommonUtil.sendEmail(providerUser.getEmail(),EmailTemplate.SUB_CAR_POOL_ENROLLED_PROVIDER, emailBody);
					// send email to the user that he is de-tagged from his current pool
					emailBody = EmailTemplate.TEXT_CAR_POOL_ENROLLED_USER.replace(EmailTemplate.RECEIPIENT, user.getFirstName())
							.replace(EmailTemplate.FIRST_NAME, providerUser.getFirstName()).replace(EmailTemplate.LAST_NAME, providerUser.getLastName())
							.replace(EmailTemplate.MOBILE, providerUser.getPhoneNumber()).replace(EmailTemplate.EMAIL, providerUser.getEmail())
							.replace(EmailTemplate.ADDRESS, providerUser.getAddressDesc());
					CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_ENROLLED_USER, emailBody);
				}
				String currentPool = providerUser.getFirstName()+ " "+providerUser.getLastName()+" | "+providerUser.getPhoneNumber()+" | "+providerUser.getEmail();
				model.addAttribute("currentPool",currentPool);	
			}
			String[] geoData = user.getLocation();		
			if(geoData!=null && geoData.length == 2)
				model.addAttribute("location", (geoData[1]+","+geoData[0]));								
			return "poolingRequest";			
		}
		else
		{
			model.addAttribute("message", "Session expired!! please login again");
			return "index";
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("username") String userId,			
			@RequestParam("userLocation") String location,
			@RequestParam("status") String enrolledStatus,
			@RequestParam("poolType") String poolMode,
			@RequestParam("capacity") String vehicleCapacity,
			@RequestParam("startDate") String startDate,
			@RequestParam("startTimeHr") String startTimeHr,			
			@RequestParam("startTimeMin") String startTimeMin,
			@RequestParam("addressDesc") String addressDesc,
			@RequestParam("vehicleType") String vehicleType,
			Model model) {
		
		User user = cabRepository.findOne(userId);		
		if(user!=null && !user.getId().equals(""))
		{
			user.setAddressDesc(addressDesc);
			if(user.getPickCount()==null && poolMode.equals("P"))
				user.setPickCount("0");
			logger.info("update Data:: "+location+","+enrolledStatus+","+poolMode+","+vehicleCapacity+","+startDate+","+startTimeHr+","+startTimeMin+","+addressDesc+","+vehicleType);			
			boolean isLocUpdate = false;
			boolean isStatusUpdate = false;
			boolean isPoolModeUpdate = false;
			boolean isStartDateUpdate = false;
			boolean isSentEmail = false;
			isStatusUpdate = !(user.getIsEnrolled()!= null && enrolledStatus.equals(user.getIsEnrolled()));
			isPoolModeUpdate = !(user.getPoolMode()!= null && poolMode.equals(user.getPoolMode()));
			if(isStatusUpdate)
			{
				if(user.getIsEnrolled()!= null && user.getIsEnrolled().equals("N"))
				{					
						if(user.getPoolMode().equals("N"))
						{
							if(user.getProviderUserId()!=null)
							{
								User pUser = cabRepository.findOne(user.getProviderUserId());
								pUser.setPickCount(String.valueOf(Integer.parseInt(pUser.getPickCount()!=null?pUser.getPickCount():"1")-1));
								cabRepository.save(pUser);
								user.setProviderUserId(null);										
								String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, pUser.getFirstName())
										.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
										.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
										.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
								CommonUtil.sendEmail(pUser.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);								
							}							
							isSentEmail = true;
						}
						else
						{
							List<User> pUsers = cabRepository.findByProviderUserId(userId);
							for(User u:pUsers)
							{
								u.setProviderUserId(null);
								cabRepository.save(u);
								String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, u.getFirstName())
										.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
										.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
										.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
								CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
								isSentEmail = true;
							}
							user.setPickCount("0");
						}					
				}								
			}
			
			if(isPoolModeUpdate)
			{
					if(user.getPoolMode().equals("N"))
						{
							if(!isSentEmail)
							{
								if(user.getProviderUserId()!=null)
								{
									User pUser = cabRepository.findOne(user.getProviderUserId());
									pUser.setPickCount(String.valueOf(Integer.parseInt(pUser.getPickCount()!=null?pUser.getPickCount():"1")-1));
									cabRepository.save(pUser);
									user.setProviderUserId(null);								
									String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, pUser.getFirstName())
											.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
											.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
											.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
									CommonUtil.sendEmail(pUser.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);
									isSentEmail = true;
								}							
							}
							user.setPickCount("0");
						}
						else
						{
							if(!isSentEmail)
							{
								List<User> pUsers = cabRepository.findByProviderUserId(userId);
								for(User u:pUsers)
								{
									u.setProviderUserId(null);
									cabRepository.save(u);
									String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, u.getFirstName())
											.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
											.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
											.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
									CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
									isSentEmail = true;
								}
							}
							user.setPickCount(null);
						}						
			}			
			if(location!=null && location.contains(","))
			{
				if(location.contains("("))
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
			
			Date stDate = user.getStartDate();
			String sDate=null, sHr=null, sMin=null;
			if(stDate!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
				String sd = sdf.format(startDate);
				sDate = sd.split("~")[0];
				sHr = sd.split("~")[1].split(":")[0];
				sMin = sd.split("~")[1].split(":")[1];
			}
		
			isStartDateUpdate = !(sDate!= null && startDate.equals(sDate) && sHr!= null && startTimeHr.equals(sHr)
					&& sMin!= null && startTimeMin.equals(sMin));
			if(isStartDateUpdate)
			{
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					String sttDate = startDate+" "+sHr+":"+sMin;
					user.setStartDate(sdf.parse(sttDate));
				}
				catch(Exception e)
				{
					logger.error("issue parsing date field");
					e.printStackTrace();
				}
			}
			
			if(poolMode!=null && poolMode.equals("P"))
			{	
				List<User> tUsers = cabRepository.findByProviderUserId(userId);
			
				if(isLocUpdate)
				{
					UserRoute[] routepoints = CommonUtil.getRoutePoints(user.getLocation());
					if(routepoints!=null)
						user.setPoints(routepoints);
					
					
					/*List<User> nUsers = cabRepository.findByPoolMode("N");
					List<User> ntUsers = new ArrayList<User>();				
					if(nUsers!=null)
					for(User u:nUsers)
					{
						if(u.getProviderUserId()==null)
						{
							List<User> nearUsers = cabRepository.getNearbyHave(u.getLocation(),"");
							
						}
					}
					user.setPickCount();*/
				}
				
				// set it to some attribute after all restructure
				if(tUsers!=null)
				{
					StringBuilder takers = new StringBuilder();
					StringBuilder currentPool = new StringBuilder();
					takers.append("[");
					for(User u: tUsers)
					{
						if(u.getIsEnrolled().equals("Y") && u.getPoolMode().equals("N") && u.getLocation()!=null && u.getLocation().length == 2)
						{							
								if(takers.toString().length() > 1)
									takers.append(",");
								takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
								.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
						
							if(currentPool.toString().length()>0)
								currentPool.append(",");
							currentPool.append(u.getFirstName()).append(" ").append(u.getLastName()).append(" | ").append(u.getPhoneNumber()).append(" | ").append(u.getEmail());
						}
						
					}
					takers.append("]");				
					model.addAttribute("takers", takers.toString());
					model.addAttribute("currentPool",currentPool.toString());
				}
				
			}
			else if(poolMode!=null && poolMode.equals("N"))
			{
				if(user.getProviderUserId()!=null)
				{
					StringBuilder providers = new StringBuilder();					
					providers.append("[");
					User u = cabRepository.findOne(user.getProviderUserId());
					
						if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
						{		
								providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
								.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
								String currentPool = u.getFirstName()+ " "+u.getLastName()+" | "+u.getPhoneNumber()+" | "+u.getEmail();
								model.addAttribute("currentPool",currentPool);
						}					
					providers.append("]"); 
					model.addAttribute("providers", providers.toString());
					
				}
			}
			
			user.setIsEnrolled(enrolledStatus);
			user.setPoolMode(poolMode);				
			if(poolMode.equals("P") && vehicleCapacity!=null && !vehicleCapacity.trim().equals(""))
				user.setVehicleCapacity(vehicleCapacity);
			if(poolMode.equals("P") && vehicleType!=null && !vehicleType.trim().equals(""))
				user.setVehicleType(vehicleType);
						
			cabRepository.save(user);
			
			model.addAttribute("empid", userId);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("lastname", user.getLastName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("zipcode", user.getZipCode());
			model.addAttribute("status", user.getIsEnrolled());
			Date attStDate = user.getStartDate();
			if(attStDate!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
				String sd = sdf.format(attStDate);
				model.addAttribute("startDate", sd.split("~")[0]);
				model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
				model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
			}
			model.addAttribute("addressDesc", user.getAddressDesc());
			model.addAttribute("poolType", user.getPoolMode());
			model.addAttribute("vehicleType", user.getVehicleType());
			model.addAttribute("capacity", user.getVehicleCapacity());
			
			String[] geoData = user.getLocation();		
			if(geoData!=null && geoData.length == 2)
			{
				model.addAttribute("location", (geoData[1]+","+geoData[0]));				
			}			
			if(user.getPoolMode().equals("P"))
				return "poolingRequest";
			else
			{
				List<User> providerList = new ArrayList<User>();
				List<User> users = cabRepository.findAll();	// to change
				StringBuilder providers = new StringBuilder();				
				providers.append("[");
				for(User u: users)
				{
					if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
					{
						if(u.getPoolMode().equals("P"))
						{
							if(providers.toString().length() > 1)
								providers.append(",");
							providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
							.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
							providerList.add(u);
						}						
					}
				}
				providers.append("]");
				model.addAttribute("providers", providers.toString());
				model.addAttribute("providerList",providerList);
				return "availableVehicleDetails";
			}
		}
		else
		{
			model.addAttribute("message", "Session expired!! please login again");
			return "index";
		}
	}
	
}
