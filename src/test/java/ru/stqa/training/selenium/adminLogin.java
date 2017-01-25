package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by ilya on 25/01/2017.
 */
public class adminLogin {
    private WebDriver driver;
    @Before
    public void before(){
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
