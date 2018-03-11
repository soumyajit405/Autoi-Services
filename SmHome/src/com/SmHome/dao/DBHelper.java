package com.SmHome.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
//import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.SmHome.beans.DeviceBean;
import com.SmHome.beans.DeviceLkpTypeBean;
import com.SmHome.beans.FunctionalBean;
import com.SmHome.beans.HomeLkpBean;
import com.SmHome.beans.HomeSessionBean;
import com.SmHome.beans.RoomBean;
import com.SmHome.beans.SystemDeviceBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.UserSessionBean;
import com.SmHome.beans.WHControllerBean;
import com.SmHome.beans.WHControllerInventoryBean;
import com.SmHome.beans.WHDeviceBean;
import com.SmHome.beans.WHScheduler;
import com.SmHome.beans.WHUserBean;


public class DBHelper {
	
	public String gethomeCode(Session session,String description)
	{
		//Session session = null;
		
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(HomeLkpBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("description", new String(description));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					HomeLkpBean homelkpbean = (HomeLkpBean) it.next();
					return homelkpbean.getCode();
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
		//	session.close();
		}
		return "NA";
	}
	
	public FunctionalBean getDetailsOfRooms(Session session,String type)
	{
		//Session session = null;
		
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(FunctionalBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("type", new String(type));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					FunctionalBean functionalBean = (FunctionalBean) it.next();
					return functionalBean;
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return null;
		
	}
	
	public Integer createRooms(Session session,FunctionalBean functionalbean,String homeId)
	{
		String emailStatus = "";
		//Session session = null;
		Transaction txt = null;
		int roomId=-999;
		
		HashMap<String, Integer> response = new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			//session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			for(int i=0;i<functionalbean.getRoomNumber();i++)
			{
				RoomBean roombean =new RoomBean();
				roomId=getCount(session,"Room",homeId);
				roomId=roomId+1;
				roombean.setRoomId("R"+(roomId));
				roombean.setDescription("Bed Room"+(i+1));
				roombean.setHomeId(homeId);
				roombean.setRoomType("B");
				session.save(roombean);
				createDevices(session,"B",homeId,"R"+(roomId));
				if(i%2==0)
				{
					 session.flush();
					  session.clear();
				}
				
			}
			//txt.commit();
			//txt.begin();
			for(int i=0;i<functionalbean.getHall();i++)
			{
				
				RoomBean roombean1 =new RoomBean();
				roomId=getCount(session,"Room",homeId);
				roomId=roomId+1;
				roombean1.setRoomId("R"+(roomId));
				roombean1.setDescription("Hall"+(i+1));
				roombean1.setHomeId(homeId);
				roombean1.setRoomType("H");
				createDevices(session,"H",homeId,"R"+(roomId));
				session.save(roombean1);
				if(i%2==0)
				{
					 session.flush();
					  session.clear();
				}
				// session.flush();
				 // session.clear();
				
			}
			//txt.commit();
			//txt.begin();
			for(int i=0;i<functionalbean.getKitchen();i++)
			{
				
				RoomBean roombean2 =new RoomBean();
				roomId=getCount(session,"Room",homeId);
				roomId=roomId+1;
				roombean2.setRoomId("R"+(roomId));
				roombean2.setDescription("Kitchen"+(i+1));
				roombean2.setHomeId(homeId);
				roombean2.setRoomType("K");
				createDevices(session,"K",homeId,"R"+(roomId));
				session.save(roombean2);
				if(i%2==0)
				{
					 session.flush();
					  session.clear();
				}
			//	 session.flush();
			//	  session.clear();
			}
			//txt.commit();
		//	txt.begin();
			for(int i=0;i<functionalbean.getBathRoom();i++)
			{
				
				RoomBean roombean3 =new RoomBean();
				roomId=getCount(session,"Room",homeId);
				roomId=roomId+1;
				roombean3.setRoomId("R"+(roomId));
				roombean3.setDescription("BathRoom"+(i+1));
				roombean3.setHomeId(homeId);
				roombean3.setRoomType("BH");
				createDevices(session,"BH",homeId,"R"+(roomId));
				session.save(roombean3);
				if(i%2==0)
				{
					 session.flush();
					  session.clear();
				}
				
			}
			txt.commit();
			/*if (txt.getStatus().equals(TransactionStatus.ACTIVE)) { 
				txt.commit();
			}*/
			
		
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txt.rollback();
			session.close();
			return -1;
		}
	}

