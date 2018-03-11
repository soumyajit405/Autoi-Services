package com.SmHome.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.FunctionalBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.WHControllerBean;
import com.SmHome.beans.WHControllerInventoryBean;
import com.SmHome.beans.WHDeviceBean;
import com.SmHome.beans.WHScheduler;
import com.SmHome.beans.WHUserBean;
import com.SmHome.controller.WHController;
import com.SmHome.supporting.EncryptDecrypt;


public class WHControllerDAO{
	public HashMap<String, HashMap<String, String>> verifyController(HashMap<String,String> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
		Session session=null;
		int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		 DBHelper db=new DBHelper();
		try
		{
		//	Timestamp ts=new Timestamp(System.currentTimeMillis());
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
			session=cfg.buildSessionFactory().openSession();
				Criteria crit = session.createCriteria(WHControllerInventoryBean.class);
				Criterion cn =null;
				Criterion cn1 =null;
				Criterion cn2 =null;
				List l=null;
				//System.out.println("serialNO "+whb.getSerialNO());
				//System.out.println("passKey "+whb.getPassKey());
					cn=Restrictions.eq("controllerID",new String(inputDetails.get("controllerId")));
					cn1=Restrictions.eq("passKey",new String(inputDetails.get("passKey")));
				//	cn2=Restrictions.eq("homeID",new String(whb.getHomeID()));
				crit.add(cn);
				crit.add(cn1);
			//	crit.add(cn2);
				
			l=crit.list();
			System.out.println("size "+l.size());
			if(l.size()!=0)
			{	
				String accessLevel="";
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					
					WHControllerInventoryBean wb=(WHControllerInventoryBean)it.next();
					db.populateController(session, inputDetails.get("userName"), inputDetails.get("controllerId"), inputDetails.get("controllerName"));
					 HashMap<String,String> deviceDetails=db.populateDevices(session, inputDetails.get("controllerId"), Integer.parseInt(wb.getProductCode()));
					 response.put("devices", deviceDetails);
					
					 status.put("status", "1");
					 response.put("response",status);
				// deviceDetails=db.getDeviceDetailsFromController(session, wbtemp.getControllerID());
					//memberbean = (MembersBean) it.next();
				}
		
	
		
	
			}
			else
			{
				status.put("status", "0");
				 response.put("response",status);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			session.close();
		}
		return response;
	}
	
