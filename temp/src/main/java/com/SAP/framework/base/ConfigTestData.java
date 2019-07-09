package com.SAP.framework.base;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class ConfigTestData {

    public static String WORKING_DIR = System.getProperty("user.dir");
    public static String LOCAL_GRIDHUB = "http://localhost:4444/wd/hub";

    public static String PROJECT_CONFIG_PROP_FILE_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources";

    public String suiteXml_Name=null;
    public String suiteXmlgroup_Name = null;
    public String testMethod_Name = null;

    public RemoteWebDriver driver = null;

    public RemoteWebDriver chromeDriver = null;
    public RemoteWebDriver firefoxDriver = null;
    public RemoteWebDriver internerExplorerDriver = null;
    public RemoteWebDriver safariDriver = null;
    public RemoteWebDriver edgeDriver = null;
    public RemoteWebDriver operaDriver = null;

    public AppiumDriver androidDriver = null;
    public AppiumDriver iOSDriver = null;

    //SAP-START    -    TestNG Params
    public String testBrowser = null;
    public String testEnvironment = null;
    public String testPlatform = null;
	//SAP-END    -    TestNG Params

    public Status finalTestCaseStatus = Status.PASS;

    public int stepNo =  0;
    public String stepDescription =  null;
    public String stepExpected =  null;

    public String methodDescription = null;
    public JSONObject jsonObject=null;
}