	public Integer populateController(Session session,String userId,String cid,String cname)
	{
		String emailStatus = "";
		//Session session = null;
		Transaction txt = null;
		int roomId=-999;
		
		HashMap<String, Integer> response = new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			//session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			Criteria crit = session.createCriteria(WHUserBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("email", new String(userId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					WHUserBean usb = (WHUserBean) it.next();
					userId=usb.getUserId();
					//return functionalBean;
			}
			}
			WHControllerBean wbean=new WHControllerBean();
			wbean.setControllerID(cid);
			wbean.setControllerName(cname);
			wbean.setUserID(userId);
			session.save(wbean);
			
			//txt.commit();
			//txt.begin();
			
			//txt.commit();
			//txt.begin();
			txt.commit();
			
			
		
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txt.rollback();
			session.close();
			return -1;
		}
	}

	public  HashMap<String,String>  populateDevices(Session session,String cid,int count)
	{
		
		//Session session = null;
		Transaction txt = null;
		 HashMap<String,String> deviceDetails=new HashMap<>();
		
		HashMap<String, Integer> response = new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			//session = cfg.buildSessionFactory().openSession();
			
			txt = session.beginTransaction();
			txt.begin();
			for(int i=0;i<count;i++)
			{
			WHDeviceBean whdbean=new WHDeviceBean();
			whdbean.setControllerID(cid);
			if(i==0)
			{
				whdbean.setDeviceName("Fan");
			}
			else if(i==1)
			{
				whdbean.setDeviceName("Light");
			}
			//whdbean.setDeviceName("device"+i);
			session.save(whdbean);
			if(i%2==0)
			{
				 session.flush();
				  session.clear();
			}
			
				
			}
			
			
		
			txt.commit();
			
			 deviceDetails=getDeviceDetailsFromController(session,cid );
			
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txt.rollback();
			session.close();
		//	return -1;
		}
		return deviceDetails;
	}

	
	public int getCount(Session session,String code,String homeId)
	{
		Long count = null;
		String tableName="";
		Query query =null;
		try {
			
			if(code.equalsIgnoreCase("home"))
			{
				tableName="HomeBean";
				/* query = session.createQuery(
					        "select count(*) from "+tableName);*/
			}
			else if(code.equalsIgnoreCase("apartment"))
			{
				tableName="ApartmentsBean";
				/* query = session.createQuery(
					        "select count(*) from "+tableName);*/
			}
			else if(code.equalsIgnoreCase("room"))
			{
				tableName="RoomBean";
				/* query = session.createQuery(
					        "select count(*) from "+tableName+"where homeId='"+homeId+"'");*/
			}
			else if(code.equalsIgnoreCase("device"))
			{
				tableName="DeviceBean";
				/* query = session.createQuery(
					        "select count(*) from "+tableName+"where homeId='"+homeId+"'");*/
			}
			else
			{
				tableName="UserBean";
				/* query = session.createQuery(
					        "select count(*) from "+tableName+"where homeId='"+homeId+"'");*/
			}
			
			 query = session.createQuery(
				        "select count(*) from "+tableName);
		
		count = (Long)query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count.intValue();
	}
	
	public Integer createDevices(Session session,String roomCode,String homeId,String roomId)
	{
		String emailStatus = "";
		//Session session = null;
		Transaction txt = null;
		//int roomId=-999;
		HashMap<String, Integer> response = new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			//session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			ArrayList<DeviceLkpTypeBean> al=new ArrayList<>();
			al=getDetailsOfDevices(session, roomCode);
			for(int i=0;i<al.size();i++)
			{
				int deviceId=getCount(session, "device",homeId);
				DeviceBean db=new DeviceBean();
				db.setDeviceId("D"+(deviceId+1));
				db.setDescription(al.get(i).getDescription());
				db.setRoomId(roomId);
				db.setHomeId(homeId);
				SystemDeviceBean sds=new SystemDeviceBean();
				sds.setDeviceId("D"+(deviceId+1));
				sds.setHomeId(homeId);
				sds.setStatus("0");
				session.save(db);
				session.save(sds);
				
			}
		
			//txt.commit();
		//	txt.begin();
			
		//	txt.commit();
			
			
		
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			txt.rollback();
			session.close();
			return -1;
		}
	}
	
	
	public ArrayList<DeviceLkpTypeBean> getDetailsOfDevices(Session session,String roomCode)
	{
		//Session session = null;
		ArrayList<DeviceLkpTypeBean> al=new ArrayList<>();
		
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(DeviceLkpTypeBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("roomCode", new String(roomCode));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					DeviceLkpTypeBean deviceTypeLkpBean = (DeviceLkpTypeBean) it.next();
					al.add(deviceTypeLkpBean);
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return al;
		
	}
	
	public HashMap<String,String> getDetails(Session session,String homeId)
	{
		//Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(RoomBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("homeId", new String(homeId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					RoomBean rm=(RoomBean) it.next();
					
					response.put(rm.getRoomId(),rm.getDescription());
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}
	
	
	
	
	public HashMap<String,String> getDeviceDetails(Session session,String homeId,String roomId)
	{
		//Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(DeviceBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("homeId", new String(homeId));
			cn1 = Restrictions.eq("roomId", new String(roomId));
			crit.add(cn);
			crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					DeviceBean dbm=(DeviceBean) it.next();
					
					response.put(dbm.getDeviceId(),dbm.getDescription());
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}
	
	
	public HashMap<String,String> getDeviceDetailsFromController(Session session,String controllerId)
	{
		//Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(WHDeviceBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("controllerID", new String(controllerId));
			//cn1 = Restrictions.eq("roomId", new String(roomId));
			crit.add(cn);
		//	crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					WHDeviceBean dbm=(WHDeviceBean) it.next();
					
					response.put(Integer.toString(dbm.getDeviceID()),dbm.getDeviceName());
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}

	public DeviceBean getDeviceType(String homeId,String deviceId)
	{
		Session session = null;
		HashMap<String, String> response=new HashMap<>();
		DeviceBean dbm=null;
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(DeviceBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("homeId", new String(homeId));
			cn1 = Restrictions.eq("deviceId", new String(deviceId));
			crit.add(cn);
			crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					 dbm=(DeviceBean) it.next();
					
					//response.put(dbm.getDeviceId(),dbm.getDescription());
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return dbm;
		
	}

	
	public int loadHomeSession(String sessionId,String homeId)
	{
		HashMap<String, String> response=new HashMap<>();
		Session session=null;
		Transaction txt=null;
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(RoomBean.class);
			Criterion cn = null;
			 txt=session.beginTransaction();
				txt.begin();
			List l = null;

			cn = Restrictions.eq("homeId", new String(homeId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();
			Timestamp effectiveTime=new Timestamp(System.currentTimeMillis());
			if (l.size() != 0) {
				
				Query query = session.createSQLQuery("update home_sessions set session_id='"+sessionId+"',effective_date="+effectiveTime+" where home_id='"+homeId+"'");
				int update = query.executeUpdate();
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			
			}else {
				HomeSessionBean hms=new HomeSessionBean();
				hms.setEffectiveDate(effectiveTime);
				hms.setHomeId(homeId);
				hms.setSessionId(sessionId);
				session.save(hms);
			}
			txt.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return 0;
		
	}
	
	
	public int loadUserSession(String sessionId,String userId)
	{
		HashMap<String, String> response=new HashMap<>();
		Session session=null;
		Transaction txt=null;
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(UserSessionBean.class);
			Criterion cn = null;
			 txt=session.beginTransaction();
				txt.begin();
			List l = null;

			cn = Restrictions.eq("userId", new String(userId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();
			Timestamp effectiveTime=new Timestamp(System.currentTimeMillis());
			if (l.size() != 0) {
				
				Query query = session.createSQLQuery("update user_session set session_id='"+sessionId+"',effective_date='"+effectiveTime+"' where user_id='"+userId+"'");
				int update = query.executeUpdate();
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			
			}else {
					UserSessionBean	ums=new UserSessionBean();
					ums.setEffectiveDate(effectiveTime);
				ums.setUserId(userId);
				ums.setSessionId(sessionId);
				session.save(ums);
			}
			txt.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return 0;
		
	}
	
	public ArrayList<String> getListOfUsers(String userId)
	{
		
		ArrayList<String> userIds=new ArrayList<>();
		HashMap<String, String> response=new HashMap<>();
		Session session=null;
		Transaction txt=null;
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			
			//l = crit.list();
			//Timestamp effectiveTime=new Timestamp(System.currentTimeMillis());
			
				
				Query query = session.createSQLQuery("select email from l3_user where email <> '"+userId+"' and home_id = (select home_id from l3_user where email='"+userId+"')");
				List result=query.list();
				if (result.size() != 0) {
					Iterator it =result.iterator();
					System.out.println("inside if");
					while (it.hasNext()) {
						userIds.add((String)it.next());
						
				}
					
						
						
				
				}
				
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			
			
			
			return userIds;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return null;
		
		
	}
	
	
	public String getUserId(String mail)
	{

		Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(UserBean.class);
			Criterion cn = null;
			Criterion cn1 = null;
			List l = null;

			cn = Restrictions.eq("email", new String(mail));
			//cn1 = Restrictions.eq("roomId", new String(roomId));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					UserBean usb=(UserBean) it.next();
					
					return usb.getId();
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return "NA";
		
	}
	public static void main(String args[])
	{
		DBHelper dbh=new DBHelper();
		System.out.println(dbh.getListOfUsers("U1"));
	}
	
	public HashMap<String, String> getDeviceStatusDetails(Session session,String homeId)
	{
		HashMap<String, String> response=new HashMap<>();
		//Session session=null;
		Transaction txt=null;
		try {
			//Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			//session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(SystemDeviceBean.class);
			Criterion cn = null;
			 txt=session.beginTransaction();
				txt.begin();
			List l = null;

			cn = Restrictions.eq("homeId", new String(homeId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();
			Timestamp effectiveTime=new Timestamp(System.currentTimeMillis());
			if (l.size() != 0) {
				
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					SystemDeviceBean sbm=(SystemDeviceBean) it.next();
					
					response.put(sbm.getDeviceId(),sbm.getStatus());
			}
			}
			txt.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}
	
	
	
	public HashMap<String,String> getControllerDetails(Session session,String homeId)
	{
		//Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(WHControllerBean.class);
			Criterion cn = null;
		
			List l = null;

			cn = Restrictions.eq("userID", new String(homeId));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					WHControllerBean wcb=(WHControllerBean) it.next();
					Criteria crit1 = session.createCriteria(WHControllerInventoryBean.class);
					Criterion	cn1 = Restrictions.eq("controllerID", new String(wcb.getControllerID()));
					List l1 = crit1.list();

					if (l1.size() != 0) {
						Iterator it1 = l1.iterator();
						System.out.println("inside if");
						while (it1.hasNext()) {
							WHControllerInventoryBean wcib=(WHControllerInventoryBean) it1.next();
							//response.put(wcb.getControllerID(),wcb.getControllerName()+"||"+wcib.getTopic());
							response.put(wcb.getControllerID(),wcb.getControllerName());
						}
					}
						
					
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}
	
	public HashMap<String,String> getScedulerDetails(Session session,String userId)
	{
		//Session session = null;
		HashMap<String, String> response=new HashMap<>();
		try {
		//	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		//	session = cfg.buildSessionFactory().openSession();
			// session=cfg.buildSessionFactory().getCurrentSession();
			Criteria crit = session.createCriteria(WHScheduler.class);
			Criterion cn = null,cn1=null;
			
		
			List l = null;

			cn = Restrictions.eq("userID", new String(userId));
			cn1 = Restrictions.eq("mode", new String("E"));
			//cn1 = Restrictions.eq("token", new String(userDetails.get("token")));
			crit.add(cn);
			crit.add(cn1);
			//crit.add(cn1);
			l = crit.list();
			//l = crit.list();

			if (l.size() != 0) {
				Iterator it = l.iterator();
				System.out.println("inside if");
				while (it.hasNext()) {
					WHScheduler whs=(WHScheduler) it.next();
					
					//HashMap<String,String> deviceSchedule=new HashMap<>();
					//deviceSchedule.put(Integer.toString(whs.getDeviceID()), whs.getStartTime());
					response.put(Integer.toString(whs.getDeviceID()), whs.getStartTime());
					
						
					
					//HashMap<String, String> deviceDetails=getDeviceDetails(session,homeId,rm.getRoomId());
					
			}
			}else {
				//response.put("key", -999);
				// send response if not matched
				//return "NA";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		//	txt.rollback();
			session.close();
		//	response.put("key", 0);

		} finally {
			//session.close();
		}
		return response;
		
	}
}
