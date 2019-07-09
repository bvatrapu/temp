package com.SAP.framework.base;

import com.SAP.framework.utils.drivergeneric.*;
import com.SAP.framework.utils.framework.ExtentManager;
import com.SAP.framework.utils.framework.Log;
import com.SAP.framework.utils.framework.Screenshot;
import com.SAP.framework.utils.generic.DateAndTime;
import com.SAP.framework.utils.generic.Generic;
import com.aventstack.extentreports.Status;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfigBase {

    public ConfigTestData configTestData=new ConfigTestData();
    public ExtentManager extentManager = new ExtentManager(configTestData);

    public ConfigDriver configDriver = null;
    public Screenshot screenshotGenarator = null;
    public Generic generic = null;
    public Browser browser = null;
    public Element element = null;
    public Keyboard keyboard = null;
    public Mouse mouse =null;
    public Wait wait=null;


    HashMap<String, String> enviornmentHashmap = null;

    //SAP-START   -   BeforeSuite
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        try {


        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //SAP-END   -   BeforeSuite
    //SAP-START   -   BeforeTest
    @BeforeTest(alwaysRun = true)
    public void extentReportConfig(ITestContext context) {
        String reportName = null;
        String groupName = null;
        configTestData.suiteXml_Name = context.getSuite().getName();
        // groupName=generic.getSuiteXmlGroupName(context.getIncludedGroups());
        reportName = configTestData.suiteXml_Name + "_[" + DateAndTime.getTime() +"]_"+ DateAndTime.getDate()+".html";
        extentManager.createReportFile(reportName);
    }
    //SAP-END   -   BeforeTest

    //SAP-START   -   BeforeMethod
    @Parameters({"browser","environment","platform"/*SAP-Parameters-BeforeMethod*/})
    @BeforeMethod(alwaysRun = true)
    public void initSetup(Method testMethod, @Optional("optional") String browser, @Optional("optional") String environment, @Optional("optional") String platform/*SAP-MethodArgs-BeforeMethod*/) {
        initClass();
        if(System.getProperty("browser")!=null) {
            configTestData.testBrowser = System.getProperty("browser").toLowerCase();
            configTestData.testEnvironment = System.getProperty("environment").toLowerCase();
            configTestData.testPlatform = System.getProperty("platform").toLowerCase();
		/*SAP-VarDec_System.getProp-BeforeMethod*/
        }else {
            configTestData.testBrowser = browser.toLowerCase();
            configTestData.testEnvironment = environment.toLowerCase();
            configTestData.testPlatform = platform.toLowerCase();
            /*SAP-VarDec-BeforeMethod*/
        }

        try {
            configTestData.testMethod_Name = testMethod.getName();
            initDriver();
            initClass_After_Driver();
            extentManager.createTest(configTestData.testMethod_Name);
        } catch (Exception e){
            e.printStackTrace();
        }

        Test testClass = testMethod.getAnnotation(Test.class);
        configTestData.suiteXmlgroup_Name = testClass.groups()[0];
      //  loadTestData(testClass.groups()[0],banner,environment);
        Log.info(configTestData.testMethod_Name + " :: TestScript is Start");

    }
    //SAP-END   -   BeforeMethod

    //SAP-START   -   AfterMethod
    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {
        for(String group:result.getMethod().getGroups()){
            extentManager.assignGroup(group);
        }
        try{
            configTestData.driver.quit();
            extentManager.addfinalStatus(configTestData.finalTestCaseStatus);
        } catch (Exception e){
            e.printStackTrace();
        }

        Log.info(configTestData.testMethod_Name + " :: TestScript is End");
        Log.info("===================================================================");
    }
    //SAP-END   -   AfterMethod

    //SAP-START   -   AfterTest
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        try{
            enviornmentHashmap = new HashMap<String, String>();
            enviornmentHashmap.put("OS",generic.getCurretnPlatform().toString());
            enviornmentHashmap.put("UserName",System.getProperty("user.name"));
            enviornmentHashmap.put("Environment",configTestData.testEnvironment.toUpperCase());
            enviornmentHashmap.put("Browser",configTestData.testBrowser.toUpperCase());
            extentManager.setSystemInfo(enviornmentHashmap);


            extentManager.assignLog(generic.readFile(ConfigTestData.WORKING_DIR+ "/src/main/resources/log/Testomation.log"));
            extentManager.setHtmlConfig(configTestData.suiteXml_Name);
            extentManager.flush();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //SAP-END   -   AfterTest

    public void initClass(){
        configDriver = new ConfigDriver(configTestData);
        screenshotGenarator = new Screenshot(configTestData);
        generic = new Generic(configTestData);

    }
    public void initClass_After_Driver(){

    }

    public void initDriver(){
        try {
//            configDriver = new ConfigDriver(configTestData);
            configDriver.initDriver();
            //  Set Objet Defination file path
            //  generic.getObjectDefFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void verify(String condition){
        configTestData.stepNo = configTestData.stepNo+1;
        if(condition.toLowerCase() == "true"){
            extentManager.addExecutionStep(Status.PASS);
        } else{
            if(configTestData.stepExpected.contains(" is not ")){
                configTestData.stepExpected = configTestData.stepExpected.replaceAll(" is not ", " is ");
            } else {
                configTestData.stepExpected = configTestData.stepExpected.replaceAll(" is ", " is not ");
            }
            extentManager.addExecutionStep(Status.FAIL);
            configTestData.finalTestCaseStatus = Status.FAIL;
        }
    }
    public void verify(String condition, String trueMsg,String falseMsg){
        configTestData.stepNo = configTestData.stepNo+1;
        if(condition.toLowerCase() == "true"){
            extentManager.addExecutionStep(Status.PASS,trueMsg);
        } else{
            extentManager.addExecutionStep(Status.FAIL,falseMsg);
            configTestData.finalTestCaseStatus = Status.FAIL;
        }
    }
    public String verify(String condition,String message){
        configTestData.stepNo = configTestData.stepNo+1;
        if(condition.toLowerCase() == "true"){
            return "true";
        } else{
            return "false";
        }
    }

    public void Assert(String condition,String trueMsg,String falseMsg){
        configTestData.stepNo = configTestData.stepNo+1;
        boolean con;
        if(condition.toLowerCase() == "true"){
            extentManager.addExecutionStep(Status.PASS,trueMsg);
            con=true;
        } else{
            con=false;
            extentManager.addExecutionStep(Status.FAIL,falseMsg);
            configTestData.finalTestCaseStatus = Status.FAIL;
        }

        Assert.assertTrue(con,falseMsg);
    }

    public String td(String keyStr) {
        String valStr=null;

        try {
            JSONParser parser = new JSONParser();
            //Object obj = parser.parse(new FileReader(jsonPath));
            // JSONObject jsonObject = (JSONObject) obj;
            valStr=configTestData.jsonObject.get(keyStr).toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return valStr;
    }

    /**
     *
     *     */
    public void report(String status){
        if(status.equalsIgnoreCase("Pass")||status.equalsIgnoreCase("true")){
            extentManager.addExecutionStep(Status.PASS,configTestData.stepExpected);
        }else {
            extentManager.addExecutionStep(Status.FAIL,configTestData.stepExpected);
        }

    }
    public void assertTrueAndReport(boolean condition, String passMessage, String failMessage){
        if(condition){
            report("pass");
        }else{
            report("fail");
        }
        assertTrue(failMessage, condition);
    }

    public void assertFalseAndReport(boolean condition, String passMessage, String failMessage){
        if(!condition){
            report("pass");
        }else{
            report("fail");
        }
        assertFalse(failMessage, condition);
    }
}