	public int updateController(HashMap<String,HashMap<String,String>> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
		Session session=null;
		int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		 DBHelper db=new DBHelper();
		 HashMap<String,String> input=new HashMap<>();
		 Transaction tx=null;
		 SQLQuery sqlQuery=null;
		try
		{
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
			session=cfg.buildSessionFactory().openSession();
			tx= session.beginTransaction(); 
			input=inputDetails.get("controllers");
			//Map<Integer, Integer> map = input;
			Object o=null;
			// Iterating over keys only
			for (String key : input.keySet()) {
			    //System.out.println("Key = " + key);
				/*o=session.load(WHControllerBean.class,new String(key));
				 WHControllerBean whcontroller=(WHControllerBean)o;
				 
				 tx= session.beginTransaction(); 
				 
				//s.setStno(105);   should not update, because we loaded with that number right..?
				 whcontroller.setControllerName(input.get(key)); */
				
						String hql = "UPDATE WHControllerBean set controllerName = :cname "  + 
					             "WHERE controllerID = :cid";
					Query query = session.createQuery(hql);
					query.setParameter("cname", input.get(key));
					query.setParameter("cid", key);
					int result = query.executeUpdate();
			}
			 tx.commit();
			 HashMap<String,String> devices=new HashMap<>();
			 devices=inputDetails.get("devices");
				tx= session.beginTransaction(); 
			 for (String key : devices.keySet()) {
				    //System.out.println("Key = " + key);
					/*o=session.load(WHDeviceBean.class,new String(key));
					 WHDeviceBean whdevice=(WHDeviceBean)o;
					 
					// 
					 
					//s.setStno(105);   should not update, because we loaded with that number right..?
					 whdevice.setDeviceName(devices.get(key));*/
				
					String hql = "UPDATE WHDeviceBean set deviceName = :dname "  + 
				             "WHERE deviceID = :did";
				Query query = session.createQuery(hql);
				query.setParameter("dname", devices.get(key));
				query.setParameter("did", Integer.parseInt(key));
				int result = query.executeUpdate();
				}
			 tx.commit();
				
		
	
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			 return -1;
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			session.close();
		}
		return 1;
	}
	
	
	public int scheduleDevice(HashMap<String,String> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
		Session session=null;
	//	int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		// DBHelper db=new DBHelper();
		// HashMap<String,String> input=new HashMap<>();
		 Transaction tx=null;
		// SQLQuery sqlQuery=null;
		 WHUserBean userbean=null;
		try
		{
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
			session=cfg.buildSessionFactory().openSession();
			tx= session.beginTransaction(); 
			//input=inputDetails.get("controllers");
			//Map<Integer, Integer> map = input;
			
			
			//session=cfg.buildSessionFactory().getCurrentSession();
			//session=cfg.buildSessionFactory().openSession();
				Criteria crit = session.createCriteria(WHUserBean.class);
				Criterion cn =null;
				Criterion cn1 =null;
				Criterion cn2 =null;
				Criterion cn3 =null;
				List l=null;
			 	  
					cn=Restrictions.eq("email",new String(inputDetails.get("userID")));
				//	cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
				crit.add(cn);
				//crit.add(cn1);
			l=crit.list();
			if(l.size()!=0)
			{	
				String accessLevel="";
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					
					userbean =(WHUserBean) it.next();
			//	accessLevel=usb.getAccessLevel();
					//memberbean = (MembersBean) it.next();
				}
			}
			if(inputDetails.get("mode").equalsIgnoreCase("D"))
			{
				inputDetails.put("userID", userbean.getUserId());
				disableScheduledDevice(session,inputDetails);
			}
			else 
			{
				crit = session.createCriteria(WHScheduler.class);
				
			 	  
					cn=Restrictions.eq("controllerID",new String(inputDetails.get("controllerID")));
					cn1=Restrictions.eq("deviceID",new Integer(Integer.parseInt(inputDetails.get("deviceID"))));
					cn2=Restrictions.eq("userID",new String(userbean.getUserId()));
					cn3=Restrictions.eq("mode",new String("E"));
					
				//	cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
				crit.add(cn);
				crit.add(cn1);
				crit.add(cn2);
				crit.add(cn3);
				//crit.add(cn1);
			l=crit.list();
				if(l.size()>0)
				{
					inputDetails.put("userID", userbean.getUserId());
					updateSchedule(session,inputDetails);
				}
			
			else
			{
			Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(inputDetails.get("startDate")); 
			 java.sql.Date sqlStartDate =  new java.sql.Date(startDate.getTime());
			Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(inputDetails.get("endDate"));
			 java.sql.Date sqlEnddate =  new java.sql.Date(endDate.getTime());
			WHScheduler whscheduler=new WHScheduler();
			whscheduler.setUserID(userbean.getUserId());
			whscheduler.setControllerID(inputDetails.get("controllerID"));
			whscheduler.setDeviceID(Integer.parseInt(inputDetails.get("deviceID")));
			whscheduler.setStatus(inputDetails.get("status"));
			whscheduler.setDescription(inputDetails.get("description"));
			whscheduler.setEndDate(sqlEnddate);
			whscheduler.setStartDate(sqlStartDate);
			whscheduler.setMode(inputDetails.get("mode"));
			whscheduler.setStartTime(inputDetails.get("startTime"));
			whscheduler.setEndTime(inputDetails.get("endTime"));
						session.save(whscheduler);
			 tx.commit();
				
			}
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			 return -1;
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			session.close();
		}
		return 1;
	}
	
