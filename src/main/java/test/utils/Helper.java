package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 13/02/2017.
 */
public class Helper {
    public static boolean isElementNotPresent(WebDriver driver, By locator, int implicitlyWait){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean isNotPresent = driver.findElements(locator).size() == 0;
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        return isNotPresent;
    }
}
