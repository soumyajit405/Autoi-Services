package com.SmHome.supporting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ActionHelper {
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	public int getActionCreated(String email,String status,String cid,int did,String source) throws SQLException, ClassNotFoundException
	{
		//JDBCConnection connref =new JDBCConnection();
		con = JDBCConnection.getOracleConnection();
		PreparedStatement pstmt = null;
		String userId="";
		if(con!=null)
		{
				String query="select user_id from wh_user where email=?";
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,email);
				 ResultSet rs= pstmt.executeQuery();
				 while(rs.next())
				 {
					 userId=rs.getString(1);
				 }
				 
				populateDeviceLogData(status, con,cid,did,userId,source); 
			   
		}
		return 1;
		}
	
	public HashMap<String,Integer> getMetricsData(String cid,String did,String year) throws SQLException, ClassNotFoundException
	{
		//JDBCConnection connref =new JDBCConnection();
		con = JDBCConnection.getOracleConnection();
		PreparedStatement pstmt = null;
		String userId="";
		LinkedHashMap<String,Integer> hmap=new LinkedHashMap<>();
		CommonUtil cutils=new CommonUtil();
		int status=0;
		if(con!=null)
		{
			QueryUtils quitls=new QueryUtils();
			ArrayList<String> queryList=quitls.getQueryList();
			
			for(int i=0;i<queryList.size();i++)
			{
				status=0;
				String [] yearData=quitls.getYear(i, year).split("&");
				 pstmt=con.prepareStatement(queryList.get(i));
				 pstmt.setString(1,yearData[0]);
				 pstmt.setString(2,yearData[1]);
				 pstmt.setString(3,cid);
				 pstmt.setString(4,did);
				 ResultSet rs= pstmt.executeQuery();
				 while(rs.next())
				 {
					 status=1;
					 hmap.put(cutils.getMonth(i),rs.getInt(1));
				 }
				 if(status==0)
				 {
					 hmap.put(cutils.getMonth(i),0);
				 }
			}
			
				
				
				 
			//	populateDeviceLogData(status, con,cid,did,userId,source); 
			   
		}
		return hmap;
		}

 public int populateDeviceLogData(String status,Connection con,String cid,int did,String uid,String source) throws SQLException
 {
	 String query="";
	 PreparedStatement pstmt = null; 
	 ResultSet rs= null;
	 int onTime=-999;
	 Timestamp ts=new Timestamp(System.currentTimeMillis());
	 Timestamp onTimestamp=null;
	 if(status.equalsIgnoreCase("1"))
	 {
		 query="select on_time from wh_device_log_info where status ='0' and controller_id=? and device_id=? and user_id=? order by effective_date desc limit 1";
		 pstmt=con.prepareStatement(query);
		  pstmt.setString(1,cid);
		  pstmt.setInt(2,did);
		  pstmt.setString(3,uid);
		  rs= pstmt.executeQuery();
		  while(rs.next())
		  {
			  onTime=rs.getInt(1);
		  }
		  if(onTime==-999)
		  {
			  query="insert into wh_device_log_info(controller_id,user_id,device_id,status,source,effective_date,on_time) values(?,?,?,?,?,?,?)";
			  pstmt=con.prepareStatement(query);
			  pstmt.setString(1,cid);
			  pstmt.setString(2,uid);
			  pstmt.setInt(3,did);
			  pstmt.setString(4,status);
			  pstmt.setString(5,source);
			  pstmt.setTimestamp(6,ts);
			  pstmt.setInt(7,0);
			 
			  pstmt.execute();
		  }
		  else
		  {
			  query="insert into wh_device_log_info(controller_id,user_id,device_id,status,source,effective_date,on_time) values(?,?,?,?,?,?,?)";
		  pstmt=con.prepareStatement(query);
		  pstmt.setString(1,cid);
		  pstmt.setString(2,uid);
		  pstmt.setInt(3,did);
		  pstmt.setString(4,status);
		  pstmt.setString(5,source);
		  pstmt.setTimestamp(6,ts);
		  pstmt.setInt(7,onTime);
		 
		  pstmt.execute();
			  
		  }
	 }
	 
	 else
	 {
		 query="select on_time,effective_date from wh_device_log_info where status ='1' and controller_id=? and device_id=? and user_id=? order by effective_date desc limit 1";
		 pstmt=con.prepareStatement(query);
		  pstmt.setString(1,cid);
		  pstmt.setInt(2,did);
		  pstmt.setString(3,uid);
		  rs= pstmt.executeQuery();
		  while(rs.next())
		  {
			  onTime=rs.getInt(1);
			  onTimestamp=rs.getTimestamp(2);
		  }
		  if(onTime==-999)
		  {
			  query="insert into wh_device_log_info(controller_id,user_id,device_id,status,source,effective_date,on_time) values(?,?,?,?,?,?,?)";
			  pstmt=con.prepareStatement(query);
			  pstmt.setString(1,cid);
			  pstmt.setString(2,uid);
			  pstmt.setInt(3,did);
			  pstmt.setString(4,status);
			  pstmt.setString(5,source);
			  pstmt.setTimestamp(6,ts);
			  pstmt.setInt(7,0);
			 
			  pstmt.execute();
		  }
		  else
		  {
			  
			 long timedifference=(ts.getTime()-onTimestamp.getTime())/(60*1000); 
			  query="insert into wh_device_log_info(controller_id,user_id,device_id,status,source,effective_date,on_time) values(?,?,?,?,?,?,?)";
		  pstmt=con.prepareStatement(query);
		  pstmt.setString(1,cid);
		  pstmt.setString(2,uid);
		  pstmt.setInt(3,did);
		  pstmt.setString(4,status);
		  pstmt.setString(5,source);
		  pstmt.setTimestamp(6,ts);
		  pstmt.setInt(7,onTime+(int)timedifference);
		 
		  pstmt.execute();
			  
		  }
	 }
return 1;
}
 }
