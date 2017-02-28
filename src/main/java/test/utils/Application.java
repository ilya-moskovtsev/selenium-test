package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by ilya on 28/02/2017.
 */
public class Application {
    private WebDriver driver;
    private WebDriverWait wait;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void quit(){ driver.quit();}

    public void registerNewCustomer(Customer customer) {
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
        final Random random = new Random();
        zoneSelect.selectByIndex(random.nextInt(zoneSelect.getOptions().size()));

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(customer.getEmail());
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(customer.getPhone());

        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(customer.getPassword());
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(customer.getPassword());

        driver.findElement(By.cssSelector("button[name=create_account")).click();
    }

    public Set<String> getCustomerIds() {
        Login.adminLogin(driver, Url.ADMIN_CUSTOMERS);
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }
}
