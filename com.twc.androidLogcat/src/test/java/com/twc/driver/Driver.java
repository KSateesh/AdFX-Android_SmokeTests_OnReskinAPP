package com.twc.driver;

	import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
	

	public class Driver extends PropertyFile {

		@SuppressWarnings("rawtypes")
		protected static AppiumDriver Ad ;
		
//		protected static ExtentReports reporter;
//		protected static ExtentTest logger;
//		    
//		static int i=1;
//		static String filepath = "/Users/monocept/Desktop/logs/AndroidLogs/com.weather.SmokeTest/Files/TestReport"+i+".html";
//	    
//		public static ExtentReports getInstance() {
//	    
//			if (reporter == null) {
//	        
//				reporter = new ExtentReports(filepath, false);
//	            
//	            // Setting the Report Header, Title and Browser Name on Reports
//	        	reporter.config()
//	                .documentTitle("TestReport_iOS")
//	                .reportName("The Weather Channel - iOS Smoke Test Report")
//	                .reportHeadline("iOS Smoke Test Report_Automation Report- TWC");
//	          
//
//	        	Map<String, String> sysInfo = new HashMap<String, String>();
//	        	sysInfo.put("Selenium Version", "2.46");
//	        	sysInfo.put("Environment", "Prod");
//	        	sysInfo.put("Device Name", "Prod");
//	        	sysInfo.put("iOS Version", "Prod");
//
//	        	reporter.addSystemInfo(sysInfo);
//	            
//	        	// Setting the DeviceName,Version on Reports
//	        	
//	        	reporter
//	        	.addSystemInfo("Environment", "Prod")
//	        	.addSystemInfo("Selenium Version", "2.46")
//	            .addSystemInfo("Device Name", "iPhone 6")
//	            .addSystemInfo("iOS Version", "8.4");
//	        	
//	        }
//	        return reporter;
//	    }

	}
	
