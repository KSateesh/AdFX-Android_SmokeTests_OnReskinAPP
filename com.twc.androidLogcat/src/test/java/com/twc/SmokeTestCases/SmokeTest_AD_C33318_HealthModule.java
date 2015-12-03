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

public class SmokeTest_AD_C33318_HealthModule extends Driver {

	@SuppressWarnings({ "unused", "deprecation" })
	public void verify_SpotLightAd_present_on_ColdFlu_section() throws Exception {

		// app kill and relaunch the app
		//app_Kill_Relaunch.Kill_realaunch();

		//Set the context of APP to Native
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");

		ATUReports.add("Launch the app", false);
		
		// To get the dimensions of the screen
		Dimension dimensions = Ad.manage().window().getSize();
		// System.out.println("dimensions :: "+dimensions);

		System.out.println("Searching for Health Module");
		
		ATUReports.add("Scroll to Health Module", false);

		for (int i = 0; i < dimensions.getHeight(); i++) {

			WebElement healthModule = null;

			try {
				healthModule = Ad.findElementByName("Health");
			} catch (Exception e) {
				// System.out.println(e);
			}

			if (healthModule != null && healthModule.isDisplayed()) {
				
				System.out.println("Health module is present and tap on Cold & Flu section");
				
				ATUReports.add("Health module is present and tap on Cold & Flu section",false);

				//Click in Cold & Flu section
				Ad.findElementById("com.weather.Weather:id/flu_dial").click();

				//Verifying the SpotLight Ad on Cold & Flu page
				MobileElement spotLightAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]");
				
				WebDriverWait wait1 = new WebDriverWait(Ad,10);
				
				wait1.until(ExpectedConditions.visibilityOf(spotLightAd));

				if (spotLightAd.isDisplayed()) {

					System.out.println("SpotLight Ad is present on Cold & Flu page");

					ATUReports.add("SpotLight Ad is present on Cold & Flu page ",false);

				} else {
					System.out.println("SpotLight Ad is NOT present on Cold & Flu page");
				}
				
				Thread.sleep(1000);
				//Swipe.swipe();
				//Verifying the Big Banner Ad on Cold & Flu page
				MobileElement bigBannerAd = (MobileElement) Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.view.View[1]");
//				MobileElement bigBannerAd = (MobileElement) Ad.findElementsByClassName("android.view.View"); 
				
				WebDriverWait wait2 = new WebDriverWait(Ad,10);
				
				wait2.until(ExpectedConditions.visibilityOf(bigBannerAd));

				if (bigBannerAd.isDisplayed()) {

					System.out.println("Big Banner Ad is present on Cold & Flu page");

					ATUReports.add("Big Banner Ad is present on Cold & Flu page ",false);

					Thread.sleep(2000);

					// Clicking back button
					Ad.findElementByAccessibilityId("Navigate up").click();

					break;

				} else {
					System.out.println("Big Banner Ad is NOT present on Cold & Flu page");
				}

			} else {
				System.out.println("Health Module is NOT present and scrolling down");

				Swipe.swipe();

			}
		}

	}

}
