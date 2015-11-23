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
public class setAddress_Location extends Driver {
    
    public void setLocation() throws InterruptedException{
        
        System.out.println("Setting Address on the APP");
        
        //Getting the present location on the APP
        String presentLocation = Ad.findElement(By.id("com.weather.Weather:id/location_name")).getText();
        
        System.out.println("Present Location on App is :: "+presentLocation);
        
        Thread.sleep(2000);
        
        //Clicking on Search icon to enter the ZIP Code
        
        Ad.findElementByAccessibilityId("Search").click();
        //	     Ad.findElementById("com.weather.Weather:id/search").click();
        
        System.out.println("Entering a ZipCode");
        
        Ad.findElementById("com.weather.Weather:id/search_src_text").sendKeys("10035");
        //	     Ad.findElementByName("Address/City/Zip").sendKeys("10035");
        
        Thread.sleep(2000);
        
        //Selecting the Address by swipe method
        Ad.swipe(280,380,1170,380,2000); //for s6
        
        //	     Ad.swipe(180,174,580,174,1000); //for nexus
        
        //	     Ad.swipe(180,290,690,290,1000); // for nexus-1
        
        System.out.println("Selected the Address");
        
        //Getting the updated location after selecting the entered ZipCode/Address
        String updatedLocation = Ad.findElement(By.id("com.weather.Weather:id/location_name")).getText();
        
        System.out.println("Updated Location is :: " + updatedLocation);
        
        Thread.sleep(4000);
        
    }  
    
}


