package com.SAP.framework.driverconfig.drivers;

import com.SAP.framework.driverconfig.BaseWebDriver;
import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.driverconfig.BaseWebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, FirefoxBrowser> {

    private ConfigTestData configTestData=null;
    public FirefoxBrowser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }

  @Override
  public DesiredCapabilities getDefaultOptions() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    if(configTestData.testPlatform.equalsIgnoreCase("headless")){
       FirefoxBinary firefoxBinary = new FirefoxBinary();
       firefoxBinary.addCommandLineOptions("--headless");
       FirefoxOptions firefoxOptions = new FirefoxOptions();
       firefoxOptions.setBinary(firefoxBinary);
       capabilities = DesiredCapabilities.firefox();
       capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
    } else {
       capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//       capabilities.setBrowserName("firefox");
        capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
    }
      return capabilities;
   }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      configTestData.firefoxDriver = setWebDriverManage(new RemoteWebDriver(new URL(configTestData.LOCAL_GRIDHUB), getOptions(options)));
      return configTestData.firefoxDriver;
  }

}
