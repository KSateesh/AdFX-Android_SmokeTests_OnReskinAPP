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

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

public class SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch extends Driver {
	
	public static String ids = null;
	
	@SuppressWarnings({ "unused", "unchecked" })
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
		
		
		//Wait for 20 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+el.getText());
//		
//		Dimension dimensions = Ad.manage().window().getSize();
//		System.out.println("dimensions :: "+dimensions);
//		Thread.sleep(2000);
//		int scrollStart = 2300;
//		int scrollEnd = 30;
//		Thread.sleep(4000);
//		Ad.swipe(0, scrollStart, 0, scrollEnd, 2000);


		Swipe.swipe();
		Swipe.swipe();
		Thread.sleep(2000);

		
		MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

		WebDriverWait wait1 = new WebDriverWait(Ad, 4);
		
		wait1.until(ExpectedConditions.visibilityOf(AdEle));
	
		if(AdEle.isDisplayed())
		{
			System.out.println("Ad is displayed on Below Right Now Section");

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


				try {
					FileInputStream fstream = new FileInputStream(FilePath);
					BufferedReader br = new BufferedReader(new InputStreamReader(
							fstream));
					String strLine;

					// / read log line by line ------ strLine = br.readLines(6, 10); /
					StringBuffer sb = new StringBuffer("");
					while ((strLine = br.readLine()) != null) {

						sb.append(strLine);

					}
					
					String[] arrays;
					String[] key;
					List<String> pubad_sgvalues = new ArrayList<String>();
					
					if(sb.toString().contains("slotName=weather.feed1")){
						String req = sb.toString().substring( sb.toString().lastIndexOf("slotName=weather.feed1"));
						req = req.substring(req.indexOf(",")+1,req.indexOf("}"));
						System.out.println("Verifing the "+req);
						arrays = req.split(" ");
						
						for(String keys : arrays){
//							System.out.println("Keys ::"+keys);	
							if(keys.contains("=")){
								 key = keys.split("=");
//								System.out.println("keys are :: "+key[0] + "---"+key[1]);
								if(key[0].contains("sg"))
								{						
//									System.out.println("sg key [1] value is ::"+key[1]);									
									pubsg = key[1].toString();																		
									pubsg= pubsg.substring(0,pubsg.lastIndexOf(",")) ;
									pubsg=pubsg.replaceAll(",", ", ");
//					    			System.out.println("pubsg: "+ pubsg);
					    			pubad_sgvalues.add(pubsg);
//					    			System.out.println("pubad values are(updated) :: " + pubad_sgvalues.toString());					    			
									break;
								} 
							}
						}
						System.out.println("pubad_sg values are :: " + pubad_sgvalues.toString());
					}
						
		               Thread.sleep(2000);
						// Capturing the Lotame Call Data (bcp.crwdcntrl.net)
						if(sb.toString().contains("response from server is {"+'"'+"Profile")){
						String lotame= null;
						if(sb.toString().contains("response from server is {"+"\"Profile\""))
								{
					 lotame = sb.toString().substring(sb.toString().lastIndexOf("response from server is {"+'"'+"Profile"));
	    			 lotame = lotame.substring(lotame.indexOf("Audiences")+12,lotame.indexOf("}}}")+1);
					 System.out.println("Verifing the lotame call "+lotame);
							
								}
		
						Thread.sleep(2000);
						//Reading the Lotame Call from LogFile [From Audiences tag]					
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(lotame.toString());
						JSONObject jsonObject = (JSONObject) obj;
						JSONArray Audience = (JSONArray) jsonObject.get("Audience");
						 
						 List<String> idvalues = new ArrayList<String>();
						if (Audience!= null) {
				    			Iterator<JSONObject> AudienceIterator = Audience.iterator();
				    			while (AudienceIterator.hasNext()) {
				    			    JSONObject AudienceObject = (JSONObject) AudienceIterator.next();
				    			   String id = AudienceObject.get("id").toString();
				    			   System.out.println("id values : " + id);
				    			   idvalues.add(id);
				    			}
				    			
				    			System.out.println("idvalues are :: " + idvalues.toString());
				    			String actual = idvalues.toString().replace("[", "").replace("]", "");
				    			
				    			System.out.println("pubad_sg values are :: " + pubad_sgvalues.toString());
				    			String expected = pubad_sgvalues.toString().replace("[", "").replace("]", "");
				    			
//				    			System.out.println("actual: "+actual);
//				    			System.out.println("expected: "+expected);				    			
//				    			if(actual.equalsIgnoreCase(expected)){
//				    				System.out.println("true");
//				    			}
				    			
					Assert.assertEquals(actual, expected);
					System.out.println("PubAd_SG values and Lotame call Audience values are matched");
				    				    
						}
					}
					
					br.close();

				} catch (Exception e) {
					e.printStackTrace();
				}


				System.out.println("Verifying Lotame Call test case done");
			

        }
    }
