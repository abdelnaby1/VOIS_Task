package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ProductDetailsPage {
    private WebDriver driver;
    private By addToCartBtn = By.id("add-to-cart-button");
    private By productTitleLoc = By.id("productTitle");
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public SmartCartPage addToCart(){
        new ElementActions(driver)
                .click(addToCartBtn);
        return new SmartCartPage(driver);
    }

    public String getProductTitle() {
        return new ElementActions(driver).getText(productTitleLoc).toLowerCase();
    }
}
