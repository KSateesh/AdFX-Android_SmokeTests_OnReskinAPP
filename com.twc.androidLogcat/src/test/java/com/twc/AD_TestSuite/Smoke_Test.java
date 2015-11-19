package com.twc.AD_TestSuite;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.twc.AppiumAutoStart.Capabilities_android;
import com.twc.General.DeleteFile;
import com.twc.General.File_Exist;
import com.twc.General.app_Kill_Relaunch;
import com.twc.General.setAddress_Location;
import com.twc.General.toKnowBuildVersion;
import com.twc.SmokeTestCases.SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall;
import com.twc.SmokeTestCases.SmokeTest_AD_C333174_FactualCall;
import com.twc.SmokeTestCases.SmokeTest_AD_C333175_Hourly;
import com.twc.SmokeTestCases.SmokeTest_AD_C333175_Hurricane;
import com.twc.SmokeTestCases.SmokeTest_AD_C333176_Map;
import com.twc.SmokeTestCases.SmokeTest_AD_C333177_News;
import com.twc.SmokeTestCases.SmokeTest_AD_C333179_Verify_PullToRefresh;
import com.twc.SmokeTestCases.SmokeTest_AD_C333180_10Day_2;
import com.twc.SmokeTestCases.SmokeTest_AD_C333180_10Day;
import com.twc.SmokeTestCases.SmokeTest_AD_C333182_Verify_Lotame_onApp_Launch_2;
import com.twc.driver.Driver;

import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

@SuppressWarnings("unused")
public class Smoke_Test extends Driver{
	
	//Pull to Refresh
	@Test (priority=1, threadPoolSize = 1,invocationCount = 1)
	public void AD_C333179_Verify_PulltoRefresh() throws ParseException, IOException, InterruptedException
	{

		SmokeTest_AD_C333179_Verify_PullToRefresh pulltorefresh = new SmokeTest_AD_C333179_Verify_PullToRefresh();
		pulltorefresh.Verify_PulltoRefresh();
	}
	//Hourly Ad
	@Test(priority=6, threadPoolSize = 1,invocationCount = 1 )
	public void AD_C333175_Verify_Ad_On_HourlyExtended_page() throws Exception {
		
		SmokeTest_AD_C333175_Hourly hourlyExtend = new SmokeTest_AD_C333175_Hourly();
		hourlyExtend.verify_adpresent_onextendedHourly_page();

	}

	@BeforeTest
	public void Capabilities_Launch() throws Exception {
		
		 //Calling the capabilities method
		Capabilities_android cap = new Capabilities_android();
	    cap.dcap();
		
		//Delete the log existed file
		DeleteFile DF = new DeleteFile();
		File_Exist FE = new File_Exist();
		
		if(FE.fileexist()) {
			DF.deleteFile();

		} else {
			System.out.println("File not exist");
		}
	}
	
	@BeforeClass
	public void getBuildVersion() throws Exception {

		// Calling the method to know build version of the app class
//		toKnowBuildVersion buildVersion = new toKnowBuildVersion();
//		buildVersion.moreOptionsClick();

		// Calling the method to know build version of the app class
	  // setAddress_Location sa = new setAddress_Location();
	 // sa.setLocation();
		
	}

	@BeforeMethod
	public void App_Kill_ReLaunch() throws Exception {
		
		System.out.println("Killing the app and relaunch the app");
		app_Kill_Relaunch.Kill_realaunch();
		
	}
	
	
}
