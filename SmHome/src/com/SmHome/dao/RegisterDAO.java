package com.SmHome.dao;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
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
import com.SmHome.beans.FunctionalBean;
import com.SmHome.beans.HomeAdminBean;
import com.SmHome.beans.HomeBean;
import com.SmHome.beans.MembersBean;
import com.SmHome.beans.PersonalDetailsBean;
import com.SmHome.beans.RoomBean;
import com.SmHome.beans.UserBean;
import com.SmHome.beans.UsrLogInfo;
import com.SmHome.beans.WHUserBean;
import com.SmHome.supporting.EncryptDecrypt;

public class RegisterDAO {
	public HashMap<String, String> registerHome(HomeBean homebean) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		int homeId=-999;
		String homeIdtemp="";
		HashMap<String, String> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			DBHelper dbhelper=new DBHelper();
			String type=dbhelper.gethomeCode(session,homebean.getType());
			 homeId=dbhelper.getCount(session, "home","N/A");
			 homeIdtemp="H"+(homeId+1);
			 homebean.setId(homeIdtemp);
			homebean.setType(type);
			session.save(homebean);
			System.out.println("AFTER");
			txt.commit();
			response.put("key",homeIdtemp);
			FunctionalBean functionalbean=dbhelper.getDetailsOfRooms(session,type);
			
			
					
