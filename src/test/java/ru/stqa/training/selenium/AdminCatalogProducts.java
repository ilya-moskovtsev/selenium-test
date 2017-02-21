package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.Login;
import test.utils.Url;

import java.util.List;

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
    @Override
    public void before() {
        super.before();
    }

    @Test
    public void getBrowserLogs(){
        // 1) зайти в админку
        // 2) открыть каталог, категорию, которая содержит товары
        Login.adminLogin(driver, Url.ADMIN_CATALOG);
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));

        // 3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
        List<WebElement> pencils = driver.findElements(By.cssSelector(".fa-pencil"));
        for (int i = 0; i < pencils.size(); i++) {
            pencils = driver.findElements(By.cssSelector(".fa-pencil"));
            pencils.get(i).click();
            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
            driver.navigate().back();
        }
    }

    @Override
    public void after() {
        super.after();
    }
}
