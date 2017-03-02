package test.utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static test.utils.Helper.isElementNotPresent;

/**
 * Created by ilya on 02/03/2017.
 */
public class CartPage extends Page {
    private int implicitlyWait = 1;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void deleteAllProductsFromCart(){
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
}
