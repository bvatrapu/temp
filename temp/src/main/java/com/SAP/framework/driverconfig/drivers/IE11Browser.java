package com.SAP.framework.driverconfig.drivers;

import com.SAP.framework.driverconfig.BaseWebDriver;
import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.driverconfig.BaseWebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IE11Browser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, IE11Browser> {

    private ConfigTestData configTestData=null;
    public IE11Browser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }

   @Override
  public DesiredCapabilities getDefaultOptions() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities = DesiredCapabilities.internetExplorer();
        //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
      capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      configTestData.internerExplorerDriver = setWebDriverManage(new RemoteWebDriver(new URL(configTestData.LOCAL_GRIDHUB), getOptions(options)));
      return configTestData.internerExplorerDriver;
  }

}
