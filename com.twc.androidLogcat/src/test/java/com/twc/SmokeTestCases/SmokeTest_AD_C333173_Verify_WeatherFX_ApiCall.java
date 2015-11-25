package com.twc.SmokeTestCases;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

@SuppressWarnings("unused")
public class SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall extends Driver{
	
	public static String wfxtg ;
	public static List<String> wfxcontainer;
	
	public static List<String>  Adwfxdata ;
	public static String wfxids = null;
	public static String Currentlist[] = null;
	
	public void verify_WeatherFX_Apicall_On_FreshLaunch() throws ParseException, Exception {
		//reading file from Property file
//		 Driver.property();
//			PropertyFile.property();

		System.out.println("Verification of WeatherFX Call Test_Case Started");
		
		String adbPath = properties.getProperty("adbPath");
		
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		
		System.out.println("Writing App logs to LogFile");
		
		//Wait for 20 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+el.getText());

		Swipe.swipe();
		Swipe.swipe();
		
		MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

		WebDriverWait wait1 = new WebDriverWait(Ad, 4);
		
		wait1.until(ExpectedConditions.visibilityOf(AdEle));
	
		if(AdEle.isDisplayed())
		{
			System.out.println("Feed-1 Ad is displayed");
		}

	//Reading the log file for feed_1 to verify WFXTG value	
		
		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

		String line = "";
		String allLine = "";
		
		while((line=r.readLine()) != null)
		{
			System.out.println("Sys data is ::"+line);


		}

		String FilePath = properties.getProperty("LogFilePath");

		  Map<String, String> mapkeys = new HashMap<String, String>();

		  try {
				FileInputStream fstream = new FileInputStream(FilePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						fstream));
				String strLine;

				// / read log line by line ------ strLine = br.readLines(6, 10); /
				StringBuffer sb = new StringBuffer("");
				 while ((strLine = br.readLine()) != null) {
				
				 // parse strLine to obtain what you want /
				 //System.out.println (strLine);
				 sb.append(strLine);
				
				 }
				

				 if(sb.toString().contains("slotName=weather.feed1")){
		      String req = sb.toString().substring( sb.toString().lastIndexOf("slotName=weather.feed1"));
		      req = req.substring(req.indexOf(",")+1,req.indexOf("}"));
		      String[] arrays = req.split(",");
		      System.out.println("Verifying the "+req);
		      for(String keys : arrays){
		    	  System.out.println(keys);
		    	  if(keys.contains("=")){
		    		  String[] key = keys.split("=");
			    	 // System.out.println(key[0] + "---"+key[1]);
			    	  mapkeys.put(key[0], key[1]);
		    	  }
		    	  
		    	  
		      }
		      for(Entry<String, String> entryKeys : mapkeys.entrySet()){
		    	  //System.out.println("key : "+entryKeys.getKey() + "----"+"value:"+entryKeys.getValue());
		    	  if(entryKeys.getKey().contains("wfxtg"))
		    	  {
		    		  System.out.println("wfxtg values are :" + entryKeys.getValue());
		    		  
		    		  // Assert on WFX TG value
		    		  Assert.assertNotNull(entryKeys.getValue());
		    		  
		    	  }
		    	 
		      }
		 
					 }
				 br.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		

		System.out.println("Verification of wfxtg test case done");


	}
}

