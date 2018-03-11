package com.SmHome.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.SystemDeviceBean;
import com.SmHome.services.DeviceService;
import com.SmHome.services.LoginService;

@Path("/device")
public class DeviceController {

	@Path("/register")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> registerDevice(DeviceBean deviceBean) throws Exception {  
		  
		 DeviceService dds=new DeviceService();
		  int ret=dds.registerDevice(deviceBean);
		  HashMap<String,Integer> response=new HashMap<>();
		  response.put("response",ret);
		 return response;
	  }
	
	
	@Path("/getDeviceStatus")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public SystemDeviceBean getDeviceStatus(HashMap<String,String> inputDetails) throws Exception {  
		  
		 DeviceService dds=new DeviceService();
		 
		 return  dds.getDeviceStatus(inputDetails.get("homeId"));
	  }
}
