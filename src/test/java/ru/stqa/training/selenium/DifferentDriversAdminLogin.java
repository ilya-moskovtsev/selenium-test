package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

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
        driver = new ChromeDriver();
    }

    //geckodriver
    @Test
    public void tryFirefoxDriver(){
        driver = new FirefoxDriver();
    }

    //Safari > Preferences, click Advanced, then select “Show Develop menu in menu bar.”
    //Develop > Allow Remote Automation
    @Test
    public void trySafariDriver(){
        driver = new SafariDriver();
    }

    @After
    public void after(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.quit();
        driver = null;
    }
}
