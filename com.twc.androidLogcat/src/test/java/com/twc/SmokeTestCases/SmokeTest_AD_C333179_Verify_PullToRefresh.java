package com.twc.SmokeTestCases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import com.relevantcodes.extentreports.LogStatus;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


public class SmokeTest_AD_C333179_Verify_PullToRefresh extends Driver {
	
	
	@SuppressWarnings({ "deprecation", "unused" })
	public void Verify_PulltoRefresh() throws IOException, InterruptedException
	{

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");
		
		//Read from PropertyFilePath
		Driver.property();
		PropertyFile.property();

		String adbPath = properties.getProperty("adbPath");
//		String[] str ={"/bin/bash", "-c", "/Users/monocept/Documents/adt-bundle-mac-x86_64-20130522/sdk/platform-tools/adb shell setprop log.tag.TwcAd DEBUG"};
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
//		String[] str1 ={"/bin/bash", "-c", "/Users/monocept/Documents/adt-bundle-mac-x86_64-20130522/sdk/platform-tools/adb -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		System.out.println("Logfile creation is done");
		
		//Wait for 20 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
        
		//Temperature element
		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+el.getText());
		
		//HILO element
		MobileElement el1 = (MobileElement) Ad.findElementById("com.weather.Weather:id/hilo");
		System.out.println("hilo : "+el1.getText());

		TouchAction action1 = new TouchAction(Ad);
		action1.longPress(el).moveTo(el1).release().perform();
		
		System.out.println("Pulled the screen to REFRESH");
		
		Thread.sleep(1000);

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
			
		//	System.out.println("Sb is  ::"+sb.toString());
			

			String req1 = sb.toString().substring( sb.toString().lastIndexOf("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb"));
					String	req = req1.substring(req1.indexOf("&")+1,req1.indexOf("adid"));
					System.out.println("Request is ::"+req1);
					System.out.println("Request data is ::"+req);
					
					if(req1.contains("URL=https://pubads.g.doubleclick.net/gampad/adx?iu=%2F7646%2Fapp_android_us%2Fdisplay%2Fbb"))
					{
						System.out.println("Verified Branded Background call is present"); 

					}else
					{
						System.out.println("Branded Background call is not presented");
						

					}

				System.out.println("Case Ended");
			
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}


