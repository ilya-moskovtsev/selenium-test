package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import test.utils.Customer;
import test.utils.Login;
import test.utils.Url;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertTrue;

/**
 * Created by ilya on 08/02/2017.
 */
public class CustomerRegistration {
    private WebDriver driver;
    final Random random = new Random();
    private Customer customer;

    @Before
    public void setup(){
        customer = new Customer();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void customerRegistration(){
        //сохраняем множество идентификаторов существующих клиентов до регистрации нового
        Set<String> oldCustomerIds = getCustomerIds();

        registerNewCustomer(customer);

        //сохраняем множество идентификаторов существующих клиентов после регистрации нового
        Set<String> newCustomerIds = getCustomerIds();

        //проверяем, что клиенты существовавшие до добавления нового никуда не делись
        assertTrue(newCustomerIds.containsAll(oldCustomerIds));
        //проверяем, что в множестве появился новый клиент
        assertTrue(newCustomerIds.size() == oldCustomerIds.size() + 1);

//        driver.findElement(By.linkText("Logout")).click();
//
//        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(customer.getEmail());
//        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(customer.getPassword());
//        driver.findElement(By.cssSelector("button[name=login")).click();
//
//        driver.findElement(By.linkText("Logout")).click();
    }

    private void registerNewCustomer(Customer customer) {
        driver.get(Url.MAIN.toString());
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(customer.getFirstname());
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(customer.getLastname());

        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(customer.getAddress1());
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(customer.getPostcode());

        driver.findElement(By.cssSelector("input[name=city]")).sendKeys(customer.getCity());
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countrySelect.selectByVisibleText("United States");
        Select zoneSelect = new Select((driver.findElement(By.cssSelector("select[name=zone_code]"))));
        zoneSelect.selectByIndex(random.nextInt(zoneSelect.getOptions().size()));

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(customer.getEmail());
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(customer.getPhone());

        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(customer.getPassword());
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(customer.getPassword());

        driver.findElement(By.cssSelector("button[name=create_account")).click();
    }

    private Set<String> getCustomerIds() {
        Login.adminLogin(driver, Url.ADMIN_CUSTOMERS);
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                    .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                    .collect(toSet());
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }

}
