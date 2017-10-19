package com.scopeddirtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserStarter {

    private String gridAddress;

    public BrowserStarter(String gridAddress) {
        this.gridAddress = gridAddress;
    }

    public void startAndClose() {
        WebDriver driver = null;
        try {
            driver = buildChrome();
            driver.get("https://www.google.com");
        } catch (Throwable t) {
            if (driver != null)
                driver.close();
            throw new RuntimeException(t);
        } finally {
            if (driver != null)
                driver.close();
        }
    }

    private WebDriver buildChrome() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, createChromeOptions());
        URL url;
        try {
            url = new URL(gridAddress);

            return new RemoteWebDriver(url, capabilities);
        } catch (Exception e) {
            throw new WebDriverInitializerException(e);
        }
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        //..
        options.addArguments("--no-sandbox");

        return options;
    }
}
