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

public class toKnowBuildVersion extends Driver {

	public void moreOptionsClick() throws InterruptedException {

		System.out.println("Get the Build version of APP");
		
		// Clicking on More Options
		WebElement more = Ad.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.view.View[1]/android.widget.LinearLayout[3]/android.widget.ImageButton[1]"));

		more.click();

	    // Clicking on Setting link
		Ad.findElement(By.xpath("//android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();

		System.out.println("Searching for About this app element");
		
		WebElement aboutThisAPP=null;
			
			 try{
				
				 aboutThisAPP = Ad.findElementByName("About this app");
				
			    } catch(Exception e){ 
					System.out.println("About this app element not found,so scroll down now");
				}
				
				if(aboutThisAPP!=null && aboutThisAPP.isDisplayed())
				{
					System.out.println("About this app element is found, get build version");	
					
				//clicking on About this App
					aboutThisAPP.click();
				
				//Getting the text of build version
				String BuildVersion = Ad.findElement(By.id("com.weather.Weather:id/about_version")).getText();

				System.out.println("Build Version is : " + BuildVersion);

				// Clicking back button
				Ad.findElement(By.id("android:id/home")).click();

				// Clicking back button
				Ad.findElement(By.id("android:id/home")).click();
				} 
				else{
//					System.out.println("About this app element not found,so scroll down now");
					
					//Follow Me
					MobileElement FollowMe = (MobileElement) Ad.findElementByName("Follow Me");
					
					// My Alerts
					MobileElement MyAlerts = (MobileElement) Ad.findElementByName("My Alerts");
					
					// SCROLLING from My Alerts element to Follow Me element
					TouchAction action1 = new TouchAction(Ad);
					action1.longPress(FollowMe).moveTo(MyAlerts).release().perform();

					WebDriverWait wait = new WebDriverWait(Ad, 10);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name("About this app")));

					Thread.sleep(2000);
					
					// clicking on About this App
					Ad.findElementByName("About this app").click();
					
					// Getting the text of build version
					String BuildVersion = Ad.findElement(By.id("com.weather.Weather:id/about_version")).getText();

					System.out.println("Build Version is : " + BuildVersion);

					// Clicking back button
					Ad.findElement(By.id("android:id/home")).click();

					// Clicking back button
					Ad.findElement(By.id("android:id/home")).click();
				}
				
				System.out.println("Build version taken from the About this app section under settings");
				
		
		/*
		WebElement aboutThisAPP=null;
		try{
//		 aboutThisAPP = Ad.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]/android.widget.RelativeLayout[1]/android.widget.TextView[1]"));
		aboutThisAPP = Ad.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[8]/android.widget.RelativeLayout[1]/android.widget.TextView[1]"));
		} catch(Exception e){ }
		
		if(aboutThisAPP!=null && aboutThisAPP.isDisplayed())
		{
		
			System.out.println("About this app element is found, get build version");
			
		//clicking on About this App
		Ad.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[8]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();
		
		//Getting the text of build version
		String BuildVersion = Ad.findElement(By.id("com.weather.Weather:id/about_version")).getText();

		System.out.println("Build Version is : " + BuildVersion);

		// Clicking back button
		Ad.findElement(By.id("android:id/home")).click();

		// Clicking back button
		Ad.findElement(By.id("android:id/home")).click();
		} 
		else{
			System.out.println("About this app element not found,so scroll down now");
			
			// Units of Measure
			MobileElement ele = (MobileElement) Ad.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]");
			//System.out.println("Units of Measure : " + ele.getText());

			// Sign Up
			MobileElement el1 = (MobileElement) Ad.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]");
			//System.out.println("Sign Up : " + el1.getText());
			
			// My Alerts
			MobileElement MyAlerts = (MobileElement) Ad.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]");
	    	//System.out.println("Sign Up : " + el1.getText());
						
			
			// Moving from Units of Measure element to Sign Up element
			TouchAction action1 = new TouchAction(Ad);
			action1.longPress(ele).moveTo(el1).release().perform();

			WebDriverWait wait = new WebDriverWait(Ad, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")));

			Thread.sleep(2000);
			// clicking on About this App
			Ad.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[8]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();
			
			// Getting the text of build version
			String BuildVersion = Ad.findElement(By.id("com.weather.Weather:id/about_version")).getText();

			System.out.println("Build Version is : " + BuildVersion);

			// Clicking back button
			Ad.findElement(By.id("android:id/home")).click();

			// Clicking back button
			Ad.findElement(By.id("android:id/home")).click();
		}
		
		System.out.println("build version taken from the About this app section under settings");

		// About multi touch actions
		// MultiTouchAction multiTouch = new MultiTouchAction(Ad);
		// TouchAction action0 = new TouchAction(Ad).tap(el);
		// TouchAction action1 = new TouchAction(driver).tap(el);
		// TouchAction action2 = new TouchAction(driver).tap(el);
		// multiTouch.add(action0).add(action1).add(action2).perform(); */

	}

}
