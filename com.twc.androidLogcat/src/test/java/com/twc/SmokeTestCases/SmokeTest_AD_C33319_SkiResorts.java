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

import atu.testng.reports.ATUReports;

import com.twc.General.Swipe;
import com.twc.General.TestMode;
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

public class SmokeTest_AD_C33319_SkiResorts extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_SpotLightAd_present_on_SkiResorts() throws Exception {

	// Set the App in Test Mode
		 //TestMode Ads_Test_Mode = new TestMode();
		 //Ads_Test_Mode.ChangeToTestMode();

		//Set the context of APP to Native
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		ATUReports.add("Launch the app", false);
		
		//If the APP in prod mode then have to tap on Back Button from the previous page
//		try {
//			Ad.findElementByAccessibilityId("Navigate up").click();
//			//System.out.println("Tapped on back button to be previous page");
//		} catch (Exception e) {
//			// System.out.println("Error message : "+e);
//		}

		System.out.println("Searching for Ski Module and tap on \"ALL RESORTS\" link");
		
		ATUReports.add("Scroll to Ski Module and tap on \"ALL RESORTS\" link", false);

		int MAX_SWIPES = 10;
		for (int i = 0; i<MAX_SWIPES; i++) {

			WebElement skiModule = null;

			try {
//				skiModule = Ad.findElementById("ski_module_header");
				
				skiModule = Ad.findElementById("ski_module_cta");
			} catch (Exception e) {
				// System.out.println(e);
			}

			if (skiModule != null && skiModule.isDisplayed()) {
				
				System.out.println("Ski module is present and tap on \"ALL RESORTS\" link");
				
				ATUReports.add("Ski module is present and tap on \"ALL RESORTS\" link",false);

				//Ski Module section - Condition Icon        
				Ad.findElementById("ski_module_cta").click();

				Thread.sleep(2000);
				//To verify the page is on SkiResorts page
				String skiResorts = Ad.findElementById("toolbar_title").getText(); //com.weather.Weather:id/toolbar_title
				
				System.out.println("Text : "+skiResorts);
				
	         	if(skiResorts.contains("Resorts"))
				{
					System.out.println("On 'ALL RESORTS' page");
					ATUReports.add("On 'ALL RESORTS' page",false);
				}
				
				//Verifying the SpotLight Ad on skiResorts/ALL Resorts page
				
				MobileElement spotLightAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.view.View[1]");
				WebDriverWait wait1 = new WebDriverWait(Ad,10);
				
				wait1.until(ExpectedConditions.visibilityOf(spotLightAd));

				if (spotLightAd.isDisplayed()) {

					System.out.println("Ad is present on ALL Resorts page");

					ATUReports.add("Ad is present on ALL Resorts page",false);
                    
					Thread.sleep(2000);
					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();
					
					break;
				} 
//				else {
//					System.out.println("SpotLight Ad is NOT present on ALL Resorts page");
//				}
				
				Thread.sleep(1000);
				
			} else {
				System.out.println("Ski Module is NOT present and scrolling down");

				Swipe.swipe();

			}
		}

	}

}
