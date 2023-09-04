package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.common.Navbar;

public class AddToCartTests {
    WebDriver driver;

    String searchTerm = "car accessories";
    int productNum = 1;
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
                .getProductTitle()
                        .toLowerCase();

        String recentlyAddedProductTitle
                = new ProductDetailsPage(driver)
                .addToCart()
                .goToCart()
                .getRecentlyAddedProductTitle()
                        .toLowerCase();

        Assert.assertTrue(productTitle.contains(recentlyAddedProductTitle.substring(0, recentlyAddedProductTitle.length() - 3)));
        Assert.assertEquals(new Navbar(driver).getCartCount(),1);
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
