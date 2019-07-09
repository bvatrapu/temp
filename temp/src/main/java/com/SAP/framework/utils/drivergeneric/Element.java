package com.SAP.framework.utils.drivergeneric;

import com.SAP.framework.base.ConfigBase;
import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.constants.Constants;
import com.SAP.framework.utils.framework.Log;
import com.beust.jcommander.Strings;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Element extends ConfigBase {

    private WebDriver driver=null;
    private WebElement webElement;
    private final int timeout = 30;
    public static int DEFAULT_TIMEOUT=500;
    private static WebDriverWait webDriverWait=null;
    private Wait wait;

    public Element(ConfigTestData configTestData){
        this.driver=configTestData.driver;
        browser=new Browser(configTestData);
        wait=new Wait(configTestData);
    }

//SAP - Methods - START

    /**
     * Method Description :: This Method will Click on provided WebElement
     * @param webElement : WebElement reference
     * @return
     */
    public void click(WebElement webElement){
        try{
            webElement.click();
        }catch (Exception e) {
            Log.error("Exception in click method :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: It can be used inside forms on elements, or form tags to submit that form.
     * @param webElement
     */
    public void submit(WebElement webElement){
        try{
            webElement.submit();
        }catch (Exception e) {
            Log.error("Exception in submit method :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: To enter text into the Text Fields and Password Fields
     * @param webElement
     * @param str
     */
    public void sendKeys(WebElement webElement,String str){
        try{
            webElement.sendKeys(str);
        }catch (Exception e) {
            Log.error("Exception in sendKeys method :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This method is used to delete the text in an input box.
     * @param webElement
     */
    public void clear(WebElement webElement){
        try{
            webElement.clear();
        }catch (Exception e) {
            Log.error("Exception in clear method :"+ e.getMessage());
        }
    }

    /**
     *  Method Description :: It gets the tag name of the current element.
     * @param webElement
     * @return
     */
    public String getTagName(WebElement webElement){
        try{
           return webElement.getTagName();
        }catch (Exception e) {
            Log.error("Exception in getTagName method :"+ e.getMessage());
            throw new TestException("webElement is not exist"+webElement);
        }
    }

    /**
     *  Method Description :: This method is used to retrieve the value of the specified attribute
     * @param webElement
     * @param arg
     * @return
     */
    public String getAttribute(WebElement webElement,String arg){
        try{
          return webElement.getAttribute(arg);
        }catch (Exception e) {
            Log.error("Exception in getTagName method :"+ e.getMessage());
            throw new TestException("webElement is not exist"+webElement);
        }
    }

    /**
     * Method Description :: This method is used to verify if the web element is selected or not.
     * @param webElement
     * @return
     */
    public String isSelected(WebElement webElement){
        boolean elmSelected=false;
        try{
           return String.valueOf(webElement.isSelected());
        }catch (Exception e) {
            Log.error("Exception in isSelected method :"+ e.getMessage());
        }
        return String.valueOf(elmSelected);
    }

    /**
     *  Method Description :: This method is used to verify if the web element is enabled or disabled within the webpage.
     * @param webElement
     * @return
     */
    public String isEnabled(WebElement webElement){
        boolean elmEnabled=false;
        try{
            elmEnabled =webElement.isEnabled();
        }catch (Exception e) {
            elmEnabled=false;
            Log.error(webElement+"::: Exception in isEnabled method :"+ e.getMessage());
        }
        return String.valueOf(elmEnabled);
    }

    /**
     *  Method Description :: This method is used to verify a presence of a web element within the webpage
     * @param webElement
     * @return
     */
    public String isDisplayed(WebElement webElement){
        boolean elmDisplayed=false;
        try{
            elmDisplayed= webElement.isDisplayed();
        }catch (Exception e) {
            elmDisplayed=false;
            Log.error("Exception in isDisplayed method :"+ e.getMessage());
        }
        return String.valueOf(elmDisplayed);
    }

    /**
     *  Method Description :: This method is used to verify a presence of a web element within the webpage
     *
     * @return
     */
    public String isExist(WebElement webElement){
        boolean elmExist=false;
        WebElement element=null;
        try{
            element = wait.waitForVisibility(webElement,Constants.MEDIUM_TIMEOUT);
            if(element!=null){
                elmExist=true;
            }
        }catch (Exception e) {
            elmExist=false;
            Log.error("Exception in isDisplayed method :"+ e.getMessage());
        }
        return String.valueOf(elmExist);
    }

    /**
     * Method Description :: This method is used to retrieve the inner text of the specified web element
     * @param webElement
     * @return
     */
    public String getText(WebElement webElement){
        String elementText = null;
        try{
            webDriverWait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            if (webDriverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
                elementText= String.valueOf(webElement.getText());
            }
        }catch (Exception e) {
            Log.error("Exception in getText method :"+ e.getMessage());
        }
        return elementText;
    }

    /**
     *  Method Description :: This Method will selct on provided WebElement and then Key Enter
     * @param webElement : WebElement reference
     * @return
     */
    public void enter(WebElement webElement){
        try{
            webDriverWait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            if (webDriverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
                webElement.sendKeys(Keys.ENTER);
            }
        }catch (Exception e) {
            Log.error("Exception in generic_Click_Enter :"+ e.getMessage());
        }
    }
    /**
     * Method Description ::  This Method will Click on corresponding element using Action Builder
     * @param webElement : WebElement reference
     * @return
     */
    public void action(WebElement webElement){
        try {
            webDriverWait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            if (webDriverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
                Actions builder = new Actions(driver);
                builder.click(webElement).build().perform();
            }
        } catch (Exception e) {
            Log.error("Exception in generic_Click_Action :"+ e.getMessage());
        }
    }
    /**
     * Method Description :: This Method will enter value in Textbox
     * @param wTextBoxName : Textbox WebElement reference
     * @param sText : String type text which will be set in text box
     * @return
     */
    public String setValueToinputBox(WebElement wTextBoxName,String sText){
        String result = null;
        try {
            wTextBoxName.click();
            wTextBoxName.clear();
            wTextBoxName.sendKeys(sText);
            // Verify that value has been entered
            result=verifyInputBoxText(wTextBoxName,sText,Constants.VERIFY_TEXT_PARTIAL);
        } catch (Exception e) {

            Log.error("Exception in generic_SetTextTextBox :"+ e.getMessage());
        }
        return result;
    }

    /**
     * Method Description :: This method is used for select Item from combobox or List
     * @param wComboBoxName
     * @param sItemText
     * @return
     */
    public String selectItemInCombobox(WebElement wComboBoxName,String sItemText){
        String result = null;
        Log.info("Select " + sItemText + " in Combobox");
        try{
            Select datatype = new Select(wComboBoxName);
            datatype.selectByVisibleText(sItemText);
            // datatype.selectByValue(sItemText);
            Thread.sleep(5000);
            result = verifyItemInCombobox(wComboBoxName, sItemText);
        }catch (Exception e) {
            result=String.valueOf(false);
            Log.error("Exception in generic_SelectItemInCombobox :"+ e.getMessage());
        }
        return result;
    }
    /**
     * Method Description :: This method is used for verify selected Item in combobox with given value
     * @param wComboBoxName
     * @param sExpectedItemText
     * @return
     */
    public String verifyItemInCombobox(WebElement wComboBoxName,String sExpectedItemText){
        String actualText;
        String result = null;
        try {
            // Get the selected value from specified combo box
            Select selectedValue = new Select(wComboBoxName);
            actualText = selectedValue.getFirstSelectedOption().getText();

            Log.info("commonVerifyItemInCombobox,actualText : " + actualText
                    + " , sExpectedItemText : " + sExpectedItemText);

            // Verify that selected item in combo box is same as expected
            // item
            if (actualText.equalsIgnoreCase(sExpectedItemText)) {
                result = String.valueOf(true);
            } else {
                result = String.valueOf(false);
            }
        } catch (Exception e) {
            Log.error("Exception in generic_VerifyItemInCombobox :"+ e.getMessage());
            result = String.valueOf(false);
        }
        return result;
    }
    /**
     * Method Description :: This method is used for check or uncheck the checkbox
     * @param wCheckBox
     * @param checkstatus
     * @return
     */
    public String checkUncheckCheckBox(WebElement wCheckBox,String checkstatus){
        String result = null;
        try {
            if (wCheckBox.isSelected() && checkstatus.equalsIgnoreCase(Constants.C_CHECK)){
                result=String.valueOf(true);
            } else if(wCheckBox.isSelected()==false && checkstatus.equalsIgnoreCase(Constants.C_CHECK)){
                // Check check box
                wCheckBox.click();
                result = String.valueOf(true);
            } else if(wCheckBox.isSelected()==false && checkstatus.equalsIgnoreCase(Constants.C_UNCHECK)){
                result = String.valueOf(true);
            } else if (wCheckBox.isSelected() && checkstatus.equalsIgnoreCase(Constants.C_UNCHECK)) {
                // UnCheck check box
                wCheckBox.click();
                result = String.valueOf(true);
            }
            Log.info(
                    "checkstatus:" + checkstatus + ",Result:" + result);

        }catch (Exception e) {
            Log.error("Exception in generic_CheckUncheckCheckBox :"+ e.getMessage());
            result = String.valueOf(false);
        }
        return result;
    }
    /**
     * Method Description :: This Method is used to Verify Label Text. 1. EXACTMATCH: Compares this string to the specified object.2.EXACTMATCHIGNORECASE:Compares this String to another String ignoring case 3.PARTIAL:Returns true if and only if this string contains the specified
     * @param wLabelName
     * @param sExpectedText
     * @return
     */
    public String verifyLabelText(WebElement wLabelName,String sExpectedText,String verifyTextOptions){
        String result=null;
        String sTemp = null;
        try{
            sTemp = wLabelName.getText();
            result= generic.verifyText(sTemp,sExpectedText,verifyTextOptions);


        }catch (Exception e) {
            result="false";
            Log.error("Exception in verifyText :"+ e.getMessage());
        }
        return result;
    }

    /**
     * Method Description :: This Method is used to Verify Inputbox Text or Value. 1. EXACTMATCH: Compares this string to the specified object.2.EXACTMATCHIGNORECASE:Compares this String to another String ignoring case 3.PARTIAL:Returns true if and only if this string contains the specified
     * @param winputBoxName
     * @param sExpectedText
     * @return
     */
    public String verifyInputBoxText(WebElement winputBoxName,String sExpectedText,String verifyTextOptions){
        String result=null;
        String sTemp = null;
        try{
            sTemp = winputBoxName.getAttribute("value");
            result= generic.verifyText(sTemp,sExpectedText,verifyTextOptions);
        }catch (Exception e) {
            result="false";
            Log.error("Exception in verifyText :"+ e.getMessage());
        }
        return result;
    }

    /**
     * Method Description :: This method is used for upload a file in current webpage
     * @param sourceFile
     * @param fileName
     */
    public void chooseFiletoUpload(WebElement sourceFile, String fileName){
        String path = fileName;
        try {
            sourceFile.sendKeys(path);
            sourceFile.click();
            Thread.sleep(650);
        }catch (Exception e) {
            Log.error("Exception in generic_ChooseFiletoUpload :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This method is used for get all Items from combobox
     * @param wComboboxName
     * @return
     */
    public List getAllElementInCombobox(WebElement wComboboxName){
        List comboboxElements = null;
        try {
            Select datatype = new Select(wComboboxName);
            comboboxElements = datatype.getOptions();
        } catch (Exception e) {
            Log.error("Exception in generic_TrimLeadingZeros :"+ e.getMessage());
        }
        return comboboxElements;
    }
    /**
     * Method Description ::  This Method will Click menuItem on provided WebElement
     * @param menu
     * @param subMenu
     */
    public void click_MenuItem(WebElement menu,WebElement subMenu){
        try {
            action(menu);
            Thread.sleep(3000);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", subMenu);

        } catch (Exception e) {
            Log.error("Exception in generic_Click_MenuItem :"+ e.getMessage());
        }
    }
    /**
     * Method Description :: This Method will Click date button and select the provided date.
     * @param datebutton
     * @param date
     * @return
     */
    public String datePicker(WebElement datebutton,String date) {
        String result = null, result1 = null, result2 = null;
        WebElement selectMonth, selectYear;
        String[] abc = date.split(",");
        try {
            action(datebutton);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            selectMonth = driver.findElement(By
                    .className("ui-datepicker-month"));
            // select month from the month drop down
            result1 = selectItemInCombobox(selectMonth, abc[0]);

            selectYear = driver.findElement(By.className("ui-datepicker-year"));

            result2 = selectItemInCombobox(selectYear, abc[2]);
           // result = result1 && result2;

            /*
             * DatePicker is a table.So navigate to each cell If a particular
             * cell matches value 13 then select it
             */

//            WebElement dateWidget = driver.findElement(By
//                    .className("ui-datepicker-calendar"));
//            List rows = dateWidget.findElements(By.tagName("tr"));
//            int rowsize = rows.size();
//            for (int i = 0; i < rowsize; i++) {
//                List columns = rows.get(i).findElements(
//                        By.tagName("td"));
//                int columnsize = columns.size();
//                for (int j = 0; j < columnsize; j++) {
//                    String datatext = columns.get(j).getText().toString();
//                    if (datatext.equalsIgnoreCase(abc[1])) {
//                        generic_Click_Action(columns.get(j));
//                    }
//
//                }
//
//            }
            result = String.valueOf(true);

        } catch (Exception e) {
            Log.error("Exception in generic_datePicker :"+ e.getMessage());
        }

        return result;
    }

    /**
     * Method Description :: This method is used for click an Element using Java Script
     * @param element
     */
    public void click_JavaScript(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Log.error("Exception in generic_Click_JavaScript :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: Verify page url is Active or not
     * @param pageurl
     */
    public void verifyURLActive(String pageurl){
        try {
            URL url = new URL(pageurl);
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()==200)            {
                System.out.println(pageurl+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)            {
                System.out.println(pageurl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        }catch (Exception e) {
            Log.error("Exception in verifyURLActive :"+ e.getMessage());
        }
    }


    /**
     * Method Description :: This Method is used for click on link and verify the target page
     * @param objectReferenceId
     * @return
     */
    public String clickOnLink_VerifyTargetPage(WebElement objectReferenceId){
        boolean result = true;
        String href=objectReferenceId.getAttribute("href");
                //element.getAttribute(objectReferenceId,"href");
        String target=objectReferenceId.getAttribute("target");
                //element.getAttribute(objectReferenceId,"target");
        String originalHandle = driver.getWindowHandle();
        System.out.println("href::"+href);
        System.out.println("target::"+target);
        if (isDisplayed(objectReferenceId)=="true") {
            click_JavaScript(objectReferenceId);
          //  waitFor(6000);
        } else {
            result = false;
        }
        browser.pageWaitLoad();
        if(target.equalsIgnoreCase("_blank")){
            browser.switchTab();
            Wait.sleep(5);
            if (browser.verifyPageUrl(href)!="true") {
                result = false;
            }
            browser.closeTab(originalHandle);
        } else {
            if (browser.verifyPageUrl(href)!="true") {
                result = false;
            }
            browser.navigateBack();
        }
        return String.valueOf(result);
    }
    /**
     * Method Description :: This method is validate the Alert Message
     * @param wLabelName
     * @param sMessageTextToVerify
     * @return
     */
    public String verifyAlertTextMessage(WebElement wLabelName,String sMessageTextToVerify){
        String result=null;
        try{
            result=verifyLabelText(wLabelName,sMessageTextToVerify,Constants.VERIFY_TEXT_PARTIAL);
        }catch (Exception e) {
            result="false";
            Log.error("Exception in generic_VerifyAlertTextMessage :"+ e.getMessage());
        }
        return result;
    }

    /**
     * Method Description :: This method is used for control move to Element
     * @param element
     */
    public void moveToElement(WebElement element){
        if(configTestData.testBrowser.equalsIgnoreCase("firefox") || configTestData.testBrowser.equalsIgnoreCase("ie")){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }else{
            Actions dragger = new Actions(driver);
            dragger.moveToElement(element).build().perform();
        }
    }
    /**
     * Method Description :: This method is used for control move to Element and click that Element
     * @param element
     */
    public void moveToElementAndClick(WebElement element){
        Actions dragger = new Actions(driver);
        dragger.moveToElement(element).click().build().perform();;
    }

    /**
     * Method Description :: This method is used for double click on element
     * @param element
     */
    public void doubleClick(WebElement element){
        Actions dragger = new Actions(driver);
        dragger.doubleClick().build().perform();;
    }

    /**
     * Method Description :: This method is used for scroll to Element
     * @param element
     */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);;window.scrollBy(0, -120)", element);
    }

    /**
     * Method Description :: This method is used for scroll to Top of the web page
     */
    public void scrollToPageTop() {
        if(configTestData.testBrowser.equalsIgnoreCase("firefox") || configTestData.testBrowser.equalsIgnoreCase("edge")){
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
        }else{
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        }
    }
    /**
     * Method Description :: This method is used for scroll to bottom of the web page
     */
    public void scrollToPageBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
    }
}
//SAP - Methods - END