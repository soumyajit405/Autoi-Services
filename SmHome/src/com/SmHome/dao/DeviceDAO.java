package com.SmHome.dao;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.SmHome.beans.AdminBean;
import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.SystemDeviceBean;
import com.SmHome.supporting.EncryptDecrypt;


public class DeviceDAO{
	public int registerDevice(DeviceBean deviceBean) throws Exception
	{
		Transaction txt=null;
		Session session=null;
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		 txt=session.beginTransaction();
		txt.begin();
		session.save(deviceBean);
		txt.commit();
		
		Query query = session.createSQLQuery("insert into system_device_info(home_id, device_id,status)values(?,?,?)");
		query.setParameter(0,deviceBean.getHomeId());
		query.setParameter(1,deviceBean.getDeviceId());
		query.setParameter(2,"X");
				int update = query.executeUpdate();
		
		return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			return 0;
			
		}
		finally
		{
			session.close();
		}
	
	}
	
	public SystemDeviceBean getDeviceStatus(String homeId) throws Exception
	{
		SystemDeviceBean systemdevicebean = null;
		Session session = null;
		HashMap<String, Integer> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();
			session = cfg.buildSessionFactory().openSession();
			Criteria crit = session.createCriteria(SystemDeviceBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("email", new String(homeId));
			crit.add(cn);
			l = crit.list();

			if (l.size() != 0) {
				// HashMap<String ,Double> hst=new HashMap<>();
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					systemdevicebean = (SystemDeviceBean)it.next();
				}

			} else {
				// send response if not matched
			}

			System.out.println("AFTER");

			session.close();

			return systemdevicebean;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", -1);
			 systemdevicebean = null;
			return systemdevicebean;

		}
	}
	
	
}
