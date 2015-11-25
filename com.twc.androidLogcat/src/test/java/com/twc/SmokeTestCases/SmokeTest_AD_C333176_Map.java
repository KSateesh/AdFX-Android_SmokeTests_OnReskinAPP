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

public class SmokeTest_AD_C333176_Map extends Driver{
    
    @SuppressWarnings("unused")
    public void verify_adpresent_onextendedMap_page() throws Exception
    {
        
        //app kill and relaunch the app
        //		app_Kill_Relaunch.Kill_realaunch();
        
        String originalContext = Ad.getContext();
        Ad.context("NATIVE_APP");
        
        //To get the dimensions of the screen
        Dimension dimensions = Ad.manage().window().getSize();
        // System.out.println("dimensions :: "+dimensions); //4
        
        System.out.println("Searching for Radar & Maps section");
        
        for (int i=0; i<dimensions.getHeight(); i++)
        {
            WebElement maps = null;
            
            try {
                //				maps = Ad.findElementById(" com.weather.Weather:id/driving_difficulty_module_title");
                maps = Ad.findElementByName("Radar & Maps");
                
            } catch (Exception e) {
                // System.out.println(e);
            }
            
            if(maps!=null&& maps.isDisplayed())
            {
                //				if(!maps.isDisplayed());
                //				{
                //				System.out.println("Radar and Maps section is not displayed");
                //				}
                
                System.out.println("Radar and Maps section is displyaed and tapping on Map Image");
                
                //				Ad.findElementById("com.weather.Weather:id/map_module_thumbnail").click();
                Ad.findElementByAccessibilityId("Radar Map").click();
                
                MobileElement AdEle =(MobileElement)Ad.findElementById("com.weather.Weather:id/ad_view_holder");
                
                WebDriverWait wait1 = new WebDriverWait(Ad, 4);
                wait1.until(ExpectedConditions.visibilityOf(AdEle));
                
                if(AdEle.isDisplayed())
                {
                    Thread.sleep(2000);
                    System.out.println("Ad present on Extended Radar & Maps page");
                    // Clicking back button
                    WebElement backButton =	Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ImageButton[1]");
                    backButton.click();
                    Thread.sleep(2000);
                }break;
                
            }else
            {
                System.out.println("Radar & Maps section is not present and scrolling down");
                
                Swipe.swipe();
                
            }
        }
    }
    
}
