package com.SAP.framework.driverconfig.drivers;

import com.SAP.framework.driverconfig.BaseWebDriver;
import com.SAP.framework.base.ConfigTestData;
import com.SAP.framework.driverconfig.BaseWebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, EdgeBrowser> {

    private ConfigTestData configTestData=null;
    public EdgeBrowser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }


  @Override
  public DesiredCapabilities getDefaultOptions() {

    DesiredCapabilities capabilities = new DesiredCapabilities();

        // driver = new EdgeDriver();
       // capabilities = DesiredCapabilities.edge();
        //    options.setUseCleanSession(true);
        capabilities.setBrowserName(DesiredCapabilities.edge().getBrowserName());
        // DesiredCapabilities capabilities = DesiredCapabilities.edge(); Tried as well
        capabilities.setCapability("acceptSslCerts", "true");
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("eager");


    return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      configTestData.edgeDriver =  setWebDriverManage(new RemoteWebDriver(new URL(configTestData.LOCAL_GRIDHUB), getOptions(options)));
      return configTestData.edgeDriver;
  }

}
