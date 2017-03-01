package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import test.utils.Login;
import test.utils.Url;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * [x] Задание 17. Проверьте отсутствие сообщений в логе браузера
 * <br>
 * Сделайте сценарий, который проверяет, не появляются ли в логе браузера
 * сообщения при открытии страниц в учебном приложении, а именно -- страниц
 * товаров в каталоге в административной панели.
 * <br>
 * Сценарий должен состоять из следующих частей:
 * <br>
 * 1) зайти в админку
 * <br>
 * 2) открыть каталог, категорию, которая содержит товары
 * <br>
 * 3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
 * <br>
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 * <br>
 * -----
 * <br>
 * Уложите созданный файл, содержащий сценарий, в ранее созданный репозиторий. В качестве ответа на задание отправьте ссылку на свой репозиторий и указание, какой именно файл содержит нужный сценарий.Created by ilya on 21/02/2017.
 */
public class AdminCatalogProducts extends TestBase {

    @Test
    public void browserLogsShouldBeEmpty(){
        // 1) зайти в админку
        // 2) открыть каталог, категорию, которая содержит товары
        Login.adminLogin(app.getDriver(), Url.ADMIN_CATALOG);
        // сохраняем логи в переменную, т.к. получение логов очищает список
        LogEntries catalogLogEntries = app.getDriver().manage().logs().get("browser");
        catalogLogEntries.forEach(l -> System.out.println(l));
        assertTrue(catalogLogEntries.getAll().isEmpty());
        // 3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
        List<WebElement> pencils = app.getDriver().findElements(By.cssSelector(".fa-pencil"));
        for (int i = 0; i < pencils.size(); i++) {
            pencils = app.getDriver().findElements(By.cssSelector(".fa-pencil"));
            pencils.get(i).click();
            LogEntries productLogEntries = app.getDriver().manage().logs().get("browser");
            productLogEntries.forEach(l -> System.out.println(l));
            assertTrue(productLogEntries.getAll().isEmpty());
            app.getDriver().navigate().back();
        }
    }
}
