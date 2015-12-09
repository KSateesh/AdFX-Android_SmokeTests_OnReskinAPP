package com.twc.SmokeTestCases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

public class SmokeTest_AD_C333174_FactualCall_RE extends Driver {

	@SuppressWarnings("deprecation")
	public void verify_facualcal_onfresh_launch() throws InterruptedException, IOException
	{
		//Reading file from Property file
		    Driver.property();
			PropertyFile.property();

			System.out.println("Verification of Factual Cal Test_Case Started");
			
			String adbPath = properties.getProperty("adbPath");
			String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
			Process p = Runtime.getRuntime().exec(str);
			
			System.out.println("Debug command is done");
		
			String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
			Process p1 = Runtime.getRuntime().exec(str1);
			System.out.println("Writing App logs to LogFile");
			
			ATUReports.add("Launch the app",false);
			
			try {
			//Wait for 20 sec for element presence
			WebDriverWait wait = new WebDriverWait(Ad, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
			
			//Temperature  Element
			MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
			System.out.println("Temp : "+el.getText());
        
			ATUReports.add("Scroll to Feed-1 Ad", false);
			
			Swipe.swipe();
			Swipe.swipe();
			
			} catch (Exception e){
//				System.out.println("Exception message :: "+e);
			}
			
			Thread.sleep(2000);
					
			MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

			WebDriverWait wait1 = new WebDriverWait(Ad, 4);
			
			wait1.until(ExpectedConditions.visibilityOf(AdEle));
		
			if(AdEle.isDisplayed())
			{
				System.out.println("Feed-1 Ad is displayed");
				ATUReports.add("Feed-1 Ad is present", false);
			}

			Thread.sleep(2000);
		//Reading the log file for feed_1 to verify PubAd Faud and Fgeo value	
	
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
			
			//Verify the Factual_API_Call is present in Logs
			String factualCall =null;
			if (sb.toString().contains("https://location.wfxtriggers.com/geopulse/")) {
				factualCall = sb.toString().substring(sb.toString().lastIndexOf("https://location.wfxtriggers.com/geopulse/7620026f-cfb6-4d0c-9f8e-434ff0cd34d0?audience=true&proximity=true"));
				factualCall = factualCall.substring(factualCall.indexOf("http"), factualCall.indexOf("proximity")+14);
				System.out.println("Factual API call is present and the url is : \n"+factualCall);
				ATUReports.add("Factual API Call is present and the url is : \n"+factualCall,false);
			}
		
			List<String> pubad_faudvalues = new ArrayList<String>();
			List<String> pubad_fgeovalues = new ArrayList<String>();
			
			String req=null;
			if (sb.toString().contains("slotName=weather.feed1")) {
			    req = sb.toString().substring(sb.toString().lastIndexOf("slotName=weather.feed1"));
				req = req.substring(req.indexOf(",") + 1, req.indexOf("}"));
				String[] arrays = req.split(", ");
				System.out.println("Verifying the " + req);
				for (String keys : arrays) {
					System.out.println(keys);
					if (keys.contains("=")) {
						String[] key = keys.split("=");
						// System.out.println(key[0] + "---"+key[1]);
						mapkeys.put(key[0], key[1]);
					}
				}
								
				String faudValue=null;
				String fgeoValue=null;						
				for (Entry<String, String> entryKeys : mapkeys.entrySet()) {					
					// Verify FAUD Value
					if (entryKeys.getKey().contains("faud")) {
						faudValue = entryKeys.getValue();
						Assert.assertNotNull(faudValue);
						System.out.println("FAUD value is present");
						System.out.println("faud values are :" + faudValue);
						pubad_faudvalues.add(faudValue);
					}
					// Verify FGEO Value					
					if (entryKeys.getKey().contains("fgeo")) {
						fgeoValue = entryKeys.getValue();
						Assert.assertNotNull(fgeoValue);
						System.out.println("FGEO value is present");
						System.out.println("FGEO vaules are :" + fgeoValue);
						pubad_fgeovalues.add(fgeoValue);
					}
				}
			}

			ATUReports.add("Verify the Factual values(FAUD,FGEO) in Feed_1 Call", false);
			String pubad_faud = pubad_faudvalues.toString();
			System.out.println("PubAd_FAUD Values "+ pubad_faud.toString());
			ATUReports.add("FAUD value is present", false);
			
			String pubad_fgeo = pubad_fgeovalues.toString();
			System.out.println("PubAd_FGEO Values "+ pubad_fgeo.toString());
			ATUReports.add("FGEO value is present", false);
			
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}



		System.out.println("Verification of FactualCall test case done");
		
				
	}
}

