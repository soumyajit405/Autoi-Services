package com.SmHome.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.WHControllerBean;
import com.SmHome.beans.WHScheduler;
import com.SmHome.dao.ActionDAO;
import com.SmHome.dao.DeviceDAO;
import com.SmHome.dao.LoginDAO;
import com.SmHome.dao.RegisterDAO;
import com.SmHome.dao.WHControllerDAO;

public class WHControllerService {

	public HashMap<String, HashMap<String, String>> verifyController(HashMap<String,String> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.verifyController(inputDetails);
	}
	
	public int updateController(HashMap<String,HashMap<String,String>> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.updateController(inputDetails);
	}
	
	public int scheduleDevice(HashMap<String,String> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.scheduleDevice(inputDetails);
	}
	
	/*public int disableScheduledDevice(HashMap<String,String> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.disableScheduledDevice(inputDetails);
	}
	
	public int updateSchedule(HashMap<String,String> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.updateSchedule(inputDetails);
	}*/
	
	public ArrayList<WHScheduler> viewScheduledDevices(HashMap<String,String> inputDetails) throws Exception
	{
		WHControllerDAO wdo=new WHControllerDAO();
		return wdo.viewScheduledDevices(inputDetails);
	}
	
	
	
	
}
