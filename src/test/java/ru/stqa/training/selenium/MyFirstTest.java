package ru.stqa.training.selenium;

import net.lightbody.bmp.core.har.Har;
import org.junit.Test;

/**
 * Created by ilya on 24/01/2017.
 */
public class MyFirstTest extends TestBase{

    @Test
    public void googleSearch(){
        app.googleSearch("webdriver");
    }

    /**
     * Get Browser Logs
     */
    @Test
    public void printBrowserLogs(){
        app.printBrowserLogs("http://selenium2.ru", "browser");
    }

    /**
     * Try BrowserMobProxy
     */
    @Test
    public void tryBrowserMobProxy(){
        // создаем новый архив информации о запросах
        app.getProxy().newHar();
        app.getDriver().get("http://selenium2.ru");
        // останавлиаем proxy
        Har har = app.getProxy().endHar();
        // список информации о всех перехваченных запросах
        har.getLog().getEntries().forEach(l -> System.out.println(
                // сначала выводим код ответа
                l.getResponse().getStatus() + ":" +
                // потом адрес запроса
                l.getRequest().getUrl()
        ));
    }
}