	public int disableScheduledDevice(Session session,HashMap<String,String> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
	//	Session session=null;
		int result;
	//	int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		// DBHelper db=new DBHelper();
		// HashMap<String,String> input=new HashMap<>();
		 Transaction tx=null;
		// SQLQuery sqlQuery=null;
		 WHUserBean userbean=null;
		try
		{
		//	Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
		//	session=cfg.buildSessionFactory().openSession();
			tx= session.beginTransaction(); 
			//input=inputDetails.get("controllers");
			//Map<Integer, Integer> map = input;
			
			
			//session=cfg.buildSessionFactory().getCurrentSession();
		//	session=cfg.buildSessionFactory().openSession();
			String hql = "UPDATE WHScheduler set mode=:mode "  + 
		             "WHERE controllerID = :controllerID and deviceID=:deviceID and userID=:userID";
		Query query = session.createQuery(hql);
		query.setParameter("mode", "D");
		query.setParameter("controllerID", inputDetails.get("controllerID"));
		query.setParameter("userID", inputDetails.get("userID"));
		query.setParameter("deviceID", Integer.parseInt(inputDetails.get("deviceID")));
		result = query.executeUpdate();
			 tx.commit();
				
		
	
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			 return -1;
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			//session.close();
		}
		return result ;
	}
	
	
	public int updateSchedule(Session session,HashMap<String,String> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
		//Session session=null;
		int result;
	//	int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		// DBHelper db=new DBHelper();
		// HashMap<String,String> input=new HashMap<>();
		 Transaction tx=null;
		// SQLQuery sqlQuery=null;
		 WHUserBean userbean=null;
		try
		{
		//	Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
		//	session=cfg.buildSessionFactory().openSession();
			tx= session.beginTransaction(); 
			//input=inputDetails.get("controllers");
			//Map<Integer, Integer> map = input;
			Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(inputDetails.get("startDate")); 
			 java.sql.Date sqlStartDate =  new java.sql.Date(startDate.getTime());
			
			//session=cfg.buildSessionFactory().getCurrentSession();
			//session=cfg.buildSessionFactory().openSession();
			String hql = "UPDATE WHScheduler set status=:status , startTime=:startTime,startDate=:startDate "  + 
					"WHERE controllerID = :controllerID and deviceID=:deviceID and userID=:userID";
			Query query = session.createQuery(hql);
			query.setParameter("status",inputDetails.get("status") );
			query.setParameter("startDate", sqlStartDate);
			query.setParameter("startTime", inputDetails.get("startTime"));
			query.setParameter("controllerID", inputDetails.get("controllerID"));
			query.setParameter("userID", inputDetails.get("userID"));
			query.setParameter("deviceID", Integer.parseInt(inputDetails.get("deviceID")));
		result = query.executeUpdate();
			 tx.commit();
				
		
	
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			 return -1;
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			//session.close();
		}
		return result ;
	}
	
	
	public  ArrayList<WHScheduler> viewScheduledDevices(HashMap<String,String> inputDetails) throws Exception
	{
		//String operation="";
		//Transaction txt=null;
		Session session=null;
		int result;
	//	int channelNo=-999;
		HashMap<String, HashMap<String, String>> response=new HashMap<>();
		 HashMap<String,String> status=new HashMap<>();
		 ArrayList<WHScheduler> al=new ArrayList<>();
		// DBHelper db=new DBHelper();
		// HashMap<String,String> input=new HashMap<>();
		 Transaction tx=null;
		// SQLQuery sqlQuery=null;
		 WHUserBean userbean=null;
		try
		{
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			//session=cfg.buildSessionFactory().getCurrentSession();
			session=cfg.buildSessionFactory().openSession();
			tx= session.beginTransaction(); 
			//input=inputDetails.get("controllers");
			//Map<Integer, Integer> map = input;
			
			
			//session=cfg.buildSessionFactory().getCurrentSession();
			//session=cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(WHUserBean.class);
			Criterion cn =null,cn1=null,cn2=null,cn3=null;
			//Criterion cn1 =null;
			List l=null;
		 	  
			cn=Restrictions.eq("email",new String(inputDetails.get("userID")));
			//	cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			//crit.add(cn1);
		l=crit.list();
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
				userbean =(WHUserBean) it.next();
		//	accessLevel=usb.getAccessLevel();
				//memberbean = (MembersBean) it.next();
			}
		}
		crit = session.createCriteria(WHScheduler.class);
				cn=Restrictions.eq("controllerID",new String(inputDetails.get("controllerID")));
				cn1=Restrictions.eq("deviceID",new Integer(Integer.parseInt(inputDetails.get("deviceID"))));
				cn2=Restrictions.eq("userID",new String(userbean.getUserId()));
				cn3=Restrictions.eq("mode",new String("E"));
			//	cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			crit.add(cn1);
			crit.add(cn2);
			crit.add(cn3);
			//crit.add(cn1);
		l=crit.list();
		if(l.size()!=0)
		{	
			String accessLevel="";
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			WHScheduler	whscheduler =(WHScheduler) it.next();
			al.add(whscheduler);
		//	accessLevel=usb.getAccessLevel();
				//memberbean = (MembersBean) it.next();
			}
		}
			// tx.commit();
				
		
	
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			status.put("status", "-1");
			 response.put("response",status);
			 return null;
			//session.close();
		//	return deviceDetails.put("response","-999");
			
		}
		finally
		{
			session.close();
		}
		return  al ;
	}
}
