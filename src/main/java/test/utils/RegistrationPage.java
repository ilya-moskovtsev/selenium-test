package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

/**
 * Created by ilya on 01/03/2017.
 */
public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open(){driver.get("http://localhost/litecart/en/create_account");}

    public WebElement firstnameInput(){
        return driver.findElement(By.cssSelector("input[name=firstname]"));
    }

    public WebElement lastnameInput() {
        return driver.findElement(By.cssSelector("input[name=lastname]"));
    }

    public WebElement address1Input() {
        return driver.findElement(By.cssSelector("input[name=address1]"));
    }

    public WebElement postcodeInput() {
        return driver.findElement(By.cssSelector("input[name=postcode]"));
    }

    public WebElement cityInput() {
        return driver.findElement(By.cssSelector("input[name=city]"));
    }

    public void countrySelect() {
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countrySelect.selectByVisibleText("United States");
    }

    public void zoneSelect(){
        Select zoneSelect = new Select((driver.findElement(By.cssSelector("select[name=zone_code]"))));
        final Random random = new Random();
        zoneSelect.selectByIndex(random.nextInt(zoneSelect.getOptions().size()));
    }

    public WebElement emailInput() {
        return driver.findElement(By.cssSelector("input[name=email]"));
    }

    public WebElement phoneInput() {
        return driver.findElement(By.cssSelector("input[name=phone]"));
    }

    public WebElement passwordInput() {
        return driver.findElement(By.cssSelector("input[name=password]"));
    }

    public WebElement confirmedPasswordInput() {
        return driver.findElement(By.cssSelector("input[name=confirmed_password]"));
    }

    public WebElement createAccountButton() {
        return driver.findElement(By.cssSelector("button[name=create_account"));
    }
}
