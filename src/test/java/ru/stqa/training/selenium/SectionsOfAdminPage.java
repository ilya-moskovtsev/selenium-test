package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import test.utils.Login;
import test.utils.Url;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


/**
 * Created by ilya on 04/02/2017.
 */
public class SectionsOfAdminPage {
    private WebDriver driver;
    List<WebElement> apps;
    List<WebElement> selectedAppListItems;

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public void before()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void pageHeaderShouldBePresent() {
        Login.adminLogin(driver, Url.ADMIN);

        apps = driver.findElements(By.cssSelector("li#app-"));
        for (int i = 0; i < apps.size(); i++) {
            apps = driver.findElements(By.cssSelector("li#app-"));
            apps.get(i).click();

            apps = driver.findElements(By.cssSelector("li#app-"));
            assertTrue(isElementPresent(By.cssSelector("h1")));
            if (isElementPresent(By.cssSelector("li#app-.selected ul")))
            {
                selectedAppListItems = driver.findElements(By.cssSelector("li#app-.selected ul li"));
                for (int j = 0; j < selectedAppListItems.size(); j++) {
                    selectedAppListItems = driver.findElements(By.cssSelector("li#app-.selected ul li"));
                    selectedAppListItems.get(j).click();
                    assertTrue(isElementPresent(By.cssSelector("h1")));
                }
            }
        }
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
