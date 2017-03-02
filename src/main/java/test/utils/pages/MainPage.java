package test.utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ilya on 02/03/2017.
 */
public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open(){driver.get("http://localhost/litecart/");}

    public void selectFirstProduct(){
        driver.findElement(By.cssSelector("li.product")).click();
    }
}
