package com.SmHome.controller;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;

import javax.net.ssl.HostnameVerifier;
import javax.websocket.*;
import javax.websocket.server.*;

import com.SmHome.dao.DBHelper;

@ServerEndpoint("/ratesrv")
public class DeviceWebSocketServer {
 //queue holds the list of connected clients
 private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
 private static Hashtable<String,Session> homeSession=new Hashtable<>();
 
 private static Thread rateThread ; //rate publisher thread
 static
 {
//rate publisher thread, generates a new value for USD rate every 2 seconds.
 /* rateThread=new Thread(){
   public void run() {
    DecimalFormat df = new DecimalFormat("#.####");
    while(true)
    {
     double d=2+Math.random();     
     if(queue!=null)
      sendAll("USD Rate: "+df.format(d));    
     try {
      sleep(2000);
     } catch (InterruptedException e) {      
     }
    }
   };
  } ;
  rateThread.start();*/
 }
 @OnMessage
 public void onMessage(Session session, String msg) {
//provided for completeness, in out scenario clients don't send any msg.
  try {   
   System.out.println("received msg "+msg+" from "+session.getId());
   DBHelper dbhelper=new DBHelper();
  // dbhelper.loadHomeSession(session.getId(), msg);
   homeSession.put(msg,session);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
@OnOpen
 public void open(Session session) {
	System.out.println(session.getId());
  queue.add(session);
  
  System.out.println("New session opened: "+session.getId());
 }
  @OnError
 public void error(Session session, Throwable t) {
  queue.remove(session);
  System.err.println("Error on session "+session.getId());  
 }
 @OnClose
 public void closedConnection(Session session) { 
  queue.remove(session);
  System.out.println("session closed: "+session.getId());
 }
 private static void sendAll(String homeId,String id,String msg) {
  try {
   /* Send the new rate to all open WebSocket sessions */  
   ArrayList<Session > closedSessions= new ArrayList<>();
   Session session=homeSession.get(homeId);
   if(!session.isOpen())
   {
    System.err.println("Closed session: "+session.getId());
    closedSessions.add(session);
   }
   else
   {
   session.getBasicRemote().sendText(id+"&"+msg);
   }
   
  /* for (Session session : queue) {
    if(!session.isOpen())
    {
     System.err.println("Closed session: "+session.getId());
     closedSessions.add(session);
    }
    else
    {
    	if(session.getId().equalsIgnoreCase(homeSession.get(id)))
    	{
    		session.getBasicRemote().sendText(msg);
    		break;
    	}
    	
    	
    }    
   }*/
   queue.removeAll(closedSessions);
   System.out.println("Sending "+msg+" to "+queue.size()+" clients");
  } catch (Throwable e) {
   e.printStackTrace();
  }
 }
 public void sendData(String homeId,String id,String data)
 {
	 sendAll(homeId,id,data);
 }
}