package pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class SearchResultsPage {
    private WebDriver driver;
    private By productLoc(int productNum){
        // as I noticed in amazon dom the 2 locators doesn't reflect to any product
        // and the first product begin with index 0,
        // so I had to do this calculation as the first product loc will begin with index 3;
//        productNum+=2;
        return By.xpath("(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS-')]//div[contains(@class,'s-title-instructions-style')]/h2/a)["+productNum+"]");
    }

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductDetailsPage selectProduct(int productNum){
        new ElementActions(driver).click(productLoc(productNum));
        return new ProductDetailsPage(driver);
    }
}
