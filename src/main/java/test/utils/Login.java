package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ilya on 11/02/2017.
 */
public class Login {
    public static void adminLogin(WebDriver driver, Url url) {
        driver.get(url.toString());
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
