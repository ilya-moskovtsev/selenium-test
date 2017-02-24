package ru.stqa.training.selenium;

import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by ilya on 24/01/2017.
 */
public class MyFirstTest extends TestBase{
    @Before
    public void before() {
        super.before();
    }

    @Test
    public void test(){
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Google Search"));
    }

    /**
     * Get Browser Logs
     */
    @Test
    public void getBrowserLogs(){
        driver.get("http://selenium2.ru");
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
        driver.manage().logs().get("performance").forEach(l -> System.out.println(l));
    }

    /**
     * Try BrowserMobProxy
     */
    @Test
    public void tryBrowserMobProxy(){
        // создаем новый архив информации о запросах
        proxy.newHar();
        driver.get("http://selenium2.ru");
        // останавлиаем proxy
        Har har = proxy.endHar();
        // список информации о всех перехваченных запросах
        har.getLog().getEntries().forEach(l -> System.out.println(
                // сначала выводим код ответа
                l.getResponse().getStatus() + ":" +
                // потом адрес запроса
                l.getRequest().getUrl()
        ));
    }

    @After
    public void after(){
        super.after();
    }
}
