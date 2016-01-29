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
import com.twc.General.app_Kill_Relaunch;
import com.twc.driver.Driver;

public class SmokeTest_AD_C33318_HealthModule_Allergy extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_SpotLightAd_present_on_ColdFlu_section() throws Exception {
		
		//Set the context of APP to Native
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		ATUReports.add("Launch the app", false);

		//If the APP in prod mode then have to tap on Back Button from Cold & Flu section
//		try {
//			Ad.findElementByAccessibilityId("Navigate up").click();
//			System.out.println("Tapped on back button on Cold & Flu page, to make sure the APP is on Health Module");
//		}catch(Exception e){
//			//System.out.println("Error message : "+e);
//		}
		
		System.out.println("Searching for Health Module to tap on Allergy section");
		
		ATUReports.add("Scroll to Health Module to tap on Allergy section", false);

		int MAX_SWIPES = 10;
		for (int i = 0; i<MAX_SWIPES; i++) {

			WebElement healthModule = null;

			try {
//				healthModule = Ad.findElementByName("Health");
				healthModule = Ad.findElementById("allergy_dial");
			} catch (Exception e) {
				// System.out.println(e);
			}

			if (healthModule != null && healthModule.isDisplayed()) {
				
				System.out.println("Health module is present and tap on Allergy section");
				
				ATUReports.add("Health module is present and tap on Allergy section",false);

				//Click in Allergy section
				Ad.findElementById("allergy_dial").click(); 

				Thread.sleep(10000);
				
				//To verify the page is on Allergy page Under Health Module
				String extendDaily = Ad.findElementById("toolbar_title").getText(); //com.weather.Weather:id/toolbar_title
				System.out.println("Text : "+extendDaily);
				if(extendDaily.contains("Allergies"))
				{
					System.out.println("On Extended Allergies page");
					ATUReports.add("On Extended Allergies page",false);
				}
				
				//try{
				//Verifying the SpotLight Ad on Allergy page
			MobileElement spotLightAd_Allergy = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");
				//MobileElement spotLightAd_Allergy = (MobileElement) Ad.findElementByClassName("android.view.View"); 
				WebDriverWait wait1 = new WebDriverWait(Ad,10);
				
				wait1.until(ExpectedConditions.visibilityOf(spotLightAd_Allergy));

				if (spotLightAd_Allergy.isDisplayed()) {

					System.out.println("SpotLight Ad is present on Allergy page");

					ATUReports.add("SpotLight Ad is present on Allergy ",false);

				} 
//				else {
//					System.out.println("SpotLight Ad is NOT present on Allergy page - Else ");
//				}
//				} catch(Exception e){
//					System.out.println("SpotLight Ad is NOT present on Allergy page  - Catch");
//				}
				Thread.sleep(2000);
				Swipe.swipe();
//				try{
				//Verifying the Big Banner Ad on Allergy page
				MobileElement bigBannerAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");
//				MobileElement bigBannerAd = (MobileElement) Ad.findElementByClassName("android.view.View"); 
				
				WebDriverWait wait2 = new WebDriverWait(Ad,10);
				
				wait2.until(ExpectedConditions.visibilityOf(bigBannerAd));

				if (bigBannerAd.isDisplayed()) {

					System.out.println("Big Banner Ad is present on Allergy page");

					ATUReports.add("Big Banner Ad is present on Allergy page ",false);

					Thread.sleep(2000);

					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();

					break;
				} 
//				else { 
//					System.out.println("Big Banner Ad is NOT present on Allergy page");
//				}
//				} catch(Exception e){
//					System.out.println("SpotLight Ad is NOT present on Allergy page");
//				}
				

			} else {
				System.out.println("Health Module is NOT present and scrolling down");

				Swipe.swipe();

			}
		}

	}

}
