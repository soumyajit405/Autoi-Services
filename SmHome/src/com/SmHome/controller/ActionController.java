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
import com.SmHome.beans.WHDeviceLogBean;
import com.SmHome.services.ActionService;
import com.SmHome.services.DeviceService;
import com.SmHome.services.LoginService;
import com.SmHome.supporting.HashUtil;
import com.SmHome.supporting.MQTTDemo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/action")
public class ActionController {

	@Path("/device")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,String> performAction(DeviceLogInfoBean deviceLogInfoBean) throws Exception {  
		  
		 ActionService as=new ActionService();
		 // int ret=as.performAction(deviceLogInfoBean);
		 
		 String ip,id;
		 DeviceWebSocketServer ds=new DeviceWebSocketServer();
		 ds.sendData(deviceLogInfoBean.getHomeId(),deviceLogInfoBean.getDeviceId(),deviceLogInfoBean.getStatus());
		 /*UserWebSocketServer uss=new UserWebSocketServer();
		 uss.sendData(deviceLogInfoBean.getHomeId(),deviceLogInfoBean.getDeviceId(),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getUserId());
		 */  
		    	/*if(deviceLogInfoBean.getStatus().equalsIgnoreCase("start"))
		    	{
		    		ip=Integer.toString(deviceLogInfoBean.getDeviceId())+"1";
		    	}
		    	else
		    	{
		    		ip=Integer.toString(deviceLogInfoBean.getDeviceId())+"0";
		    	}*/
		/* ip=deviceLogInfoBean.getDeviceId()+deviceLogInfoBean.getStatus();
		    	DeviceWebSocketServer ds=new DeviceWebSocketServer();
				ds.sendData(deviceLogInfoBean.getHomeId(),ip);
		    	*/
		   
		        
		        
		    
		/* DeviceWebSocketServer ds=new DeviceWebSocketServer();
		 ds.sendData("0");
*/
	        

		  HashMap<String,String> response=new HashMap<>();
		  response.put("response",Integer.toString(1));
		 return response;
	  }
	
	@Path("/deviceWH")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	public HashMap<String,String> performActionWH(WHDeviceLogBean deviceLogInfoBean) throws Exception {  
		  
		 ActionService as=new ActionService();
		 MQTTDemo mqdemo=new MQTTDemo();
		/* HashUtil hsu=new HashUtil();
		 hsu.convertToHash(deviceLogInfoBean.getControllerID());*/
		// mqdemo.doDemo(hsu.convertToHash(deviceLogInfoBean.getControllerID()),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());
		// mqdemo.doDemo("/test/light1",deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());
		  int ret=as.performActionWH(deviceLogInfoBean);
		 
		 String ip,id;
		/* MQTTDemo mqdemo=new MQTTDemo();
		 HashUtil hsu=new HashUtil();
		 hsu.convertToHash(deviceLogInfoBean.getControllerID());
		// mqdemo.doDemo(hsu.convertToHash(deviceLogInfoBean.getControllerID()),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());
		 mqdemo.doDemo("/test/light1",deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());*/
		// DeviceWebSocketServer ds=new DeviceWebSocketServer();
		 //ds.sendData(deviceLogInfoBean.getHomeId(),deviceLogInfoBean.getDeviceId(),deviceLogInfoBean.getStatus());
		 /*UserWebSocketServer uss=new UserWebSocketServer();
		 uss.sendData(deviceLogInfoBean.getHomeId(),deviceLogInfoBean.getDeviceId(),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getUserId());
		 */  
		    	/*if(deviceLogInfoBean.getStatus().equalsIgnoreCase("start"))
		    	{
		    		ip=Integer.toString(deviceLogInfoBean.getDeviceId())+"1";
		    	}
		    	else
		    	{
		    		ip=Integer.toString(deviceLogInfoBean.getDeviceId())+"0";
		    	}*/
		/* ip=deviceLogInfoBean.getDeviceId()+deviceLogInfoBean.getStatus();
		    	DeviceWebSocketServer ds=new DeviceWebSocketServer();
				ds.sendData(deviceLogInfoBean.getHomeId(),ip);
		    	*/
		   
		        
		        
		    
		/* DeviceWebSocketServer ds=new DeviceWebSocketServer();
		 ds.sendData("0");
*/
	        

		  HashMap<String,String> response=new HashMap<>();
		  response.put("response",Integer.toString(1));
		 return response;
	  }
	
	@Path("/getMetrics")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	public HashMap<String,Integer> getMetrics(HashMap<String,String> inputDetails) throws Exception {  
		  
		 ActionService as=new ActionService();
		
		// mqdemo.doDemo(hsu.convertToHash(deviceLogInfoBean.getControllerID()),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());
		// mqdemo.doDemo("/test/light1",deviceLogInfoBean.getStatus(),deviceLogInfoBean.getDeviceID());
		 return as.getMetrics(inputDetails);
		 
		
		// return response;
	  }
}
