package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import test.utils.Login;
import test.utils.Url;

import java.io.File;

/**
 * Created by ilya on 25/01/2017.
 */
public class DifferentDriversAdminLogin {
    private WebDriver driver;

    @Before
    public void before(){
    }

    //chromedriver
    @Test
    public void tryChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
    }

    //geckodriver
    @Test
    public void tryFirefoxDriver(){
        driver = new FirefoxDriver();
    }

    //Firefox ESR 45.7.0
    @Test
    public void tryFirefoxESR(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);

        driver = new FirefoxDriver(
                new FirefoxBinary(new File("/Applications/Firefox 2.app/Contents/MacOS/firefox-bin")),
                new FirefoxProfile(),
                caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    //Firefox Nightly 52.0
    @Test
    public void tryFirefoxNightly(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);

        driver = new FirefoxDriver(
                new FirefoxBinary(new File("/Applications/Firefox 3.app/Contents/MacOS/firefox-bin")),
                new FirefoxProfile(),
                caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    //Safari > Preferences, click Advanced, then select “Show Develop menu in menu bar.”
    //Develop > Allow Remote Automation
    @Test
    public void trySafariDriver(){
        driver = new SafariDriver();
    }

    @After
    public void after(){
        Login.adminLogin(driver, Url.ADMIN);
        driver.quit();
        driver = null;
    }
}
