package com.SmHome.dao;

import java.sql.*;
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
import com.SmHome.beans.WHDeviceLogBean;
import com.SmHome.supporting.ActionHelper;
import com.SmHome.supporting.EncryptDecrypt;
import com.SmHome.supporting.QueryUtils;


public class ActionDAO{
	public int performAction(DeviceLogInfoBean deviceLogInfoBean) throws Exception
	{
		String operation="";
		Transaction txt=null;
		Session session=null;
		String userId="";
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
			Timestamp ts=new Timestamp(System.currentTimeMillis());
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(UserBean.class);
		Criterion cn = null;
	
		List l = null;

		cn = Restrictions.eq("email", new String(deviceLogInfoBean.getUserId()));
		//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
		crit.add(cn);
		//crit.add(cn1);
		l = crit.list();
		//l = crit.list();

		if (l.size() != 0) {
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				UserBean usb = (UserBean) it.next();
				userId=usb.getId();
				//return functionalBean;
		}
		}
		
		 txt=session.beginTransaction();
		txt.begin();
		deviceLogInfoBean.setUserId(userId);
		deviceLogInfoBean.setEffectiveDate(ts);
		session.save(deviceLogInfoBean);
		txt.commit();
		
	/*	if(deviceLogInfoBean.getStatus().equalsIgnoreCase("start"))
		{
			operation="O";
		}
		else
		{
			operation="X";
		}*/
		Query query = session.createSQLQuery("update system_device_info set status=? where home_id=? and device_id=?");
		query.setParameter(0,operation);
		query.setParameter(1,deviceLogInfoBean.getHomeId());
		query.setParameter(2,deviceLogInfoBean.getDeviceId());
				int update = query.executeUpdate();
		
		return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			return -1;
			
		}
		finally
		{
			session.close();
		}
	
	}
	
	public int performActionWH(WHDeviceLogBean deviceLogInfoBean) throws Exception
	{
		String operation="";
		Transaction txt=null;
		Session session=null;
		String userId="";
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			ActionHelper ah=new ActionHelper();
			ah.getActionCreated(deviceLogInfoBean.getUserID(),deviceLogInfoBean.getStatus(),deviceLogInfoBean.getControllerID(), deviceLogInfoBean.getDeviceID(), deviceLogInfoBean.getSource());
		/*Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(UserBean.class);
		Criterion cn = null;
	
		List l = null;

		cn = Restrictions.eq("email", new String(deviceLogInfoBean.getUserID()));
		//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
		crit.add(cn);
		//crit.add(cn1);
		l = crit.list();
		//l = crit.list();

		if (l.size() != 0) {
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				UserBean usb = (UserBean) it.next();
				userId=usb.getId();
				//return functionalBean;
		}
		}
		Query query;
		if(deviceLogInfoBean.getStatus().equalsIgnoreCase("1"))
		{
			query = session.createSQLQuery(
					"select on_time from wh_device_log_info where status ='0' and controller_id=:cid and device_id=:did and user_id=:uid order by effective_date desc limit 1  ")
					.addEntity(WHDeviceLogBean.class)
					.setParameter("cid", deviceLogInfoBean.getControllerID())
					.setParameter("uid", userId)
					.setParameter("did", deviceLogInfoBean.getDeviceID());
			System.out.println(query.getQueryString());
					List result = query.list();
					if(result.size()==0)
					{
						deviceLogInfoBean.setUserID(userId);
						deviceLogInfoBean.setEffectiveDate(ts);
						deviceLogInfoBean.setOnTime(0);
						session.save(deviceLogInfoBean);
					}
					else
					{
						deviceLogInfoBean.setUserID(userId);
						deviceLogInfoBean.setEffectiveDate(ts);
						deviceLogInfoBean.setOnTime((int)result.get(0));
						session.save(deviceLogInfoBean);
					}
		}*/
		/*else
		{
		 query = session.createSQLQuery(
				"select count(*) from wh_device_log_info  where   controller_id =:cid and user_id=:uid")
				.addEntity(WHDeviceLogBean.class)
				.setParameter("cid", deviceLogInfoBean.getControllerID())
				.setParameter("uid", deviceLogInfoBean.getUserID());
				List result = query.list();
		}
         for(Object object : data) {
            Map row = (Map)object;
            System.out.print("First Name: " + row.get("first_name")); 
            System.out.println(", Salary: " + row.get("salary")); 
         }*/
		/// txt=session.beginTransaction();
		/*txt.begin();
		deviceLogInfoBean.setUserID(userId);
		deviceLogInfoBean.setEffectiveDate(ts);
		session.save(deviceLogInfoBean);*/
		//txt.commit();
		
	/*	if(deviceLogInfoBean.getStatus().equalsIgnoreCase("start"))
		{
			operation="O";
		}
		else
		{
			operation="X";
		}*/
		/*Query query = session.createSQLQuery("update system_device_info set status=? where home_id=? and device_id=?");
		query.setParameter(0,operation);
		query.setParameter(1,deviceLogInfoBean.getHomeId());
		query.setParameter(2,deviceLogInfoBean.getDeviceId());
				int update = query.executeUpdate();
		*/
		return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			//session.close();
			return -1;
			
		}
		finally
		{
			//session.close();
		}
	
	}
	
	
	public HashMap<String,Integer> getMetrics(HashMap<String,String> inputDetails) throws Exception
	{
		
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
		ActionHelper ahelper=new ActionHelper();
		response =ahelper.getMetricsData(inputDetails.get("controllerId"),inputDetails.get("deviceId"),inputDetails.get("year"));
		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			//session.close();
			//return -1;
			
		}
		finally
		{
			//session.close();
		}
		return response;
	}
	
}
