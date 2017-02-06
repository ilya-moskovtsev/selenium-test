package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Created by ilya on 05/02/2017.
 */
@RunWith(Parameterized.class)
public class MainPageToProductPage {
    @Parameters
    public static List<WebDriver> data() {
        return Arrays.asList(new WebDriver[]{
                new ChromeDriver(),
                new SafariDriver(),
                new FirefoxDriver()
        });
    }

    private WebDriver driver;

    public MainPageToProductPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void productShouldHaveCorrectPage()
    {
        driver.get("http://localhost/litecart/");
        WebElement product1 = driver.findElement(By.cssSelector("#box-campaigns li"));
        String name1 = product1.findElement(By.cssSelector(".name")).getText();

        String regularPrice1 = product1.findElement(By.cssSelector(".regular-price")).getText();
        String regularPriceColor1 = product1.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularPriceTextDecoration1 = product1.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        Dimension regularPriceSize1 = product1.findElement(By.cssSelector(".regular-price")).getSize();

        String campaignPrice1 = product1.findElement(By.cssSelector(".campaign-price")).getText();
        String campaignPriceColor1 = product1.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceFontWeight1 = product1.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        Dimension campaignPriceSize1 = product1.findElement(By.cssSelector(".campaign-price")).getSize();

        product1.click();

        if (driver instanceof SafariDriver) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement product2 = driver.findElement(By.cssSelector("#box-product"));
        String name2 = product2.findElement(By.cssSelector("h1.title")).getText();

        String regularPrice2 = product2.findElement(By.cssSelector(".regular-price")).getText();
        String regularPriceColor2 = product2.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularPriceTextDecoration2 = product2.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        Dimension regularPriceSize2 = product2.findElement(By.cssSelector(".regular-price")).getSize();

        String campaignPrice2 = product2.findElement(By.cssSelector(".campaign-price")).getText();
        String campaignPriceColor2 = product2.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignPriceFontWeight2 = product2.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        Dimension campaignPriceSize2 = product2.findElement(By.cssSelector(".campaign-price")).getSize();

//        а) на главной странице и на странице товара совпадает текст названия товара
        assertTrue(name1.equals(name2));
//        б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        assertTrue(regularPrice1.equals(regularPrice2));
        assertTrue(campaignPrice1.equals(campaignPrice2));
//        в) обычная цена серая и зачёркнутая,
        if (driver instanceof ChromeDriver) {
            assertTrue(regularPriceColor1.equals("rgba(119, 119, 119, 1)"));
            assertTrue(regularPriceColor2.equals("rgba(102, 102, 102, 1)"));
        }

        if (driver instanceof FirefoxDriver || driver instanceof SafariDriver){
            assertTrue(regularPriceColor1.equals("rgb(119, 119, 119)"));
            assertTrue(regularPriceColor2.equals("rgb(102, 102, 102)"));
        }

        assertTrue(regularPriceTextDecoration1.equals("line-through"));
        assertTrue(regularPriceTextDecoration2.equals("line-through"));
//        а акционная цена красная и жирная
        if (driver instanceof ChromeDriver) {
            assertTrue(campaignPriceColor1.equals("rgba(204, 0, 0, 1)"));
            assertTrue(campaignPriceColor2.equals("rgba(204, 0, 0, 1)"));
        }

        if (driver instanceof FirefoxDriver || driver instanceof SafariDriver) {
            assertTrue(campaignPriceColor1.equals("rgb(204, 0, 0)"));
            assertTrue(campaignPriceColor2.equals("rgb(204, 0, 0)"));
        }

        if (driver instanceof ChromeDriver || driver instanceof SafariDriver) {
            assertTrue(campaignPriceFontWeight1.equals("bold"));
            assertTrue(campaignPriceFontWeight2.equals("bold"));
        }

        if (driver instanceof FirefoxDriver) {
            assertTrue(campaignPriceFontWeight1.equals("900"));
            assertTrue(campaignPriceFontWeight2.equals("700"));
        }
//        (это надо проверить на каждой странице независимо,
//        при этом цвета на разных страницах могут не совпадать)

//        г) акционная цена крупнее, чем обычная (это надо проверить на каждой странице независимо)
        assertTrue((campaignPriceSize1.getHeight()*campaignPriceSize1.getWidth())
                > (regularPriceSize1.getHeight()*regularPriceSize1.getWidth()));
        assertTrue((campaignPriceSize2.getHeight()*campaignPriceSize2.getWidth())
                > (regularPriceSize2.getHeight()*regularPriceSize2.getWidth()));
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