			int ret=dbhelper.createRooms(session,functionalbean, homeIdtemp);
			if(ret==1)
			{
				response.put("key", homeIdtemp);
			}
			else
			{
				response.put("key", "-999");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			session.close();
			response.put("key", "0");

		} finally {
			session.close();
		}
		return response;

	}

	public HashMap<String, String> registerAdmin(HomeAdminBean homeadminbean) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		HomeBean homeBean = null;
		String type="";
		HashMap<String, String> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			 txt=session.beginTransaction();
			 txt.begin();
			 session.save(homeadminbean);
			// txt.commit();
			 Criteria crit = session.createCriteria(HomeBean.class);
				Criterion cn = null;
				//Criterion cn1 = null;
				List l = null;

				cn = Restrictions.eq("id", new String(homeadminbean.getHomeId()));
				crit.add(cn);
				l = crit.list();

				if (l.size() != 0) {
					// HashMap<String ,Double> hst=new HashMap<>();
					Iterator it = l.iterator();
					System.out.println("inside if");
					while (it.hasNext()) {
						
					 homeBean =(HomeBean) it.next();
					 type=homeBean.getType();
						//memberbean = (MembersBean) it.next();
					}
				}
				DBHelper dbhelper=new DBHelper();
				int count=dbhelper.getCount(session,"user",homeadminbean.getHomeId());
					UserBean userbean=new  UserBean();
					userbean.setId("U"+(count+1));
					userbean.setEmail(homeadminbean.getEmail());
					userbean.setAccessLevel("F");
					userbean.setHomeId(homeadminbean.getHomeId());
					userbean.setName(homeadminbean.getAdminFullName());
					userbean.setPassword(homeadminbean.getPassword());
					userbean.setPhone(homeadminbean.getPhone());
					userbean.setStatus("A");
			System.out.println("AFTER");
				session.save(userbean);
			 txt.commit();
			

			

			response.put("key", "1");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			session.close();
			response.put("key","-1");

		} finally {
			session.close();
		}
		return response;

	}

	public HashMap<String, Integer> registerMember(MembersBean membersbean) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		HashMap<String, Integer> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			String password = EncryptDecrypt.encrypt(membersbean.getPassword());
			membersbean.setPassword(password);
			session.save(membersbean);

			System.out.println("AFTER");

			txt.commit();

			response.put("key", 1);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			session.close();
			response.put("key", 0);

		} finally {
			session.close();
		}
		return response;

	}

	public HashMap<String, Integer> insertPersonalDetails(PersonalDetailsBean psb) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		HashMap<String, Integer> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
			txt.begin();
			session.save(psb);

			System.out.println("AFTER");

			txt.commit();

			response.put("key", 1);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			session.close();
			response.put("key", 0);

		} finally {
			session.close();
		}
		return response;

	}

	public HashMap<String, Integer> getAuthenticated(HashMap<String, String> inputDetails) {/*
		String emailStatus = "";
		Session session = null;
		HashMap<String, Integer> response = new HashMap<>();
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		Transaction txt = null;
		// session=cfg.buildSessionFactory().getCurrentSession();
		session = cfg.buildSessionFactory().openSession();
		try {

			if (inputDetails.get("password").equalsIgnoreCase("SMHOME")) {
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				UsrLogInfo usr = new UsrLogInfo();
				usr.setHomeId(0);
				usr.setUsrId(1);
				usr.setActivity("I");
				usr.setLogTime(ts);
				txt = session.beginTransaction();
				txt.begin();
				session.save(usr);
				txt.commit();
				System.out.println("Welcome");
			} else {

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
						MembersBean memberbean = (MembersBean) it.next();
						System.out.println(memberbean.getPassword());
						System.out.println(inputDetails.get("password"));
						if (inputDetails.get("password").equalsIgnoreCase(memberbean.getPassword())) {
							Timestamp ts = new Timestamp(System.currentTimeMillis());
							UsrLogInfo usr = new UsrLogInfo();
							usr.setHomeId(0);
							usr.setUsrId(1);
							usr.setActivity("I");
							usr.setLogTime(ts);
							txt = session.beginTransaction();
							txt.begin();
							session.save(usr);
							txt.commit();
							response.put("key", 1);
						}
					}

				}

				System.out.println("AFTER");

				session.close();

				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			session.close();
			response.put("key", -1);
			return response;

		}
		return response;
	*/
		
	return null;}

	public HashMap<String, Integer> getLocationByHomeId(HashMap<String, String> inputDetails) {

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
					MembersBean memberbean = (MembersBean) it.next();

					if (EncryptDecrypt.encrypt(inputDetails.get("password"))
							.equalsIgnoreCase(memberbean.getPassword())) {
						response.put("key", 1);
					}
				}

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

	public HashMap<String, HashMap<String,String>> insertUserDetails(HashMap<String, String> userDetails) throws Exception {
		//String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		String homeId="";
		HashMap<String, HashMap<String,String>> response = new HashMap<>();
		HashMap<String,String> secondaryResponse=new HashMap<>();
		DBHelper db=new DBHelper();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			session = cfg.buildSessionFactory().openSession();
			txt = session.beginTransaction();
				txt.begin();
				DBHelper dbhelper=new DBHelper();
				int count=dbhelper.getCount(session,"user",userDetails.get("homeId"));
				UserBean userbean=new  UserBean();
				userbean.setId("U"+(count+1));
				userbean.setEmail(userDetails.get("email"));
				userbean.setHomeId(userDetails.get("homeId"));
				userbean.setName(userDetails.get("name"));
				userbean.setPhone(userDetails.get("phone"));
				userbean.setPassword(userDetails.get("password"));
				userbean.setStatus("A");
				userbean.setAccessLevel("P");
				session.save(userbean);
				txt.commit();
				secondaryResponse.put("access", "P");
				HashMap<String,String> secondaryResponse1=new HashMap<>();
				secondaryResponse1.put("homeId",userDetails.get("homeId"));
				response.put("key", secondaryResponse);
				response.put("homeId",secondaryResponse);
				HashMap<String, String> roomDetails=db.getDetails(session,userDetails.get("homeId"));
				response.put("Rooms",roomDetails);
				for ( Map.Entry<String, String> entry : roomDetails.entrySet()) {
				    HashMap<String,String> deviceDetails=db.getDeviceDetails(session, homeId, entry.getKey());
				    response.put(entry.getKey(), deviceDetails);
				    // do something with key and/or tab
				}
			} 

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			session.close();
			HashMap<String, String> hmap=new HashMap<>();
			hmap.put("access","-1");
			response.put("key", hmap);

		} finally {
			session.close();
		}
		return response;

	
		
		}
	
	public HashMap<String, Integer> registerMemberWH(WHUserBean whuserbean) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		HashMap<String, Integer> response = new HashMap<>();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			
			Criteria crit = session.createCriteria(WHUserBean.class);
			Criterion cn =null;
			//Criterion cn1 =null;
			List l=null;
		 	  
				cn=Restrictions.eq("email",new String(whuserbean.getEmail()));
				//cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			//crit.add(cn1);
		l=crit.list();
		if(l.size()>0)
		{
			response.put("key", -999);
		}
		else
		{
			String hql = "select count(*) from  WHUserBean" ;
		Query query = session.createQuery(hql);
		List results=query.list();
		
			txt = session.beginTransaction();
			txt.begin();
			whuserbean.setUserId("Autoi-US-"+results.size());
			session.save(whuserbean);

			System.out.println("AFTER");

			txt.commit();

			response.put("key", 1);
		}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			//session.close();
			response.put("key", -1);

		} finally {
			session.close();
		}
		return response;

	}
	
	
	public HashMap<String, HashMap<String, String>> googleSingInWH(WHUserBean whuserbean) throws Exception {
		String emailStatus = "";
		Session session = null;
		Transaction txt = null;
		HashMap<String, HashMap<String,String>> response = new HashMap<>();
		HashMap<String,String> secondaryResponse=new HashMap<>();
		DBHelper db=new DBHelper();
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			// session=cfg.buildSessionFactory().getCurrentSession();

			session = cfg.buildSessionFactory().openSession();
			
			Criteria crit = session.createCriteria(WHUserBean.class);
			Criterion cn =null;
			//Criterion cn1 =null;
			List l=null;
		 	  
				cn=Restrictions.eq("email",new String(whuserbean.getEmail()));
				//cn1=Restrictions.eq("password",new String(inputDetails.get("password")));
			crit.add(cn);
			//crit.add(cn1);
		l=crit.list();
		WHUserBean  usb= null;
		if(l.size()>0)
		{
				
			Iterator it = l.iterator();
			System.out.println("inside if");
			while (it.hasNext()) {
				
			 usb=(WHUserBean) it.next();
			//accessLevel=usb.getAccessLevel();
				//memberbean = (MembersBean) it.next();
			}
			//secondaryResponse.put("access", "P");
			HashMap<String,String> secondaryResponse1=new HashMap<>();
			secondaryResponse1.put("homeId","1");
			//response.put("key", secondaryResponse);
			response.put("homeId",secondaryResponse1);
			HashMap<String, String> controllerDetails=db.getControllerDetails(session,usb.getUserId());
			response.put("controllers",controllerDetails);
			for ( Map.Entry<String, String> entry : controllerDetails.entrySet()) {
			    HashMap<String,String> deviceDetails=db.getDeviceDetailsFromController(session, entry.getKey());
			    response.put(entry.getKey(), deviceDetails);
			    // do something with key and/or tab
			}
			
			}
		
		else
		{
			String hql = "select count(*) from  WHUserBean" ;
		Query query = session.createQuery(hql);
		List results=query.list();
		
			txt = session.beginTransaction();
			txt.begin();
			whuserbean.setUserId("Autoi-US-"+(results.size()+1));
			session.save(whuserbean);

			System.out.println("AFTER");

			txt.commit();
			HashMap<String,String> secondaryResponse1=new HashMap<>();
			secondaryResponse1.put("homeId","1");
			//response.put("key", secondaryResponse);
			response.put("homeId",secondaryResponse1);
			HashMap<String, String> controllerDetails=db.getControllerDetails(session,whuserbean.getUserId());
			response.put("controllers",controllerDetails);
			for ( Map.Entry<String, String> entry : controllerDetails.entrySet()) {
			    HashMap<String,String> deviceDetails=db.getDeviceDetailsFromController(session, entry.getKey());
			    response.put(entry.getKey(), deviceDetails);
			    // do something with key and/or tab
			}
			//response.put("key", 1);
		}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			txt.rollback();
			HashMap<String, String> hmap=new HashMap<>();
			hmap.put("homeId","-1");
			response.put("homeId", hmap);
		} finally {
			session.close();
		}
		return response;

	}

}
