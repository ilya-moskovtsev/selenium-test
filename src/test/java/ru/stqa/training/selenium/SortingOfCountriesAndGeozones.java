package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by ilya on 04/02/2017.
 */
public class SortingOfCountriesAndGeozones {
    private WebDriver driver;

    @Before
    public void before()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void countriesAndGeozonesShouldBeSorted() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countryRows = driver.findElements(By.cssSelector("tr.row"));
        List<String> checkCountrySort = new ArrayList<>();
        List<String> sortedCountries = new ArrayList<>();
        List<String> haveZones = new ArrayList<>();

//        check country names are sorted
        for (WebElement row : countryRows) {
            WebElement countryName = row.findElement(By.cssSelector("td:nth-of-type(5) a"));
            checkCountrySort.add(countryName.getText());

            if (!row.findElement(By.cssSelector("td:nth-of-type(6)")).getText().equals("0")){
                haveZones.add(countryName.getAttribute("href"));
            }
        }
        sortedCountries.addAll(checkCountrySort);
        Collections.sort(sortedCountries);

        for (int i = 0; i < checkCountrySort.size(); i++) {
            assertTrue(checkCountrySort.get(i).equals(sortedCountries.get(i)));
        }

//        check zone names are sorted
        for (String url : haveZones) {
            driver.get(url);
            List<WebElement> zoneRows = driver.findElements(By.cssSelector("#table-zones tr:not(.header)"));
            List<String> checkZonesSort = new ArrayList<>();
            List<String> sortedZones = new ArrayList<>();
            for (WebElement row : zoneRows) {
                checkZonesSort.add(row.findElement(By.cssSelector("td:nth-of-type(3)")).getText());
            }

            checkZonesSort.remove(checkZonesSort.size()-1);
            sortedZones.addAll(checkZonesSort);
            Collections.sort(sortedZones);

            for (int i = 0; i < checkZonesSort.size(); i++) {
                assertTrue(checkZonesSort.get(i).equals(sortedZones.get(i)));
            }
        }

    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
