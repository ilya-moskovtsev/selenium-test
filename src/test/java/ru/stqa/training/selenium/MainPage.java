package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by ilya on 04/02/2017.
 */
public class MainPage {
    WebDriver driver;

    @Before
    public void before(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void eachProductShouldHaveOneLabel(){
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : products) {
            assertTrue(product.findElements(By.cssSelector("div.sticker")).size() == 1);
        }
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
