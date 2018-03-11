package com.SmHome.services;

import java.util.HashMap;
import java.util.Hashtable;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.dao.LoginDAO;
import com.SmHome.dao.RegisterDAO;

public class LoginService {

	public MembersBean getDetails(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.getDetails( inputDetails);
	}
	
	public Hashtable<String,Integer> authenticateHome(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.authenticateHome( inputDetails);
	}
	
	public HashMap<String, HashMap<String, String>> authenticateUser(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.authenticateUser( inputDetails);
	}
	public HashMap<String, HashMap<String, String>> authenticateUserWH(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.authenticateUserWH( inputDetails);
	}
	
	public Hashtable<String,String> generateToken(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.generateToken( inputDetails);
	}
	
	public Hashtable<String,String> authenticateToken(HashMap<String,String> inputDetails) throws Exception
	{
		LoginDAO ldo=new LoginDAO();
		return ldo.authenticateToken( inputDetails);
	}
	
	
}
