package pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class DealDetails {
    private WebDriver driver;
    private By producLoc(int productNum){
        return By.xpath("(//div[contains(@class,'a-section octopus-dlp-asin-title')])["+productNum+"]/a");
    }

    public DealDetails(WebDriver driver) {
        this.driver = driver;
    }
    public ProductDetailsPage openProduct(int productNum){
        new ElementActions(driver)
                .click(producLoc(productNum));
        return new ProductDetailsPage(driver);
    }
}
