package pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CartPage {
    private WebDriver driver;
    private By recentlyAddedProductLoc =By.xpath("(//*[contains(@id,'activeCartViewForm')]//ul//span[contains(@class,'sc-product-title')])[1]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getRecentlyAddedProductTitle(){
        String recentlyAddedProductTitle =  new ElementActions(driver)
                .getText(recentlyAddedProductLoc)
                .toLowerCase();
        return recentlyAddedProductTitle.substring(0, recentlyAddedProductTitle.length() - 3);
    }
}
