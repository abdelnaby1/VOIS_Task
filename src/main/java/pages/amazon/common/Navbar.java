package pages.amazon.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.amazon.CartPage;
import pages.amazon.SearchResultsPage;
import pages.amazon.TodayDeals;
import utils.BrowserActions;
import utils.ElementActions;

public class Navbar {
    private WebDriver driver;
    public String urlOfDeals = "//https://www.amazon.com/gp/goldbox?ref_=nav_cs_gb";
    private By searchField = By.id("twotabsearchtextbox");
    private By searchBtn = By.id("nav-search-submit-button");
    private By cartLink = By.id("nav-cart");
    private By cartCountLoc = By.id("nav-cart-count");
    private By todayDealsLinkLoc = By.xpath("//div[contains(@id,'nav-xshop')]//a[contains(text(),\"Today's Deals\")]");
    public Navbar(WebDriver driver) {
        this.driver = driver;
    }
    public SearchResultsPage searchFor(String searchTerm){
        ElementActions elementActions = new ElementActions(driver);
        if (elementActions.isDisplayed(todayDealsLinkLoc)){
            elementActions
                    .type(searchField,searchTerm)
                    .click(searchBtn);
        }else {
            driver.navigate().refresh();
        }
        elementActions
                .type(searchField,searchTerm)
                .click(searchBtn);
        return new SearchResultsPage(driver);
    }
    public CartPage gotoCart(){
        new ElementActions(driver)
                .click(cartLink);
        return new CartPage(driver);
    }
    public int getCartCount(){
        return Integer.parseInt(new ElementActions(driver).getText(cartCountLoc));
    }

    public TodayDeals openTodayDeals(){
        ElementActions elementActions = new ElementActions(driver);
        if (elementActions.isDisplayed(todayDealsLinkLoc)){
            elementActions
                    .click(todayDealsLinkLoc);
        }else {
            driver.navigate().refresh();
        }
        elementActions
                .click(todayDealsLinkLoc);
        return new TodayDeals(driver);
    }
}
