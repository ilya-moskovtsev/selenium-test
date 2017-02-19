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
public class SeleniumServerGrid {
    WebDriver driver1;
    WebDriver driver2;

    @Before
    public void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        capabilities.setPlatform(MAC);
        driver1 = new RemoteWebDriver(new URL("http://192.168.1.68:4444/wd/hub"), capabilities);

        capabilities.setPlatform(WINDOWS);
        driver2 = new RemoteWebDriver(new URL("http://192.168.1.68:4444/wd/hub"), capabilities);
    }

    @Test
    public void tryRemoteWebDriver(){
        driver1.get(Url.MAIN.toString());
        driver2.get(Url.MAIN.toString());
    }

    @After
    public void after(){
        driver1.quit();
        driver1 = null;
        driver2.quit();
        driver2 = null;
    }

}
