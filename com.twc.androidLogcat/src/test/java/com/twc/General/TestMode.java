package com.twc.General;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.driver.Driver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

public class TestMode extends Driver {

	WebElement aboutThisAPP = null;
	WebElement backButton = null;
	
	public void ChangeToTestMode() throws Exception {

		System.out.println("Set the APP into Test Mode");

		//Wait for 10sec till the specified Element Presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ImageButton[1]")));
		
		// Clicking on Menu Options
		WebElement menu = Ad.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ImageButton[1]"));

		menu.click();

		// Clicking on Setting link

		Ad.findElementByName("Settings").click();
		
		aboutThisAPP = Ad.findElementByName("About this app");
		
		aboutThisAPP.click();

		//Tap on "Build Version number" more than 7times to get the "Test Mode Settings" button
		for (int i=1; i<=8; i++){
		
			Ad.findElementById("com.weather.Weather:id/about_version").click();
		}
		
		Ad.findElementByName("TEST MODE SETTINGS").click();
		
		Ad.findElementByName("Ads").click();
		
		Ad.findElementByName("Test").click();
		
		System.out.println("Changed to Test Mode under Settings");
		
		Thread.sleep(1000);
		
		System.out.println("Now Kill and Relaunch the APP");
		
		app_Kill_Relaunch.Kill_realaunch();
		
//		backButton = Ad.findElementByAccessibilityId("Navigate up");

//		backButton.click();

//		backButton.click();
		

	}

}
