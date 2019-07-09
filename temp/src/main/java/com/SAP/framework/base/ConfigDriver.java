package com.SAP.framework.base;

import com.SAP.framework.driverconfig.WebDriver;
import com.SAP.framework.driverconfig.drivers.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nullable;
import java.net.MalformedURLException;

public class ConfigDriver {
    private ConfigTestData configTestData=null;
    public ConfigDriver(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }

    public void initDriver(){
        try {
            configTestData.driver = getWebDriver(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public RemoteWebDriver getWebDriver(@Nullable Capabilities capabilities)
            throws MalformedURLException {
        WebDriver webDriver = null;

        switch (configTestData.testBrowser) {
            case "firefox":
            case "ff":
                webDriver = new FirefoxBrowser(configTestData);
                break;
            case "chrome":
                webDriver = new ChromeBrowser(configTestData);
                break;
            case "ie":
            case "internet explorer":
            case "ie11":
                webDriver = new IE11Browser(configTestData) ;
                break;
            case "safari":
                webDriver = new SafariBrowser(configTestData) ;
                break;
            case "edge":
            case "microsoft edge":
                webDriver = new EdgeBrowser(configTestData);
                break;

            default:
                webDriver = new ChromeBrowser(configTestData);
                break;

        }

        return webDriver.buildWebDriver(capabilities);
    }
}
