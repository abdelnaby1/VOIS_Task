package pages;

import org.openqa.selenium.WebDriver;
import pages.common.Navbar;

public class SmartCartPage {
    private WebDriver driver;

    public SmartCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage goToCart(){
        return new Navbar(driver).gotoCart();
    }
}
