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
import com.SmHome.beans.DeviceLogInfoBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.RoomBean;
import com.SmHome.supporting.EncryptDecrypt;


public class RoomDAO{
	public int insertRoom(RoomBean roombean) throws Exception
	{
		String operation="";
		Transaction txt=null;
		Session session=null;
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
			//Timestamp ts=new Timestamp(System.currentTimeMillis());
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		 txt=session.beginTransaction();
		txt.begin();
		//deviceLogInfoBean.setEffectiveDate(ts);
		session.save(roombean);
		txt.commit();
		
		
		
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
	
	
	public int updateRoom(RoomBean roombean) throws Exception
	{
		String operation="";
		Transaction txt=null;
		Session session=null;
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
			//Timestamp ts=new Timestamp(System.currentTimeMillis());
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		 txt=session.beginTransaction();
		txt.begin();
		Query query = session.createSQLQuery("update room_info set description='"+roombean.getDescription()+"' where home_id='"+roombean.getHomeId()+"' and room_id='"+roombean.getRoomId()+"'");
				int update = query.executeUpdate();
		
		//deviceLogInfoBean.setEffectiveDate(ts);
		
		txt.commit();
		
		
		
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
			session.close();
		}
	
	}
	
	
	public int updateRoomAttr(RoomBean roombean) throws Exception
	{
		RoomBean rm=null;
		String operation="";
		Transaction txt=null;
		Session session=null;
		HashMap<String,Integer> response=new HashMap<>();
		try
		{
			//Timestamp ts=new Timestamp(System.currentTimeMillis());
		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//session=cfg.buildSessionFactory().getCurrentSession();
		session=cfg.buildSessionFactory().openSession();
		
		/*Criteria crit = session.createCriteria(RoomBean.class);
		Criterion cn =null;
		//Criterion cn1 =null;
		List l=null;
	 	  
			cn=Restrictions.eq("roomId",new Integer(roombean.getRoomId()));
		crit.add(cn);
	l=crit.list();
	
	if(l.size()!=0)
	{
		//HashMap<String ,Double> hst=new HashMap<>();
		Iterator it=l.iterator();
		System.out.println("inside if");
		while(it.hasNext())
		{
			 rm=(RoomBean)it.next();
		}
			
		}
	
	if(rm.getInfo1()==null && rm.getInfo2()==null)
	{
		rm.setInfo1(roombean.getInfo1());
		rm.setInfo2(roombean.getInfo2());
	}*/
		 txt=session.beginTransaction();
		txt.begin();
		Query query = session.createSQLQuery("update room_info set info1=?,info2=? where home_id=? and room_id=?");
		query.setParameter(0,roombean.getInfo1());
		query.setParameter(1,roombean.getInfo2());
		query.setParameter(2,roombean.getHomeId());
		query.setParameter(3,roombean.getRoomId());
				int update = query.executeUpdate();
		
		//deviceLogInfoBean.setEffectiveDate(ts);
		
		txt.commit();
		
		
		
		return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			rm=null;
			return -1;
		}
		finally
		{
			session.close();
		}
	
	}
	
	
}
