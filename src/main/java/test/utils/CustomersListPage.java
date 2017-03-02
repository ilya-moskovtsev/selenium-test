package test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by ilya on 02/03/2017.
 */
public class CustomersListPage extends Page {

    public CustomersListPage(WebDriver driver) {
        super(driver);
    }

    public CustomersListPage open() {
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        return this;
    }

    public Set<String> getCustomerIds() {
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }
}
