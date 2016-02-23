package com.twc.SmokeTestCases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MultiAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import atu.testng.reports.ATUReports;

import com.relevantcodes.extentreports.LogStatus;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


public class SmokeTest_AD_C333179_Verify_PullToRefresh extends Driver {

	@SuppressWarnings({ "deprecation", "unused" })
	public void Verify_PulltoRefresh() throws IOException, InterruptedException
	{
		// Setting the Native_App context
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		//Read data from PropertyFile
		Driver.property();
		PropertyFile.property();

		//Run the 'Debug' and 'Write logs to LogFile' Command
		String adbPath = properties.getProperty("adbPath");
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		System.out.println("Debug command is done");

		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		System.out.println("Writing App logs to LogFile");

		ATUReports.add("Launch the app",false);

		//Wait for 10 secs for 'Temperature' Element Presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));

		//Temperature element
		ATUReports.add("On CC Screen,Temperature is present", false);
		MobileElement temp = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+temp.getText());

		//HILO element
		MobileElement hilo = (MobileElement) Ad.findElementById("com.weather.Weather:id/hilo");
		System.out.println("hilo : "+hilo.getText());

		ATUReports.add("On CC Screen,Pull the screen to REFRESH", false);
		TouchAction action1 = new TouchAction(Ad);
		action1.longPress(temp).moveTo(hilo).release().perform();

		
		System.out.println("Pull the screen to REFRESH is done");

		Thread.sleep(1000);

		//Read data from LogFile and verify the Branded BackGround Call
		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

		String line = "";
		String allLine = "";

		while((line=r.readLine()) != null)
		{
				System.out.println("Sys data is ::"+line);

		}

		String FilePath = properties.getProperty("LogFilePath");


		Map<String, String> mapkeys = new HashMap<String, String>();
		StringBuffer sb=null;

		try {
			FileInputStream fstream = new FileInputStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			sb = new StringBuffer("");
			while ((strLine = br.readLine()) != null) {
				// parse strLine to obtain what you want /
				//System.out.println (strLine);
				sb.append(strLine);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Verify the BB call is present or not in APP Logs file
		String req1 = sb.toString().substring(sb.toString().lastIndexOf("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb"));
		String req = req1.substring(req1.indexOf("URL"),req1.indexOf("lang")+4);
		// System.out.println("Request is ::"+req1);
		System.out.println("BB call value :: " + req);

		if (req1.contains("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb")) {
			System.out.println("Branded Background call is present");
			ATUReports.add("Branded Background call is present", false);

		} else {
			System.out.println("Branded Background call is NOT present");
			ATUReports.add("Branded Background call is NOT present", false);
			Assert.fail();
		}

		System.out.println("PulltoRefresh test case is done");			
	}

}

