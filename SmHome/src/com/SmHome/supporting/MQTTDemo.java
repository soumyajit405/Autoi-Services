package com.SmHome.supporting;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTDemo {

  MqttClient client;
  
  public MQTTDemo() {}

  public static void main(String[] args) {
    new MQTTDemo().doDemo("/test/light1","ON",1);
  }

  public void doDemo(String topic,String status,int deviceID) {
	    try {
	    	//System.setProperty("http.proxyHost", "www-proxy.idc.oracle.com");
	    //	System.setProperty("http.proxyPort", "80");

	      client = new MqttClient("tcp://45.113.138.18:1883", topic);
	      client.connect();
	      MqttMessage message = new MqttMessage();
	      //message.setPayload((deviceID+status).getBytes());
	      message.setPayload(status.getBytes());
	      client.publish(topic, message);
	      client.disconnect();
	      System.out.println("After");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}