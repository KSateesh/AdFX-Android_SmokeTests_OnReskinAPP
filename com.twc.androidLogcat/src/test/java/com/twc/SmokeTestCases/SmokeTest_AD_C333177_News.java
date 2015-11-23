package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.General.Swipe;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

public class SmokeTest_AD_C333177_News extends Driver{
	
	public void verify_adpresent_onextendedNews_page() throws Exception
	{
		//app kill and relaunch the app
		//app_Kill_Relaunch.Kill_realaunch();

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");
		
		//To get the dimensions of the screen
		Dimension dimensions = Ad.manage().window().getSize();
		//System.out.println("dimensions :: "+dimensions); //7
		
		System.out.println("Searching for News section");
		
		for (int i=0; i<dimensions.getHeight(); i++)
		{

			WebElement news = null;

			try {
//				news = Ad.findElementById("com.weather.Weather:id/news_title");
				news = Ad.findElementByName("News");
				
			} catch (Exception e) {
				// System.out.println(e);	
			}

			if(news!=null && news.isDisplayed())
			{  
				System.out.println("News section is displayed and tapping on News Image");
	
//				Ad.findElementById("com.weather.Weather:id/news_image_icon").click();
				Ad.findElementByAccessibilityId("News icon").click();
				
				MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

				WebDriverWait wait1 = new WebDriverWait(Ad, 4);
				wait1.until(ExpectedConditions.visibilityOf(AdEle));
				if(AdEle.isDisplayed())
				{
					System.out.println("Ad present on Extended News page");
					Thread.sleep(2000);
					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();
					
				}break;

			}else
			{
               System.out.println("News section is not present and scrolling down");
				
				Swipe.swipe();
			}
		}
	}
}
