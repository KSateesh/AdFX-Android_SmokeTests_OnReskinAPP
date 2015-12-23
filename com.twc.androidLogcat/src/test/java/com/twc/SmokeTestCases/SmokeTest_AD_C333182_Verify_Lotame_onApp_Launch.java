package com.twc.SmokeTestCases;


import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

public class SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch extends Driver {
	
	public static String ids = null;
	
	@SuppressWarnings({ "unused", "unchecked", "deprecation" })
//	public static void main(String[] args) throws IOException, InterruptedException {
	public void Verify_LotameCall_onapp_launch_test() throws InterruptedException, ParseException, IOException
	{
		//reading file from Property file
		 Driver.property();
			PropertyFile.property();

	System.out.println("Verification of Lotame Call Test_Case Started");
		
		String adbPath = properties.getProperty("adbPath");
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		System.out.println("Writing App logs to LogFile");
		
		ATUReports.add("Launch the app",false);
		
		//Un-Comment the below try catch block,to run this test case as alone
		
//		try {
//		//Wait for 10 sec for element presence
//		WebDriverWait wait = new WebDriverWait(Ad, 10);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
//		
//		//Temperature  Element
//		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
//		System.out.println("Temp : "+el.getText());
//
//		Swipe.swipe();
//		Swipe.swipe();
//		} catch(Exception e){
//			//System.out.println("Exception message : "+e);
//		}
		
		Thread.sleep(2500);

		//Verify the Feed-1 Ad
		MobileElement AdEle =(MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");

		WebDriverWait wait1 = new WebDriverWait(Ad, 4);
		
		wait1.until(ExpectedConditions.visibilityOf(AdEle));
	
		if(AdEle.isDisplayed())
		{
			System.out.println("Feed_1 Ad is present");
			ATUReports.add("Feed_1 Ad is present",false);

		}

		Thread.sleep(2000);
				
		String pubsg=null;

		//Reading the log file for feed_1 to verify SG value
			
				BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

				String line = "";
				String allLine = "";

				while((line=r.readLine()) != null)
				{
					System.out.println("Sys data is ::"+line);
				}

				String FilePath = properties.getProperty("LogFilePath");

				StringBuffer sb=null;
				try {
					FileInputStream fstream = new FileInputStream(FilePath);
					BufferedReader br = new BufferedReader(new InputStreamReader(
							fstream));
					String strLine;

					// / read log line by line ------ strLine = br.readLines(6, 10); /
					 sb = new StringBuffer("");
					while ((strLine = br.readLine()) != null) {

						sb.append(strLine);

					}
					br.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
					Thread.sleep(1000);
									
					String[] arrays;
					String[] key;
					List<String> pubad_sgvalues = new ArrayList<String>();
					
					if(sb.toString().contains("slotName=weather.feed1")){
						String req = sb.toString().substring( sb.toString().lastIndexOf("slotName=weather.feed1"));
						req = req.substring(req.indexOf(",")+1,req.indexOf("}"));
						System.out.println("Verifing the "+req);
						arrays = req.split(" ");
						
						for(String keys : arrays){

							if(keys.contains("=")){
								 key = keys.split("=");
//								System.out.println("keys are :: "+key[0] + "---"+key[1]);
								if(key[0].contains("sg"))
								{															
									pubsg = key[1].toString();																		
									pubsg= pubsg.substring(0,pubsg.lastIndexOf(",")) ;
									pubsg=pubsg.replaceAll(",", ", ");
					    			pubad_sgvalues.add(pubsg);					    			
									break;
								} 
							}
						}
						System.out.println("pubad_sg values are :: " + pubad_sgvalues.toString());
						
					}
			  
					 //Verify the Lotame_API_Call is present in Logs
					  try{
							String lotameCall = sb.toString().substring(sb.toString().lastIndexOf("https://ad.crwdcntrl.net/"));
							lotameCall = lotameCall.substring(lotameCall.indexOf("https"), lotameCall.indexOf("net")+4);
							System.out.println("Ad_Lotame call is present and the url is : \n"+lotameCall);
							ATUReports.add("Ad_Lotame call is present and the url is : \n"+lotameCall,false);
						 }
					  catch(Exception e){
							String BCP_lotameCall = sb.toString().substring(sb.toString().lastIndexOf("https://bcp.crwdcntrl.net/"));
							BCP_lotameCall = BCP_lotameCall.substring(BCP_lotameCall.indexOf("https"), BCP_lotameCall.indexOf("net")+4);
							System.out.println("BCP_lotame call is present and the url is : \n"+BCP_lotameCall);
							ATUReports.add("BCP_lotame call is present and the url is : \n"+BCP_lotameCall,false);
						  }
				   
		               Thread.sleep(1000);
						// Capturing the Lotame Call Data (bcp.crwdcntrl.net)
						if(sb.toString().contains("response from server is {"+'"'+"Profile")){
						String lotame= null;
						if(sb.toString().contains("response from server is {"+"\"Profile\""))
								{
					 lotame = sb.toString().substring(sb.toString().lastIndexOf("response from server is {"+'"'+"Profile"));
	    			 lotame = lotame.substring(lotame.indexOf("Audiences")+12,lotame.indexOf("}}}")+1);
					 System.out.println("Verifing the lotame call "+lotame);
							
								}
		
						Thread.sleep(1000);
						//Reading the Lotame Call from LogFile [From Audiences tag]					
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(lotame.toString());
						JSONObject jsonObject = (JSONObject) obj;
						JSONArray Audience = (JSONArray) jsonObject.get("Audience");
						String actual=null; 
						 List<String> idvalues = new ArrayList<String>();
						if (Audience!= null) {
				    			Iterator<JSONObject> AudienceIterator = Audience.iterator();
				    			while (AudienceIterator.hasNext()) {
				    			    JSONObject AudienceObject = (JSONObject) AudienceIterator.next();
				    			   String id = AudienceObject.get("id").toString();
				    			   System.out.println("Lotame_Audience id value is : " + id);
				    			   idvalues.add(id);
				    			}
						}	
						
						//Audience values from Lotame API Call
						ATUReports.add("Verify Audience values from Lotame API call",false);
						System.out.println("Lotame call Audience values are :: " + idvalues.toString());
		    			  actual = idvalues.toString().replace("[", "").replace("]", "");
		    			  ATUReports.add("Lotame API Call Audience values are :: "+actual,false);
		    			 
		    			boolean LotameCall_NullCheck=false;				    							    			
		    			LotameCall_NullCheck = actual.isEmpty();
		    		
		    			if(LotameCall_NullCheck == true){
		    				System.out.println("Lotame_API call Audience values are NULL");
		    				ATUReports.add("Lotame_API call Audience values are NULL",false);
		    				Assert.fail();
		    			}		

						//SG values from PUB_AD Call
			ATUReports.add("Verify PubAd_SG values from Feed_1 call", false);
			System.out.println("pubad_sg values are :: "+ pubad_sgvalues.toString());
			String expected = pubad_sgvalues.toString().replace("[", "").replace("]", "");
			ATUReports.add("PubAd_SG values are :: " + expected, false);

				// Asserting the PubAd_SG values with Lotame Call values
	    	
								for (int counter = 0; counter < idvalues.size(); counter++) { 
								    if (expected.contains(idvalues.get(counter))) {

								    	System.out.println("Lotame_API Audience value "+idvalues.get(counter)+" is present in the PubAD_SG_values");
								    	ATUReports.add("Lotame_API Audience value "+idvalues.get(counter)+" is present in the PubAD_SG_values",false);		
								    }
								    else{
								    	System.out.println("Lotame_API Audience value "+idvalues.get(counter)+" is NOT present in the PubAD_SG_values");
								    	ATUReports.add("Lotame_API Audience value "+idvalues.get(counter)+" is NOT present in the PubAD_SG_values",false);
								    	Assert.fail();
								    }
								} 
			    
						
					}
					
				System.out.println("Lotame Call test case is done");
        }
    }
