package com.SmHome.services;

import java.util.HashMap;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.HomeAdminBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.WHUserBean;
import com.SmHome.dao.RegisterDAO;

public class RegisterService {

	public HashMap<String,String> registerHome(HomeBean homebean) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.registerHome(homebean);
	}
	
	public HashMap<String, String> registerAdmin(HomeAdminBean homeadminbean) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.registerAdmin(homeadminbean);
	}
	
	public HashMap<String,Integer> registerMember(MembersBean membersbean) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.registerMember(membersbean);
	}
	
	public HashMap<String,Integer> insertPersonalDetails(PersonalDetailsBean psb) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.insertPersonalDetails(psb);
	}
	
	public HashMap<String,Integer> getAuthenticated(HashMap<String,String> inputDetails) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.getAuthenticated(inputDetails);
	}
	
	public HashMap<String,HashMap<String,String>> insertUserDetails(HashMap<String,String> userDetails) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.insertUserDetails(userDetails);
	}
	public HashMap<String,Integer> registerMemberWH(WHUserBean whuserbean) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.registerMemberWH(whuserbean);
	}
	
	public HashMap<String, HashMap<String, String>> googleSingInWH(WHUserBean whuserbean) throws Exception
	{
		RegisterDAO rdo=new RegisterDAO();
		return rdo.googleSingInWH(whuserbean);
	}
	
}
