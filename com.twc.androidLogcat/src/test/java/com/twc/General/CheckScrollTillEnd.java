package com.twc.General;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.driver.Driver;


@SuppressWarnings("unused")
public class CheckScrollTillEnd extends Driver {
	
	public void setLocation() throws InterruptedException{
		
		  
        MobileElement ele = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temperature : " + ele.getText());
		
	//	MobileElement ele1=(MobileElement) Ad.findElementById("com.weather.Weather:id/hurricane_central_module_title");
		
//		String ele1="Right Now";
//		Ad.scrollToExact(ele1);
		//System.out.println("Scrolling to Right now is done");
		
//		String ele2="10 Day";
//		Ad.scrollToExact(ele2);	
//		System.out.println("Scrolling to 10-Day is done");
		
//		String ele3="Maps";
//		Ad.scrollToExact(ele3);
//		System.out.println("Scrolling to Maps is done");
		// Sign Up
//		MobileElement el1 = (MobileElement) Ad.findElementById("com.weather.Weather:id/hourly_title_textview");
//		System.out.println("Sign Up : " + el1.getText());
		
		// Moving from Units of Measure element to Sign Up element
//		TouchAction action1 = new TouchAction(Ad);
//		action1.longPress(ele).moveTo(el1).release().perform();
		
		
//	
//		Dimension dimensions = Ad.manage().window().getSize();
//		System.out.println("dimensions :: "+dimensions);
//		
//		Double screenHeightStart = dimensions.getHeight() * 0.5;
//		System.out.println("screenHeightStart :: "+screenHeightStart);
//		
//		int scrollStart = screenHeightStart.intValue();
//		System.out.println("scroll to value = " +scrollStart);
//		
//		Double screenHeightEnd = dimensions.getHeight() * 0.1;  //  dimensions.getHeight() * 0.2;  == 512.0
//		System.out.println("screenHeightEnd :: " +screenHeightEnd);
//		
//     	int scrollEnd = screenHeightEnd.intValue(); 
//     	System.out.println("scroll to end = " +scrollEnd);
//		int scrollStart =2300;
//		int scrollEnd =20;
//		Ad.swipe(0,scrollStart,0,scrollEnd,2000);
//		Thread.sleep(10000);


		
//		for (int i = 0; i < dimensions.getHeight(); i++) {
//			System.out.println("Scrolling "+i+" time");
//			Ad.swipe(0,scrollStart,0,scrollEnd,2000);
//			Thread.sleep(6000);
//			if (Ad.findElement(By.name("YourText")).size()>0)
//			System.exit(i);
			//}
//			Ad.findElement(By.name("YourText")).click();

//		System.out.println("Scroll done till all the pages");

	
		 String presentLocation = Ad.findElement(By.id("com.weather.Weather:id/location_name")).getText();

	     System.out.println("Present Location is :: "+presentLocation);
	     
	     Thread.sleep(2000);
	     	     
	     Ad.findElementByAccessibilityId("Search").click();
//	     Ad.findElementById("com.weather.Weather:id/search").click();

	     System.out.println("Entering a ZipCode");
	     
	     Ad.findElementById("com.weather.Weather:id/search_src_text").sendKeys("10035");
//	     Ad.findElementByName("Address/City/Zip").sendKeys("10035");
	     
	     Thread.sleep(2000);
	     	     
	     Ad.swipe(280,380,1170,380,2000); //for s6
	     
	     System.out.println("Selected the Address");
	     
	     String updatedLocation = Ad.findElement(By.id("com.weather.Weather:id/location_name")).getText();

	     System.out.println("Updated Location is :: "+updatedLocation);
    
//	     Ad.swipe(180,174,580,174,1000); //for nexus
	     
//	     Ad.swipe(startx, starty, endx, endy, duration);
	     
//	     Ad.swipe(180,290,690,290,1000); // for nexus-1
    	 
	     Thread.sleep(4000);
 
//	    WebElement setAddress = Ad.findElement(By.id("android:id/search_src_text"));
//	    setAddress.sendKeys("10035");
//	    Thread.sleep(4000);
//	    setAddress.sendKeys(Keys.ARROW_DOWN);
//	    setAddress.sendKeys(Keys.ENTER);
	    
//	    Ad.swipe(280,358,1170,358,2000);
//	    Thread.sleep(4000);
	    
//	  Point y=  x.getLocation();
//	  System.out.println(y);
//	    x.sendKeys("10035");
//	    x.sendKeys(Keys.ARROW_DOWN);
//	    x.sendKeys(Keys.ENTER);

//	     Thread.sleep(4000);
//	     Actions actions=new Actions(Ad);
//	     actions.sendKeys(Keys.ARROW_DOWN).click().build().perform();     
//	     actions.sendKeys(Keys.ARROW_DOWN).click();
//	     actions.sendKeys(Keys.ENTER);
//	     actions.keyDown(Keys.ARROW_DOWN).click().build().perform();
//
//	     WebDriverWait wait= new WebDriverWait(Ad,10);
//	     
//	     wait.until(ExpectedConditions.presenceOfElementLocated());
	     
//	     Thread.sleep(4000);
//	     
//	     Select city = new Select(Ad.findElement(By.id("com.weather.Weather:id/search")));
//	     city.selectByVisibleText("");
//	     
//	     try{
//	     Alert al= Ad.switchTo().alert();
//	    		 al.accept();
//	    		 System.out.println("Alert present and handled it");
//	     }catch (Exception e){
//	    	 System.out.println("Exception message : "+e);
//	    	 System.out.println("Alert is not presented");
//	    	 }
	}  
	



	}
	

