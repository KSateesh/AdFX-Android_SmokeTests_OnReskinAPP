package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.General.Swipe;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

public class SmokeTest_AD_C333180_10Day extends Driver{


	@SuppressWarnings("unused")
	public void verify_adpresent_onextendedTenday_page() throws Exception
	{
		
		
		//app kill and relaunch the app
//		app_Kill_Relaunch.Kill_realaunch();

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");
	
		//To get the dimensions of the screen
				Dimension dimensions = Ad.manage().window().getSize();
				//System.out.println("dimensions :: "+dimensions); //2
      
				System.out.println("Searching for Daily section");
				
		for (int i = 0; i < dimensions.getHeight(); i++) 
		{
			
			WebElement tenday = null;

			try {
				tenday = Ad.findElementById("com.weather.Weather:id/tenday_title");
//				tenday = Ad.findElementByName("Daily"); 

			 } catch (Exception e) {
				// System.out.println(e);	
			 }
			
			if(tenday!= null && tenday.isDisplayed())	
			{ 
				System.out.println("Daily section is displayed and tapping on 15 DAYS button");
				
				try{
					
				 Ad.findElementById("com.weather.Weather:id/daily_more").click();
//					Ad.findElementByName("15 DAYS").click();

				}catch(NoSuchElementException e)
				{
					Ad.findElementByName("WEEKEND FORECAST").click();
				}


				MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

				WebDriverWait wait1 = new WebDriverWait(Ad, 4);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));
				
				if(AdEle.isDisplayed())
				{
					System.out.println("Ad is displayed on Extended_Daily_page");
					Thread.sleep(2000);
					
					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click(); 
					
				}break;

			}else
			{
                System.out.println("10Day section is not presented and scrolling down");
				
				Swipe.swipe();
				
			}
		}

		}

	}


