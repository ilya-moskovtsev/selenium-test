package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.utils.Url;

import java.util.List;
import java.util.Set;

import static test.utils.Login.adminLogin;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
/**
 * [x] Задание 14. Проверьте, что ссылки открываются в новом окне
 * <p>
 * Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
 * <p>
 * Сценарий должен состоять из следующих частей:
 * <p>
 * 1) зайти в админку<br>
 * 2) открыть пункт меню Countries (или страницу)<br>
 * 3) открыть на редактирование какую-нибудь страну или начать создание новой<br>
 * 4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
 * <p>
 * Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank". Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
 * <p>
 * Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.
 * <p>
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 * <p>
 * -----
 * <p>
 * Уложите созданный файл, содержащий сценарий, в ранее созданный репозиторий. В качестве ответа на задание отправьте ссылку на свой репозиторий и указание, какой именно файл содержит нужный сценарий.
 */
public class NewWindow {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newWindow(){
        //1) зайти в админку
        //2) открыть пункт меню Countries (или страницу)
        adminLogin(driver, Url.ADMIN_COUNTRIES);
        //3) открыть на редактирование какую-нибудь страну или начать создание новой
        driver.findElement(By.linkText("Add New Country")).click();
        //4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
        List<WebElement> externalLinks = driver.findElements(By.cssSelector(".fa-external-link"));
        //Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
        // Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
        // запоминаем идентификатор текущего окна
        String originalWindow = driver.getWindowHandle();
        for (int i = 0; i < externalLinks.size(); i++) {
            // открывает новое окно
            externalLinks.get(i).click();
            // ожидание появления нового окна
            wait.until(numberOfWindowsToBe(2));
            //потом переключиться в новое окно
            Set<String> existingWindows = driver.getWindowHandles();
            existingWindows.remove(originalWindow);
            driver.switchTo().window(existingWindows.iterator().next());
            // закрыть его,
            driver.close();
            // вернуться обратно,
            driver.switchTo().window(originalWindow);
            // и повторить эти действия для всех таких ссылок.
        }
    }

    @After
    public void after(){
        driver.quit();
        driver = null;
    }
}
