package pages.amazon;

import org.openqa.selenium.WebDriver;
import pages.amazon.common.Navbar;

public class HomePage {
    private WebDriver driver;

    private String url = "https://www.amazon.com/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage gotoUrl(){
        driver.get(url);
        return this;
    }
    public SearchResultsPage searchFor(String searchTerm){
       return new Navbar(driver).searchFor(searchTerm);
    }
    public TodayDeals openTodayDeals(){
        return new Navbar(driver).openTodayDeals();
    }
}
