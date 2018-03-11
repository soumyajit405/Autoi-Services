package com.SmHome.services;

import java.util.HashMap;
import java.util.Hashtable;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.RoomBean;
import com.SmHome.dao.ActionDAO;
import com.SmHome.dao.DeviceDAO;
import com.SmHome.dao.LoginDAO;
import com.SmHome.dao.RegisterDAO;
import com.SmHome.dao.RoomDAO;

public class RoomService {

	public int insertRoom(RoomBean roombean) throws Exception
	{
		RoomDAO rdo=new RoomDAO();
		return rdo.insertRoom(roombean);
	}
	
	public int updateRoom(RoomBean roombean) throws Exception
	{
		RoomDAO rdo=new RoomDAO();
		return rdo.updateRoom(roombean);
	}
	
	public int updateRoomAttr(RoomBean roombean) throws Exception
	{
		RoomDAO rdo=new RoomDAO();
		return rdo.updateRoomAttr(roombean);
	}
	
}
