package com.SmHome.services;

import java.util.HashMap;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.WHDeviceLogBean;
import com.SmHome.dao.ActionDAO;
import com.SmHome.dao.DeviceDAO;
import com.SmHome.dao.LoginDAO;
import com.SmHome.dao.RegisterDAO;

public class ActionService {

	public int performAction(DeviceLogInfoBean deviceLogInfoBean) throws Exception
	{
		ActionDAO ado=new ActionDAO();
		return ado.performAction(deviceLogInfoBean);
	}
	
	public int performActionWH(WHDeviceLogBean deviceLogInfoBean) throws Exception
	{
		ActionDAO ado=new ActionDAO();
		return ado.performActionWH(deviceLogInfoBean);
	}
	
	public HashMap<String,Integer> getMetrics(HashMap<String,String> inputDetails) throws Exception
	{
		ActionDAO ado=new ActionDAO();
		return ado.getMetrics(inputDetails);
	}
	
}
