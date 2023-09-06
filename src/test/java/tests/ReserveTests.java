package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ksrtc.HomePage;

public class ReserveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
    }

    @Test
    public void reserveTest(){
        new HomePage(driver)
                .gotoUrl()
                .chooseRoute("Chikkamagaluru","Bengaluru")
                .chooseArrivalDate(1)
                .search()
                .selectService(1)
                .selectSeat(1)
                .selectBoardAndDroppingPoints()
                .fillCustomerDetails("6789125987","ahmed@gmail.com")
                .fillPassengerDetails("Ahmed","MALE",
                        "25","GENERAL PUBLIC",
                        "Egypt","98756544554","Hotel number 6",
                        "1998","Aug",26)
                .makePayment();

//        Assert.assertEquals(BrowserActions.getPageUrl(driver),"https://ksrtc.in/oprs-web/booking/revamp/paxInfo.do");
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
