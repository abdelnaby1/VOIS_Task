package pages.amazon.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.amazon.CartPage;
import pages.amazon.SearchResultsPage;
import pages.amazon.TodayDeals;
import utils.ElementActions;

public class Navbar {
    private WebDriver driver;
    private By searchField = By.id("twotabsearchtextbox");
    private By searchBtn = By.id("nav-search-submit-button");
    private By cartLink = By.id("nav-cart");
    private By cartCountLoc = By.id("nav-cart-count");
    private By todayDealsLinkLoc = By.xpath("//div[contains(@id,'nav-xshop')]//a[contains(text(),\"Today's Deals\")]");
    public Navbar(WebDriver driver) {
        this.driver = driver;
    }
    public SearchResultsPage searchFor(String searchTerm){
        new ElementActions(driver)
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
        new ElementActions(driver)
                .click(todayDealsLinkLoc);
        return new TodayDeals(driver);
    }
}
