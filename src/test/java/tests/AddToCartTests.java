package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DealDetails;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.common.Navbar;

public class AddToCartTests {
    WebDriver driver;

    String searchTerm = "car accessories";
    int productNum = 1;
    String filterTerm1 = "Headphones";
    String filterTerm2 = "Grocery";
    int pageNum = 3;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
    }

    @Test
    public void checkAddingToCartFromSearch(){
        String productTitle =
                new HomePage(driver)
                .gotoUrl()
                .searchFor(searchTerm)
                .selectProduct(productNum)
                .getProductTitle();

        String recentlyAddedProductTitle
                = new ProductDetailsPage(driver)
                .addToCart()
                .goToCart()
                .getRecentlyAddedProductTitle();

        Assert.assertTrue(productTitle.contains(recentlyAddedProductTitle.substring(0, recentlyAddedProductTitle.length() - 3)));
        Assert.assertEquals(new Navbar(driver).getCartCount(),1);
    }
    @Test
    public void checkAddingToCartTodayDeals(){
       String productTitle;
        Boolean isDealPage =
               new HomePage(driver)
               .gotoUrl()
               .openTodayDeals()
               .filterBy(filterTerm1)
               .filterBy(filterTerm2)
               .chooseDiscount("10% off or more")
//                       .gotoPageNumber(pageNum)
                       .openDealOrProduct(6);
        if (isDealPage){
            productTitle = new DealDetails(driver).openProduct(productNum).getProductTitle();
        }else{
            productTitle = new ProductDetailsPage(driver).getProductTitle();
        }

        String recentlyAddedProductTitle =
                new ProductDetailsPage(driver)
                .addToCart()
                .goToCart()
                .getRecentlyAddedProductTitle();

        Assert.assertTrue(productTitle.contains(recentlyAddedProductTitle.substring(0, recentlyAddedProductTitle.length() - 3)));
        Assert.assertEquals(new Navbar(driver).getCartCount(),1);
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
