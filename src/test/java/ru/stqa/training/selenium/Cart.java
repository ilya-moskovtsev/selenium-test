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
import org.openqa.selenium.support.ui.WebDriverWait;
import test.utils.Url;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static test.utils.Helper.isElementNotPresent;

/**
 * [x] Задание 13. Сделайте сценарий работы с корзиной
 * <p>
 * Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
 * <p>
 * 1) открыть главную страницу<br>
 * 2) открыть первый товар из списка<br>
 * 3) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)<br>
 * 4) подождать, пока счётчик товаров в корзине обновится<br>
 * 5) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара<br>
 * 6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)<br>
 * 7) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
 * <p>
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 * <p>
 * -----
 * <p>
 * Уложите созданный файл, содержащий сценарий, в ранее созданный репозиторий. В качестве ответа на задание отправьте ссылку на свой репозиторий и указание, какой именно файл содержит нужный сценарий.
 */
public class Cart {
    private WebDriver driver;
    private WebDriverWait wait;
    private int implicitlyWait = 1;

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void productShouldBeAddedToAndDeletedFromCart() {

        for (int i = 0; i < 3; i++) {
            //1) открыть главную страницу
            driver.get(Url.MAIN.toString());
            //2) открыть первый товар из списка
            driver.findElement(By.cssSelector("li.product")).click();

            if (isElementNotPresent(driver,By.cssSelector(".options select"),implicitlyWait)) {
                //3) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
                driver.findElement(By.name("add_cart_product")).click();
            } else {
                //если требуется выбрать размер, то выбираем первый вариант
                Select size = new Select(driver.findElement(By.cssSelector(".options select")));
                size.selectByIndex(1);
                //3) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
                driver.findElement(By.name("add_cart_product")).click();
            }

            //4) подождать, пока счётчик товаров в корзине обновится
            wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"), String.valueOf(i+1)));
            //5) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
        }
        //6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        driver.findElement(By.linkText("Checkout »")).click();
        //7) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
        for (int i = 0; i < 3; i++) {
            if(isElementNotPresent(driver, By.cssSelector("#box-checkout-summary"), implicitlyWait))
                break;
            WebElement orderSummary = driver.findElement(By.cssSelector("#box-checkout-summary"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(orderSummary));
        }
    }

    @After
    public void after() {
        driver.quit();
        driver = null;
    }
}
