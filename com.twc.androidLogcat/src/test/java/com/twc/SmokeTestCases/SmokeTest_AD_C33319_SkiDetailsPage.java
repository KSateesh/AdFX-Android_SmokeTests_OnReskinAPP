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

public class SmokeTest_AD_C33319_SkiDetailsPage extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_SpotLightAd_present_on_SkiDetails() throws Exception {

	// Set the App in Test Mode
//		 TestMode Ads_Test_Mode = new TestMode();
//		 Ads_Test_Mode.ChangeToTestMode();

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

		System.out.println("Searching for Ski Module to tap on the same");
		
		ATUReports.add("Scroll to Ski Module to tap on the same", false);

		int MAX_SWIPES = 10;
		for (int i = 0; i<MAX_SWIPES; i++) {

			WebElement skiDetails = null;

			try {
				skiDetails = Ad.findElementById("ski_module_header");
			} catch (Exception e) {
				// System.out.println(e);
			}

			if (skiDetails != null && skiDetails.isDisplayed()) {
				
				System.out.println("Ski module is present and tap on Ski_Details section");
				
				ATUReports.add("Ski module is present and tap on Ski_Details section",false);

				//Ski Module section - Condition Icon        
				Ad.findElementById("ski_module_condition_icon").click();

				Thread.sleep(2000);
				//To verify the Ski Details page after tap on Ski Module
				String skiDetails_Text = Ad.findElementById("toolbar_title").getText(); //com.weather.Weather:id/toolbar_title
				System.out.println("Text : "+skiDetails_Text);
				if(skiDetails_Text.contains("Resorts"))
				{
					System.out.println("On Ski_Details page");
					ATUReports.add("On Ski_Details page",false);
				}
				
				//Verifying the SpotLight Ad on SkiDetails page
				
				MobileElement spotLightAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ViewSwitcher[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]");
				
				WebDriverWait wait1 = new WebDriverWait(Ad,10);
				
				wait1.until(ExpectedConditions.visibilityOf(spotLightAd));

				if (spotLightAd.isDisplayed()) {

					System.out.println("SpotLight Ad is present on SkiDetails page");

					ATUReports.add("SpotLight Ad is present on SkiDetails page ",false);

				} 
//				else {
//					System.out.println("SpotLight Ad is NOT present on SkiDetails page");
//				}
				Thread.sleep(1000);
				Swipe.swipe();
				Thread.sleep(1000);
				Swipe.swipe();
				
				//Verifying the Big Banner Ad on Ski Details page
				MobileElement bigBannerAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");

				
				WebDriverWait wait2 = new WebDriverWait(Ad,10);
				
				wait2.until(ExpectedConditions.visibilityOf(bigBannerAd));

				if (bigBannerAd.isDisplayed()) {

					System.out.println("Big Banner Ad is present on SkiDetails page");

					ATUReports.add("Big Banner Ad is present on SkiDetails page ",false);

					Thread.sleep(2000);

					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();

					break;

				}
//					else {
//					System.out.println("Big Banner Ad is NOT present on SkiDetails page");
//				}

			} else {
				System.out.println("Ski Module is NOT present and scrolling down");

				Swipe.swipe();

			}
		}

	}

}
