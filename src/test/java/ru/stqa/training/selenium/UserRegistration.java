package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import test.utils.Url;
import test.utils.User;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 08/02/2017.
 */
public class UserRegistration {
    private WebDriver driver;
    final Random random = new Random();
    private User user;

    @Before
    public void setup(){
        user = new User();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void userRegistration(){
        driver.get(Url.MAIN.toString());
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(user.getFirstname());
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(user.getLastname());

        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(user.getAddress1());
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(user.getPostcode());

        driver.findElement(By.cssSelector("input[name=city]")).sendKeys(user.getCity());
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countrySelect.selectByVisibleText("United States");
        Select zoneSelect = new Select((driver.findElement(By.cssSelector("select[name=zone_code]"))));
        zoneSelect.selectByIndex(random.nextInt(zoneSelect.getOptions().size()));

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(user.getEmail());
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(user.getPhone());

        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(user.getPassword());

        driver.findElement(By.cssSelector("button[name=create_account")).click();

        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(user.getEmail());
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("button[name=login")).click();

        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }

}
