package com.SmHome.controller;

import java.util.HashMap;
import java.util.Hashtable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.SmHome.beans.MembersBean;
import com.SmHome.services.LoginService;

@Path("/login")
public class LoginController {

	@Path("/getDetails")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public MembersBean getAuthenticated(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.getDetails(inputDetails);
	  }
	
	@Path("/authenticateHome")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public Hashtable<String,Integer> authenticateHome(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.authenticateHome(inputDetails);
	  }
	
	@Path("/authenticateUser")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String, HashMap<String, String>> authenticateUser(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.authenticateUser(inputDetails);
	  }
	@Path("/authenticateUserWH")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String, HashMap<String, String>> authenticateUserWH(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.authenticateUserWH(inputDetails);
	  }
	
	
	
	@Path("/generateToken")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public Hashtable<String,String> generateToken(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.generateToken(inputDetails);
	  }
	
	@Path("/authenticateToken")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public Hashtable<String,String> authenticateToken(HashMap<String,String> inputDetails) throws Exception {  
		  
		 LoginService loginservice=new LoginService();
		 return loginservice.authenticateToken(inputDetails);
	  }
}
