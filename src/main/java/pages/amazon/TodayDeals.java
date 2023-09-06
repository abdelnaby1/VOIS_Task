package pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import utils.ElementActions;

public class TodayDeals {
    private WebDriver driver;

    private By filterLoc(String filterText){
        return By.xpath("//span[contains(@data-testid,'grid-filter-DEPARTMENTS')]//span[contains(text(),'"+filterText+"')]");
    }
    private By discountLoc(String discountDeal){
        //10% off or more
       return By.xpath("//span[contains(@data-testid,'grid-filter-DISCOUNT')]//span[contains(text(),'"+discountDeal+"')]");
    }
    private By nextPageBtn = By.xpath("//ul[contains(@class,'a-pagination')]//li[contains(@class,'a-last')]//a[contains(text(),'Next') and contains(@href,'#')]");
    private By dealLoc(int dealNum){
        if (dealNum == 0){
            return By.xpath("(//div[contains(@data-testid,'grid-deals-container')]//div[contains(@data-testid,'deal-card')]/a)");
        }
//        return By.xpath("(//div[contains(@data-testid,'grid-deals-container')]//div[contains(@data-testid,'deal-card')]/a)["+dealNum+"]");
        return By.xpath("((//div[contains(@data-testid,'grid-deals-container')]//div[contains(@data-testid,'deal-card')]/a)[not(contains(@href,'/store'))])["+dealNum+"]");
    }
    // alternative for deal locator to be a product not a deal
    private By dealLocator = By.xpath("(//a[contains(@href,'Headphones')])[1]");
    public TodayDeals(WebDriver driver) {
        this.driver = driver;
    }
    public TodayDeals filterBy(String filterTerm){
        new ElementActions(driver)
                .waitForVisibility(filterLoc(filterTerm));
        RelativeLocator.RelativeBy inputLoc = RelativeLocator.with(By.tagName("input")).toLeftOf(filterLoc(filterTerm));
        new ElementActions(driver).click(inputLoc);
        return this;
    }
    public TodayDeals chooseDiscount(String discountDeal){
        new ElementActions(driver).click(discountLoc(discountDeal));
        return this;
    }
    public ProductDetailsPage openDealOrProduct(int dealNum){
        new ElementActions(driver)
                .click(dealLoc(dealNum));
        String url = driver.getCurrentUrl();
        if(url.contains("/deals") || url.contains("/deal")){
            return new DealDetails(driver).openProduct(dealNum);
        }else{
            return new ProductDetailsPage(driver);
        }
    }

    public TodayDeals gotoPageNumber(int pageNum){
        int count = 1;
       new ElementActions(driver).waitForVisibilityOfAll(dealLoc(0));
        while (count < pageNum){
            new ElementActions(driver).click(nextPageBtn);
            count++;
        }
        return this;
    }
}
