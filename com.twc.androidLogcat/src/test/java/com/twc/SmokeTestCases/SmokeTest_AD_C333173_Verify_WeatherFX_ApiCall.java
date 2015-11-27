package com.twc.SmokeTestCases;

import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.twc.General.Swipe;
import com.twc.General.wfxtg;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@SuppressWarnings("unused")
public class SmokeTest_AD_C333173_Verify_WeatherFX_ApiCall extends Driver {

	@SuppressWarnings({ "unused", "unchecked", "rawtypes", "resource" })

	public void SmokeTest_WFX () throws IOException, InterruptedException{

		System.out.println("Verification of WeatherFX Call Test_Case Started");
		
		String adbPath = properties.getProperty("adbPath");
		
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		
		System.out.println("Writing App logs to LogFile");
		
		//Wait for 20 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
		MobileElement el = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		System.out.println("Temp : "+el.getText());

//		Swipe.swipe();
		Ad.swipe(0,1800,0,20,2000);
		Ad.swipe(0,1800,0,20,2000);
		
		MobileElement AdEle =(MobileElement) Ad.findElementById("com.weather.Weather:id/ad_view_holder");

		WebDriverWait wait1 = new WebDriverWait(Ad, 4);
		
		wait1.until(ExpectedConditions.visibilityOf(AdEle));
	
		if(AdEle.isDisplayed())
		{
			System.out.println("Feed-1 Ad is displayed");
		}
		
		Thread.sleep(1000);
		// Reading the log file for feed_1,to verify WFXTG value 

				BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

				String line = "";
				String allLine = "";

				while ((line = r.readLine()) != null) {
					System.out.println("Sys data is ::" + line);
				}

				String FilePath = properties.getProperty("LogFilePath");

				try {
					FileInputStream fstream = new FileInputStream(FilePath);
					BufferedReader br = new BufferedReader(new InputStreamReader(
							fstream));
					String strLine;

					// / read log line by line ------ strLine = br.readLines(6, 10); /
					StringBuffer sb = new StringBuffer("");
					while ((strLine = br.readLine()) != null) {

						sb.append(strLine);

					}
					 Thread.sleep(2000);
					
					 String req = null;
					 String[] arrays;
					 String[] key;
					 String zipcode = null;
					 List<String> pubad_zip = new ArrayList<String>();
					
					if (sb.toString().contains("slotName=weather.feed1")) {
						req = sb.toString().substring(sb.toString().lastIndexOf("slotName=weather.feed1"));
						req = req.substring(req.indexOf(",") + 1, req.indexOf("}"));
						System.out.println("Verifing the " + req);
						arrays = req.split(" ");
						for (String keys : arrays) {
						
							if (keys.contains("=")) {
								key = keys.split("=");
								// System.out.println("keys are :: "+key[0] + "---"+key[1]);
								if (key[0].contains("zip")) {
									key[1] = key[1].toString();
									key[1] = key[1].substring(0, key[1].lastIndexOf(","));
									pubad_zip.add(key[1]);
									break;
								}

							}
						}						
					   zipcode = pubad_zip.toString().replace("[", "").replace("]", "");
						System.out.println("Zip value is :: "+ zipcode);
					}

					 List<String> pubad_ZCSvalues =wfxtg.getValues(req, "zcs");
					 List<String> pubad_HZCSvalues =wfxtg.getValues(req, "hzcs");
					 List<String> pubad_NZCSvalues = wfxtg.getValues(req, "nzcs");

					 Thread.sleep(1000);
					 
						System.out.println("================================");
						
						try{
						// Capturing the WFXTG Call Data
						if (sb.toString().contains("response from server is {" + '"' + "wfxtg")) {
							String wfxValues = null;
							if (sb.toString().contains("response from server is {" + "\"wfxtg\"")) {
								wfxValues = sb.toString().substring(sb.toString().lastIndexOf("response from server is {" + '"'+ "wfxtg"));
								wfxValues = wfxValues.substring(
										wfxValues.indexOf("scatterSegs" + '"' + ":") + 13,
										wfxValues.indexOf("]}}") + 1);
								System.out.println("Verifing the WFX call " + wfxValues);
							}
							System.out.println("================================");
							
							Thread.sleep(1000);

							List<String> segmentList = new ArrayList<String>();
							String seg = null;
							String zip = zipcode; 
							JSONParser parser = new JSONParser();
							Object obj = parser.parse(wfxValues.toString());
							JSONArray jsonObject = (JSONArray) obj;

							Map<String, List<String>> zcsmap = new HashMap<String, List<String>>();
							Map<String, List<String>> hzcsmap = new HashMap<String, List<String>>();
							Map<String, List<String>> nzcsmap = new HashMap<String, List<String>>();
							
							for (int index = 0; index < jsonObject.size(); index++) {
								JSONObject obj1 = (JSONObject) jsonObject.get(index);
								if (obj1.containsKey("zcs")) {
									JSONArray array2 = (JSONArray) obj1.get("zcs");
									for (int index2 = 0; index2 < array2.size(); index2++) {
										JSONObject obj2 = (JSONObject) array2.get(index2);
										JSONArray array3 = (JSONArray) obj2.get("segments");
										List<String> list = new ArrayList<String>();
										for (int index3 = 0; index3 < array3.size(); index3++) {
											list.add(((Long) array3.get(index3)).toString());
										}
										zcsmap.put((String) obj2.get("zip"), list);
									}

								} else if (obj1.containsKey("hzcs")) {
									JSONArray array2 = (JSONArray) obj1.get("hzcs");
									for (int index2 = 0; index2 < array2.size(); index2++) {
										JSONObject obj2 = (JSONObject) array2.get(index2);
										JSONArray array3 = (JSONArray) obj2.get("segments");
										List<String> list = new ArrayList<String>();
										for (int index3 = 0; index3 < array3.size(); index3++) {
											list.add(((Long) array3.get(index3)).toString());
										}
										hzcsmap.put((String) obj2.get("zip"), list);
									}

								} else if (obj1.containsKey("nzcs")) {
									JSONArray array2 = (JSONArray) obj1.get("nzcs");
									for (int index2 = 0; index2 < array2.size(); index2++) {
										JSONObject obj2 = (JSONObject) array2.get(index2);
										JSONArray array3 = (JSONArray) obj2.get("segments");
										List<String> list = new ArrayList<String>();
										for (int index3 = 0; index3 < array3.size(); index3++) {
											list.add(((Long) array3.get(index3)).toString());
										}
										nzcsmap.put((String) obj2.get("zip"), list);
									}

								}
							}
							
						// Asserting the PubAd_ZCS_Values and WFX_APICall_ZCS_Values
							List<String> zcssegments = zcsmap.get(zip);

							List wfx_zcs = new ArrayList();
							for (String i : zcssegments) {					
								wfx_zcs.add(Integer.parseInt(i));					
							}
							Collections.sort(wfx_zcs);	
							String wfx_zcs_vales = wfx_zcs.toString().replace(" ", "");
							
							String pubAd_zcs_values = pubad_ZCSvalues.toString();
							System.out.println("PubAd_ZCS_values " + pubAd_zcs_values);
							System.out.println("WFX_APICall_ZCS_values " + wfx_zcs_vales);
							
							if(wfx_zcs_vales.equalsIgnoreCase(pubAd_zcs_values)){
								System.out.println("PubAd_ZCS_values and WFX_APICall_ZCS_values are equal");
							}else{
								System.out.println("PubAd_ZCS_values and WFX_APICall_ZCS_values are NOT equal");
							}
							
							System.out.println("================================");
							
							// Asserting the PubAd_NZCS_Values and WFX_APICall_NZCS_Values
							List<String> nzcssegments = nzcsmap.get(zip);

							List wfx_nzcs = new ArrayList();
							for (String j : nzcssegments) {					
								wfx_nzcs.add(Integer.parseInt(j));					
							}
							Collections.sort(wfx_nzcs);
							String wfx_nzcs_vales = wfx_nzcs.toString().replace(" ", "");
															
							String pubAd_nzcs_vales = pubad_NZCSvalues.toString();
							System.out.println("PubAd_NZCS_values " + pubAd_nzcs_vales);
							System.out.println("WFX_APICall_NZCS_values " + wfx_nzcs_vales);
							
							if(wfx_nzcs_vales.equalsIgnoreCase(pubAd_nzcs_vales)){
								System.out.println("PubAd_NZCS_values and WFX_APICall_NZCS_values are equal");
							}else{
								System.out.println("PubAd_NZCS_values and WFX_APICall_NZCS_values are NOT equal");
							}
							System.out.println("================================");
							
							// Asserting the PubAd_HZCS_Values and WFX_APICall_HZCS_Values
							List<String> hzcssegments = hzcsmap.get(zip);

							List wfx_hzcs = new ArrayList();
							for (String j : hzcssegments) {					
								wfx_hzcs.add(Integer.parseInt(j));					
							}
							Collections.sort(wfx_hzcs);
							String wfx_hzcs_vales = wfx_hzcs.toString().replace(" ", "");
													
							String pubAd_hzcs_vales = pubad_HZCSvalues.toString();
							System.out.println("PubAd_HZCS_values " + pubAd_hzcs_vales);
							System.out.println("WFX_APICall_HZCS_values " + wfx_hzcs_vales);
							
							if(wfx_hzcs_vales.equalsIgnoreCase(pubAd_hzcs_vales)){
								System.out.println("PubAd_HZCS_values and WFX_APICall_HZCS_values are equal");
							}else{
								System.out.println("PubAd_HZCS_values and WFX_APICall_HZCS_values are NOT equal");
							}
							System.out.println("================================");

						}
					
						br.close();
						}catch(Exception e){
							System.out.println("WFXTG values are showing as " +'"'+"nl"+'"'+ "for ZCS/NZCS");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("Verifying WFX test case done");
				}
			}
