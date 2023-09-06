package tests;

import DataManager.JsonFileForDDTManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.amazon.DealDetails;
import pages.amazon.HomePage;
import pages.amazon.ProductDetailsPage;
import pages.amazon.common.Navbar;

import java.util.HashMap;
import java.util.List;

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

    @DataProvider
    public Object[][] getData(){
        JsonFileForDDTManager manager = new JsonFileForDDTManager(System.getProperty("user.dir")+"/src/test/resources/testData/products.json");
        List<HashMap<Object, Object>> data = manager.getJsonDataToMap();
        return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
    }
    @Test(dataProvider = "getData")
    public void checkAddingToCartFromSearch(HashMap<Object,Object> inputs){
        String productTitle =
                new HomePage(driver)
                .gotoUrl()
                .searchFor((String) inputs.get("searchTerm"))
                .selectProduct((Integer) inputs.get("productNum"))
                .getProductTitle();

        String recentlyAddedProductTitle
                = new ProductDetailsPage(driver)
                .addToCart()
                .goToCart()
                .getRecentlyAddedProductTitle();

        Assert.assertTrue(productTitle.contains(recentlyAddedProductTitle));
        Assert.assertEquals(new Navbar(driver).getCartCount(),1);
    }
    @Test
    public void checkAddingToCartTodayDeals(){
       String productTitle =
               new HomePage(driver)
               .gotoUrl()
               .openTodayDeals()
               .filterBy(filterTerm1)
               .filterBy(filterTerm2)
               .chooseDiscount("10% off or more")
//                       .gotoPageNumber(pageNum)
                       .openDealOrProduct(6)
                       .getProductTitle();

        String recentlyAddedProductTitle =
                new ProductDetailsPage(driver)
                .addToCart()
                .goToCart()
                .getRecentlyAddedProductTitle();

        Assert.assertTrue(productTitle.contains(recentlyAddedProductTitle));
        Assert.assertEquals(new Navbar(driver).getCartCount(),1);
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
