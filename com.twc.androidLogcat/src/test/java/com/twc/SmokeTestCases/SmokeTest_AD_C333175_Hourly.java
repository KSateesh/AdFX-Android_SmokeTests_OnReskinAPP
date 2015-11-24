package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.General.Swipe;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

@SuppressWarnings("unused")
public class SmokeTest_AD_C333175_Hourly extends Driver {

	@SuppressWarnings("unused")
	public void verify_adpresent_onextendedHourly_page() throws Exception {

		// app kill and relaunch the app
//		app_Kill_Relaunch.Kill_realaunch();

		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		//To get the dimensions of the screen
		Dimension dimensions = Ad.manage().window().getSize();
		//System.out.println("dimensions :: "+dimensions);
		
		System.out.println("Searching for Hourly section");
		
		for (int i = 0; i < dimensions.getHeight(); i++) {

			WebElement hourly = null;

			try {
				hourly = Ad.findElementById("com.weather.Weather:id/hourly_title_textview");	
//				hourly = Ad.findElementByName("HOURLY");
				
			} catch (Exception e) {
				// System.out.println(e);	
			}

			if (hourly!= null && hourly.isDisplayed()) {
				
				System.out.println("Hourly section is displayed and tapping on the same");
				
				Ad.findElementById("com.weather.Weather:id/hourly_title_textview").click();
				Ad.findElementByName("HOURLY").click();

				MobileElement AdEle = (MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

				WebDriverWait wait1 = new WebDriverWait(Ad, 4);

				wait1.until(ExpectedConditions.visibilityOf(AdEle));

				if (AdEle.isDisplayed()) {

					System.out.println("Ad is displayed on Hourly-Extended page");

					Thread.sleep(2000);
					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();

				}break;
                
//				Thread.sleep(2000);
//				Ad.findElementByAccessibilityId("Navigate up").click();
//				break;

			} else {

				System.out.println("Hourly section is not present and scrolling down");
				
				Swipe.swipe();

			}

		}
	}
}
