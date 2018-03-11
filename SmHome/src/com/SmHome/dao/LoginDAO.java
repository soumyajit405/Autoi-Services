package com.SmHome.dao;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.AdminTokenBean;
import com.SmHome.beans.HomeAdminBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.WHUserBean;
import com.SmHome.supporting.EncryptDecrypt;
import com.SmHome.supporting.RandomTimeGenerator;

public class LoginDAO {
	public MembersBean getDetails(HashMap<String, String> inputDetails) throws Exception {
		MembersBean memberbean = null;
		Session session = null;
		HashMap<String, Integer> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();
			session = cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(MembersBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("email", new String(inputDetails.get("email")));
			crit.add(cn);
			l = crit.list();

			if (l.size() != 0) {
				// HashMap<String ,Double> hst=new HashMap<>();
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					memberbean = (MembersBean) it.next();
				}

			} else {
				// send response if not matched
			}

			System.out.println("AFTER");

			session.close();

			return memberbean;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", -1);
			return memberbean;

		}

	}

	public Hashtable<String,String> generateToken(HashMap<String,String> inputDetails) throws Exception
	{
		
		Session session=null;
		Transaction txt = null;
		Hashtable<String,String> response=new Hashtable<>();
		String homeId="";
		try
		{
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		
		txt = session.beginTransaction();
		txt.begin();
			Criteria crit = session.createCriteria(HomeAdminBean.class);
			Criterion cn =null;
			Criterion cn1 =null;
			List l=null;
		 	  
				cn=Restrictions.eq("email",new String(inputDetails.get("email")));
				//cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			//crit.add(cn1);
		l=crit.list();
		
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			 HomeAdminBean usb =(HomeAdminBean) it.next();
			 homeId=usb.getHomeId();
				//memberbean = (MembersBean) it.next();
			}
			
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			RandomTimeGenerator rt=new RandomTimeGenerator();
			String randomCode=rt.randomAlphaNumeric(4);
			AdminTokenBean adm=new AdminTokenBean();
			adm.setGeneratedTime(ts);
			adm.setHomeId(homeId);
			adm.setToken(randomCode);
			adm.setTokenStatus("A");
			session.save(adm);
			txt.commit();
			response.put("key",randomCode+homeId);	
			}
		else
		{
			 
				response.put("key","-999");
			
			
		System.out.println("AFTER");
			
			
		session.close();

		
		
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", "-1");
			return response;
			
		}
		return response;
	}

	
	
	public HashMap<String, HashMap<String, String>> authenticateUser(HashMap<String,String> inputDetails) throws Exception
	{
		
		Session session=null;
		HashMap<String, HashMap<String,String>> response = new HashMap<>();
		HashMap<String,String> secondaryResponse=new HashMap<>();
		 UserBean usb=null;
			DBHelper db=new DBHelper();
		try
		{
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(UserBean.class);
			Criterion cn =null;
			Criterion cn1 =null;
			List l=null;
		 	  
				cn=Restrictions.eq("email",new String(inputDetails.get("email")));
				cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			crit.add(cn1);
		l=crit.list();
		
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			  usb =(UserBean) it.next();
			accessLevel=usb.getAccessLevel();
				//memberbean = (MembersBean) it.next();
			}
			secondaryResponse.put("access", "P");
			HashMap<String,String> secondaryResponse1=new HashMap<>();
			secondaryResponse1.put("homeId",usb.getHomeId());
			response.put("key", secondaryResponse);
			response.put("homeId",secondaryResponse1);
			/*HashMap<String, String> roomDetails=db.getDetails(session,usb.getHomeId());
			response.put("Rooms",roomDetails);
			for ( Map.Entry<String, String> entry : roomDetails.entrySet()) {
			    HashMap<String,String> deviceDetails=db.getDeviceDetails(session, usb.getHomeId(), entry.getKey());
			    response.put(entry.getKey(), deviceDetails);
			    // do something with key and/or tab
			}
			HashMap<String,String> deviceStatusDetails=db.getDeviceStatusDetails(session, usb.getHomeId());
			   response.put("Devices", deviceStatusDetails);*/
			//response.put("key",accessLevel);	
			}
		else
		{
			 	secondaryResponse.put("access","-999");
				response.put("key",secondaryResponse);
			
			
		System.out.println("AFTER");
			
			
		session.close();

		
		
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			HashMap<String, String> hmap=new HashMap<>();
			hmap.put("access","-1");
			response.put("key", hmap);
			//return response;
			
		}
		return response;
	}

	public HashMap<String, HashMap<String, String>> authenticateUserWH(HashMap<String,String> inputDetails) throws Exception
	{
		
		Session session=null;
		HashMap<String, HashMap<String,String>> response = new HashMap<>();
		HashMap<String,String> secondaryResponse=new HashMap<>();
		WHUserBean usb=null;
			DBHelper db=new DBHelper();
		try
		{
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(WHUserBean.class);
			Criterion cn =null;
			Criterion cn1 =null;
			List l=null;
		 	  
				cn=Restrictions.eq("email",new String(inputDetails.get("email")));
				cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			crit.add(cn1);
		l=crit.list();
		
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			  usb =(WHUserBean) it.next();
		//	accessLevel=usb.getAccessLevel();
				//memberbean = (MembersBean) it.next();
			}
			//secondaryResponse.put("access", "P");
			HashMap<String,String> secondaryResponse1=new HashMap<>();
			secondaryResponse1.put("homeId","1");
			//response.put("key", secondaryResponse);
			response.put("homeId",secondaryResponse1);
			HashMap<String, String> controllerDetails=db.getControllerDetails(session,usb.getUserId());
			HashMap<String, String> schedulerDetails=db.getScedulerDetails(session,usb.getUserId());
			response.put("controllers",controllerDetails);
			response.put("schedulers",schedulerDetails);
			for ( Map.Entry<String, String> entry : controllerDetails.entrySet()) {
			    HashMap<String,String> deviceDetails=db.getDeviceDetailsFromController(session, entry.getKey());
			    response.put(entry.getKey(), deviceDetails);
			    // do something with key and/or tab
			}
			/*HashMap<String,String> deviceStatusDetails=db.getDeviceStatusDetails(session, usb.getHomeId());
			   response.put("Devices", deviceStatusDetails);*/
			//response.put("key",accessLevel);	
			}
		
		else
		{
			 	secondaryResponse.put("homeId","-999");
				response.put("homeId",secondaryResponse);
			
			
		System.out.println("AFTER");
			
			
		session.close();

		
		
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			HashMap<String, String> hmap=new HashMap<>();
			hmap.put("homeId","-1");
			response.put("homeId", hmap);
			//return response;
			
		}
		return response;
	}
	public Hashtable<String,String> authenticateToken(HashMap<String,String> inputDetails) throws Exception
	{
		
		Session session=null;
		Hashtable<String,String> response=new Hashtable<>();
		try
		{
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(AdminTokenBean.class);
			Criterion cn =null;
			Criterion cn1 =null;
			List l=null;
		 	  System.out.println(inputDetails.get("token"));
			String homeId=inputDetails.get("token").substring(18);
			String token=inputDetails.get("token").substring(0,18);
				cn=Restrictions.eq("token",new String(token));
				cn1=Restrictions.eq("homeId",new String(homeId));
			crit.add(cn);
			crit.add(cn1);
		l=crit.list();
		
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			 AdminTokenBean adm =(AdminTokenBean) it.next();
			 response.put("key",adm.getHomeId());	
				//memberbean = (MembersBean) it.next();
			}
			
			}
			//response.put("key","1");	
			
		else
		{
			 
				response.put("key","-999");
			
			
		System.out.println("AFTER");
			
			
		session.close();

		
		
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", "-1");
			return response;
			
		}
		return response;
	}
	
	
	public Hashtable<String, Integer> authenticateHome(HashMap<String, String> inputDetails) throws Exception {
		MembersBean memberbean = null;
		Session session = null;
		Hashtable<String, Integer> response = new Hashtable<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();
			session = cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(HomeBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("id", new Integer(inputDetails.get("id")));
			cn1 = Restrictions.eq("password", new String(inputDetails.get("password")));
			crit.add(cn);
			crit.add(cn1);
			l = crit.list();

			if (l.size() != 0) {
				// HashMap<String ,Double> hst=new HashMap<>();
				/*
				 * Iterator it=l.iterator(); System.out.println("inside if");
				 * while(it.hasNext()) { HomeBean=(HomeBean)it.next(); }
				 */
				response.put("key", 1);
			} else {
				response.put("key", 0);
				// send response if not matched
			}

			System.out.println("AFTER");

			session.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", -1);
			return response;

		}

	}

}
