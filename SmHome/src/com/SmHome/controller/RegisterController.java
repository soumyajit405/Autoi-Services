package com.SmHome.controller;



import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.HomeAdminBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.WHUserBean;
import com.SmHome.services.RegisterService;


@Path("/register")
public class RegisterController {

	   
	  @Path("/home")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,String> registerHome(HomeBean homebean) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.registerHome(homebean);
	  }  
	  
	  @Path("/admin")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String, String> registerAdmin(HomeAdminBean homeadminbean) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.registerAdmin(homeadminbean);
	  }  
	  
	  @Path("/member")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> registerMember(MembersBean membersbean) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.registerMember(membersbean);
	  }  
	  
	  @Path("/registerMemberWH")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> registerMemberWH(WHUserBean whuserbean) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.registerMemberWH(whuserbean);
	  }  
	  
	  @Path("/googleSingInWH")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String, HashMap<String, String>> googleSingInWH(WHUserBean whuserbean) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.googleSingInWH(whuserbean);
	  }  
	  
	  
	  
	  @Path("/personal")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> insertPersonalDetails(PersonalDetailsBean psb) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.insertPersonalDetails(psb);
	  }  
	  
	  @Path("/getAuthenticated")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> getAuthenticated(HashMap<String,String> inputDetails) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.getAuthenticated(inputDetails);
	  }
	  
	  @Path("/user")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,HashMap<String,String>> insertUserDetails(HashMap<String,String> userDetails) throws Exception {  
		  
		 RegisterService registerservice=new RegisterService();
		 return registerservice.insertUserDetails(userDetails);
	  }  
	
}
