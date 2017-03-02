package test.utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static test.utils.Helper.isElementNotPresent;

/**
 * Created by ilya on 02/03/2017.
 */
public class ProductPage extends Page {
    private int implicitlyWait = 1;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(){
        Integer numberOfProducts = Integer.valueOf(driver.findElement(By.cssSelector(".quantity")).getText());
        if (isElementNotPresent(driver, By.cssSelector(".options select"),implicitlyWait)) {
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
        wait.until(textToBePresentInElementLocated(By.cssSelector(".quantity"), String.valueOf(numberOfProducts+1)));
    }
}
