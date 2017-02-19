package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.utils.Url;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.Platform.MAC;
import static org.openqa.selenium.Platform.WINDOWS;

/**
 * Created by ilya on 17/02/2017.
 */
public class SeleniumServer {
    WebDriver driver;

    @Before
    public void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setPlatform(WINDOWS);
        capabilities.setPlatform(MAC);
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://192.168.1.68:4444/wd/hub"), capabilities);
    }

    @Test
    public void tryRemoteWebDriver(){
        driver.get(Url.MAIN.toString());
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }

}
