package com.SmHome.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.RoomBean;
import com.SmHome.services.ActionService;
import com.SmHome.services.DeviceService;
import com.SmHome.services.LoginService;
import com.SmHome.services.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/room")
public class RoomController {

	@Path("/insert")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> insertRoom(RoomBean roombean) throws Exception {  
		  
		 RoomService rs=new RoomService();
		  int ret=rs.insertRoom(roombean);
		

		  HashMap<String,Integer> response=new HashMap<>();
		  response.put("key",ret);
		 return response;
	  }
	
	
	@Path("/update")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> updateRoom(RoomBean roombean) throws Exception {  
		  
		 RoomService rs=new RoomService();
		  int ret=rs.updateRoom(roombean);
		

		  HashMap<String,Integer> response=new HashMap<>();
		  response.put("key",ret);
		 return response;
	  }
	
	@Path("/updateAttr")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> updateRoomAttr(RoomBean roombean) throws Exception {  
		  
		 RoomService rs=new RoomService();
		 int ret=   rs.updateRoomAttr(roombean);
		
		 HashMap<String,Integer> response=new HashMap<>();
		  response.put("key",ret);
		 
		return response;
	  }
	
	
}
