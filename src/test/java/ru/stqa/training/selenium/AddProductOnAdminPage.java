package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import test.utils.Login;
import test.utils.Url;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * [x] Задание 12. Сделайте сценарий добавления товара
 * <p>
 * Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
 * <p>
 * Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
 * <p>
 * Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
 * <p>
 * Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу (о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).
 * <p>
 * Картинку с изображением товара нужно уложить в репозиторий вместе с кодом. При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет. Надо средствами языка программирования преобразовать относительный путь в абсолютный.
 * <p>
 * После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
 * <p>
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 * <p>
 * -----
 * <p>
 * Уложите созданный файл, содержащий сценарий, в ранее созданный репозиторий. В качестве ответа на задание отправьте ссылку на свой репозиторий и указание, какой именно файл содержит нужный сценарий.
 */
public class AddProductOnAdminPage {
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
    public void addProduct(){
        Login.adminLogin(driver, Url.ADMIN);
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        if ( !driver.findElement(By.cssSelector("input[type=radio]")).isSelected() )
        {
            driver.findElement(By.cssSelector("input[type=radio]")).click();
        }
        driver.findElement(By.name("name[en]")).sendKeys("ProductName");
        driver.findElement(By.name("code")).sendKeys("ProductCode");

        List<WebElement> categories = driver.findElements(By.name("categories[]"));
        for (WebElement checkbox : categories) {
            if ( !checkbox.isSelected() )
            {
                checkbox.click();
            }
        }

        Select defaultCategory = new Select(driver.findElement(By.name("default_category_id")));
        defaultCategory.selectByVisibleText("Subcategory");

        List<WebElement> productGroups = driver.findElements(By.name("product_groups[]"));
        for (WebElement checkbox : productGroups) {
            if ( !checkbox.isSelected() )
            {
                checkbox.click();
            }
        }

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("100");

        File f = new File("img/cartoon_duck.jpg");
        driver.findElement(By.name("new_images[]")).sendKeys(f.getAbsolutePath());

        driver.findElement(By.name("date_valid_from")).sendKeys("27/02/2017");

        driver.findElement(By.name("date_valid_to")).sendKeys("27/02/2018");

        driver.findElement(By.linkText("Information")).click();

        Select manufacturerId = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturerId.selectByVisibleText("ACME Corp.");

//        Select supplierId = new Select(driver.findElement(By.name("supplier_id")));
//        manufacturerId.selectByVisibleText("");

        driver.findElement(By.name("keywords")).sendKeys("keyword1, keyword2");

        driver.findElement(By.name("short_description[en]")).sendKeys("Short description");

        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Description");

        driver.findElement(By.name("head_title[en]")).sendKeys("Head Title");

        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta Description");

        driver.findElement(By.linkText("Prices")).click();

        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("10");

        Select purchasePriceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrencyCode.selectByVisibleText("US Dollars");

//        Select taxClassId = new Select(driver.findElement(By.name("tax_class_id")));
//        taxClassId.selectByVisibleText("");

        driver.findElement(By.name("save")).click();

        assertTrue(driver.findElement(By.linkText("ProductName")).isDisplayed());
    }

    @After
    public void after() {
        driver.quit();
        driver = null;
    }
}
