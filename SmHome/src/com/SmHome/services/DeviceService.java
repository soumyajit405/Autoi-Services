package com.SmHome.services;

import java.util.HashMap;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.SystemDeviceBean;
import com.SmHome.dao.DeviceDAO;
import com.SmHome.dao.LoginDAO;
import com.SmHome.dao.RegisterDAO;

public class DeviceService {

	public int registerDevice(DeviceBean deviceBean) throws Exception
	{
		DeviceDAO ddo=new DeviceDAO();
		return ddo.registerDevice(deviceBean);
	}
	
	public SystemDeviceBean getDeviceStatus(String homeId) throws Exception
	{
		DeviceDAO ddo=new DeviceDAO();
		return ddo.getDeviceStatus(homeId);
	}
	
}
