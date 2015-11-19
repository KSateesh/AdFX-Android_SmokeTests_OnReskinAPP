package com.twc.General;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;

import com.twc.driver.Driver;



public class Scroll extends Driver{
	
	public static void scrolldown(){
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}
	
	public static void scrollup(){
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);
	}
	

}
