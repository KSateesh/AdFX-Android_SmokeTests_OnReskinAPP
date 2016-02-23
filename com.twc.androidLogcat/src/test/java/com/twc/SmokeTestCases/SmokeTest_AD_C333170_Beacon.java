package com.twc.SmokeTestCases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.General.TestMode;
import com.twc.General.setAddress_Location;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

@SuppressWarnings("unused")
public class SmokeTest_AD_C333170_Beacon extends Driver{

//public static void main(String args[]) throws IOException, InterruptedException  {
	
	@SuppressWarnings("deprecation")
	@Test
	public void Verify_CreativeID_ThirdPartyBeacon() throws Exception{
		
		// Set the App in Test Mode
		TestMode Ads_Test_Mode = new TestMode();
		Ads_Test_Mode.ChangeToTestMode();
		
		Thread.sleep(4000);
		
		Driver.property();
		PropertyFile.property();
	
		System.out.println("Verifying the LogFile_Path ::"+ properties.getProperty("LogFilePath"));

		ATUReports.add("Launch the app", false);

		// Calling the method to Set Address/Location in the APP
		  setAddress_Location sa = new setAddress_Location();
	      sa.setLocation();
		
		Thread.sleep(1000);

		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

		String line = "";
		String allLine = "";

		while ((line = r.readLine()) != null) {
			System.out.println("Sys data is ::" + line);
		}

		String FilePath = properties.getProperty("LogFilePath");

		Map<String, String> mapkeys = new HashMap<String, String>();

		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			FileInputStream fstream = new FileInputStream(FilePath);
			br = new BufferedReader(new InputStreamReader(fstream));
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
		
		ATUReports.add("Search for the CreativeID and ThirdPartyBeacon", false);
		
        String creativeID = sb.toString().substring(sb.toString().lastIndexOf("http response in string: creativeId:"));
							
//        creativeID = creativeID.substring(creativeID.indexOf("http"), creativeID.indexOf("-")-2);
        
        creativeID = creativeID.substring(creativeID.indexOf("creativeId:")+12, creativeID.indexOf("-")-2);
        
        
        if(!creativeID.isEmpty() && !creativeID.contains("null")){
        	
        	System.out.println("Creative ID is : "+creativeID);
        	ATUReports.add("Creative ID is : " +creativeID, false);
        
        }
        else {
        	
        	System.out.println("Creative ID is empty/null");
        	ATUReports.add("Creative ID is empty/null", false);
        	Assert.fail();
        }
        
        Thread.sleep(2000);
        
        String thirdPatyBeacon = sb.toString().substring(sb.toString().lastIndexOf("thirdPartyBeacon:"));
		
        thirdPatyBeacon = thirdPatyBeacon.substring(thirdPatyBeacon.indexOf("thirdPartyBeacon:")+18, thirdPatyBeacon.indexOf("-")-2);
        
        String expected = "http://google.com/?3rdParty"; 
        
       if(!thirdPatyBeacon.isEmpty() && !thirdPatyBeacon.contains("null")){
        	
        	System.out.println("ThirdPatyBeacon is : "+thirdPatyBeacon);
        	
        	ATUReports.add("ThirdPatyBeacon is : "+thirdPatyBeacon, false);
        	
        	Assert.assertEquals(thirdPatyBeacon, expected);
        	
        	System.out.println("ThirdPatyBeacon is matched with the expected");
        	
        	ATUReports.add("ThirdPatyBeacon is matched with the expected", false);
        	
        }
        else{
        
        	System.out.println("ThirdPatyBeacon is null/empty");
        	
        	ATUReports.add("ThirdPatyBeacon ID is empty/null", false);
        	
        	Assert.fail();
        }
        
		String clickthru = sb.toString().substring(sb.toString().lastIndexOf("clickthru:"));

		clickthru = clickthru.substring(clickthru.indexOf("clickthru:"),clickthru.indexOf(".com") + 4);

		if (!clickthru.isEmpty() || !clickthru.contains("null")) {

			System.out.println("clickthru text is : " + clickthru);
			
			ATUReports.add("clickthru text is : " + clickthru, false);
		
		} else {

			System.out.println("clickthru is null/empty");
		
			ATUReports.add("clickthru is null/empty", false);
			
			Assert.fail();
		}

              System.out.println("Verify Creative_Id and ThirdParty_Beacon case ended");	
				
	}

	
}

	









