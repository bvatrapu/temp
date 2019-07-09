package com.SAP.framework.utils.drivergeneric;

import com.SAP.framework.base.ConfigBase;
import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.constants.Constants;
import com.SAP.framework.utils.framework.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Browser  {

    private WebDriver driver=null;
    private String browserName;
    private final int timeout = 30;
    public int DEFAULT_TIMEOUT=500;
    private WebDriverWait wait=null;

    public Browser(ConfigTestData configTestData){
        this.browserName=configTestData.testBrowser;
        this.driver=configTestData.driver;
    }
	//SAP - Methods - START

    /**
     * Method Description :: To get testing browser name (Ex: chrome,firefox)
     * @return
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * Method Description :: It returns Web driver
     * @return
     */
    public WebDriver driver() {
        return driver;
    }

    /**
     * Method Description :: It Close the driver browser
     */
    public void close() {
        driver().close();
    }

    /**
     * Method Description :: It returns the element which is find in current page
     * @param locator
     * @return
     */
    public WebElement findElement(By locator) {
        return driver().findElement(locator);
    }

    /**
     * Method Description :: This method launches a new browser and opens
     * the specified URL in the browser instance
     * @param arg0
     */
    public void get(String arg0) {
        driver().get(arg0);
    }

    /**
     *  Method Description :: This method is used to retrieve the URL of the webpage the user is currently accessing
     * @return
     */
    public String getCurrentUrl() {
        return driver().getCurrentUrl();
    }

    /**
     * Method Description :: This method  is used to retrieve the page source
     * of the webpage the user is currently accessing
     * @return
     */
    public String getPageSource() {
        return driver().getPageSource();
    }

    /**
     * Method Description :: This method is used to retrieve the title of the webpage the user is currently working on.
     * @return
     */
    public String getTitle() {
        return driver().getTitle();
    }

    /**
     * Method Description :: This method is used to tackle with the situation when we have more than one window to deal with.
     * @return
     */
    public String getWindowHandle() {
        return driver().getWindowHandle();
    }

    /**
     * Method Description :: This method  is similar to that of getWindowHandle() with the subtle difference that it helps to deal with multiple windows i.e. when we have to deal with more than 2 windows.
     * @return
     */
    public Set getWindowHandles() {
        return driver().getWindowHandles();
    }

    /**
     * Method Description :: This method is Quits this driver instance, closing every associated window which is opened
     */
    public void quit() {
        driver().quit();
    }

    /**
     * Method Description :: This method is Load a new web page in the current browser window.
     * @param URL
     */
    public void navigateToUrl(String URL){
        Log.info("Navigating to: " + URL);
        try{
            this.driver.navigate().to(URL);
        }catch (Exception e) {
            Log.error("Exception in navigateToUrl :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: To move back a single "item" in the web browser's history.
     */
    public void navigateBack(){
        driver.navigate().back();
    }

    /**
     * Method Description :: To move a single "item" forward in the web browser's history.
     */
    public void navigateForward(){
        driver.navigate().forward();
    }

    /**
     * Method Description :: It refreshes the current web page
     */
    public void pageRefresh(){
        driver.navigate().refresh();
    }

    /**
     * Method Description :: This method is used for get current Page title
     * @return
     */
    public String getPageTitle() {
        try {
            Log.info("The title of the page is:" + driver.getTitle());
            return driver.getTitle();
        } catch (Exception e) {
            Log.error("Exception in getPageTitle :"+ e.getMessage());
            throw new TestException("URL did not load");
        }
    }
    /**
     * Method Description :: This method is handle the popup or alert messages with Accept or Decline actions
     * @param sAcceptOrDecline
     */
    public void handleAlert(String sAcceptOrDecline){
        try {

            wait = new WebDriverWait(driver, 30);
            if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
                Log.info("alert is not present, Not performing any operation");
            } else {
                Log.info("alert is present, Performing" + sAcceptOrDecline);
                // Handle the message window
                Alert alert = driver.switchTo().alert();

                if (sAcceptOrDecline.equals(Constants.C_ALERT_ACCEPT)) {
                    alert.accept();
                } else if (sAcceptOrDecline.equals(Constants.C_ALERT_DECLINE)) {
                    alert.dismiss();
                }
            }
        } catch (Exception e) {
            Log.error("Exception in generic_HandleAlert :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This method  is used to move the window down
     * @param numberoftimes
     */
    public void scrollDown(Integer numberoftimes){
        try {
            for (int i = 0; i <= numberoftimes; i++) {
                driver.findElement(By.tagName("body"))
                        .sendKeys(Keys.ARROW_DOWN);
            }
            Thread.sleep(650);

        }catch (Exception e) {
            Log.error("Exception in generic_ScrollDown :"+ e.getMessage());
        }
    }/**
     * Method Description :: This method  is used to move the window up
     * @param numberoftimes
     */
    public void scrollUp(Integer numberoftimes){
        try {
            for (int i = 0; i <= numberoftimes; i++) {
                driver.findElement(By.tagName("body"))
                        .sendKeys(Keys.ARROW_UP);
            }
            Thread.sleep(650);

        }catch (Exception e) {
            Log.error("Exception in generic_ScrollUp :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This method is wait upto page load
     */
    public synchronized void pageWaitLoad() {
        String str = null;
        try {
            str = (String) ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState");

        } catch (Exception e) {
            // it's need when JS isn't worked
            pageWaitLoad();
            return;
        }
        while (!str.equals("complete")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            str = (String) ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState");
        }
    }

    /**
     * Method Description :: This method is switching controls from one window to the other.
     * @param iframeId
     * @return
     */
    public WebElement switchToNewWindow(String iframeId) {
        driver.switchTo().frame(iframeId);
        WebElement window = driver.switchTo().activeElement();
        return window;
    }

    /**
     * Method Description :: This method is get current page url and verifies with given url
     * @param Url
     * @return
     */
    public String verifyPageUrl(String Url){
        boolean returnStatus = true;
        String getpageUrl=driver.getCurrentUrl();
        if(Url.contains("https")){
            Url = Url.replaceAll("https://","");
        } else {
            Url = Url.replaceAll("http://","");
        }
        getpageUrl=getpageUrl.toLowerCase().trim();
        Url=Url.toLowerCase().trim();
        if(!getpageUrl.contains(Url)){
            returnStatus = false;
        }
        return String.valueOf(returnStatus);
    }

    /**
     * Method Description :: This method is used get webpage responce code
     * @param url
     * @return
     */
	public int getStatusCode(String url){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Method Description :: This method is get the url of Link from href attribute and validate with status code : 200
     * @param link
     * @return
     */
    public String verifyLink(WebElement link){
        String href = link.getAttribute("href");
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(href);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode()!=200){
            return "false";
        } else{
            return "true";
        }

    }

    /**
     * Method Description :: This method is switch tab within the browser
     */
    public void switchTab() {
        String parentHandle = driver.getWindowHandle(); // get the current window handle
        //Clicking on this window
        for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
            driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
    }

    /**
     * Method Description :: This method is close tab within the browser
     */
    public void closeTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.close()");
    }

    /**
     * Method Description :: This method is close given tab within the browser
     * @param orignalTab
     */
    public void closeTab(String orignalTab) {
        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(orignalTab)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(orignalTab);
    }
        
}
//SAP - Methods - END