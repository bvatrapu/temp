package com.SAP.framework.utils.drivergeneric;

import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.constants.Constants;
import com.SAP.framework.utils.framework.Log;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Wait {

    private static WebDriver driver=null;
    private static final int timeout = 30;
    public static int DEFAULT_TIMEOUT=500;
    private static WebDriverWait wait=null;

    public Wait(ConfigTestData configTestData){
        this.driver=configTestData.driver;
    }
//SAP - Methods - START

    /**
     * Method Description :: Wait To Load web page
     * @param waitTime
     */
    public void ToLoad(int waitTime) {
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    /**
     * Method Description :: Wait for Element Visibility of given amount of Time
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        element = new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /**
     * Method Description :: Wait for Element INVisibility of given amount of Time
     * @param element
     * @param timeout
     * @return
     */
    public static boolean waitForInVisibility(WebElement element, int timeout){
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Method Description :: Wait for Element INVisibility of 20 Seconds
     * @param element
     * @return
     */
    public static boolean waitForInVisibility(WebElement element){
        return new WebDriverWait(driver, Constants.MEDIUM_TIMEOUT).until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Method Description :: Wait for Element Visibility of 20 Seconds
     * @param element
     * @return
     */
    public static WebElement waitForVisibility(WebElement element){
        element = new WebDriverWait(driver,Constants.MEDIUM_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /**
     * Method Description :: Wait for List of Elements Visibility of 20 Seconds
     * @param elements
     * @return
     */
    public static List<WebElement> waitForVisibility(List<WebElement> elements){
        elements = new WebDriverWait(driver,Constants.MEDIUM_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }

    /**
     * Method Description :: Wait for List of Elements Visibility of given amount of Time
     * @param driver
     * @param elements
     * @param timeout
     * @return
     */
    public static List<WebElement> waitForVisibility(WebDriver driver, List<WebElement> elements,int timeout) {
        elements = new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }

    /**
     * Method Description :: Wait for clickable of Element Visibility of given amount of Time
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickability(WebElement element, int timeout){
        waitUntilElementIsDisplayed(element);
        element = new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    /**
     * Method Description :: Wait for clickable of Element Visibility of 20 seconds
     * @param driver
     * @param element
     * @return
     */
    public static WebElement waitForClickability(WebDriver driver, WebElement element){
        waitUntilElementIsDisplayed(element);
        element = new WebDriverWait(driver,Constants.MEDIUM_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    /**
     * Method Description :: Wait until element is displayed
     * @param element
     * @return
     */
    public static boolean waitUntilElementIsDisplayed(WebElement element){
        int counter = 0;
        boolean result = false;

        while(counter <= 2){
            try{
                if(element.isDisplayed()){
                    result = true;
                    break;
                }
            }catch(NoSuchElementException | StaleElementReferenceException e){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            counter++;
        }

        return result;
    }
    /**
     * Method Description :: Sleep given amount of time
     * @param timeInSeconds
     */
    public static void sleep(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            Log.error("TimeoutException in generic_Wait for :"+ e.getMessage());
        }
    }

	
}
//SAP - Methods - END