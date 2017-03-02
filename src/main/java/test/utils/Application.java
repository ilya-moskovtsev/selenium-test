package test.utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

import static java.util.stream.Collectors.toSet;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by ilya on 28/02/2017.
 */
public class Application {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    public BrowserMobProxy proxy;
    private RegistrationPage registrationPage;

    public EventFiringWebDriver getDriver() {
        return driver;
    }

    public BrowserMobProxy getProxy() {
        return proxy;
    }

    public Application() {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        /**
         * Try BrowserMobProxy
         */
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        // configure it as a desired capability
        cap.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new EventFiringWebDriver(new ChromeDriver(cap));
        driver.register(new MyListener());
        wait = new WebDriverWait(driver, 10);
        registrationPage = new RegistrationPage(driver);
    }

    public void quit(){ driver.quit(); driver = null;}

    public void registerNewCustomer(Customer customer) {
        registrationPage.open();

        registrationPage.firstnameInput().sendKeys(customer.getFirstname());
        registrationPage.lastnameInput().sendKeys(customer.getLastname());

        registrationPage.address1Input().sendKeys(customer.getAddress1());

        registrationPage.postcodeInput().sendKeys(customer.getPostcode());
        registrationPage.cityInput().sendKeys(customer.getCity());

        registrationPage.countrySelect();
        registrationPage.zoneSelect();

        registrationPage.emailInput().sendKeys(customer.getEmail());
        registrationPage.phoneInput().sendKeys(customer.getPhone());

        registrationPage.passwordInput().sendKeys(customer.getPassword());
        registrationPage.confirmedPasswordInput().sendKeys(customer.getPassword());

        registrationPage.createAccountButton().click();
    }

    public Set<String> getCustomerIds() {
        Login.adminLogin(driver, Url.ADMIN_CUSTOMERS);
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }

    public void googleSearch(String searchFor) {
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys(searchFor);
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs(searchFor + " - Google Search"));
    }

    public void printBrowserLogs(String url, String logType) {
        driver.get(url);
        System.out.println("Available log types -> " + driver.manage().logs().getAvailableLogTypes());
        System.out.println("Printing log type -> " + logType);
        driver.manage().logs().get(logType).forEach(l -> System.out.println(l));
    }
}
