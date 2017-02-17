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

/**
 * Created by ilya on 17/02/2017.
 */
public class SeleniumServer {
    WebDriver driver;

    @Before
    public void before() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://172.16.62.142:4444/wd/hub"), DesiredCapabilities.chrome());
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
