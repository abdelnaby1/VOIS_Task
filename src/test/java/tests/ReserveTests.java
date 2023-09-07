package tests;

import DataManager.JsonFileForDDTManager;
import DataManager.JsonFileManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ksrtc.HomePage;

public class ReserveTests {
    WebDriver driver;
    JsonFileManager manager;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
        manager = new JsonFileManager(System.getProperty("user.dir")+"/src/test/resources/testData/reservationData.json");

    }

    @Test
    public void reserveTest(){
        new HomePage(driver)
                .gotoUrl()
                .chooseRoute(manager.getAsString("from"),manager.getAsString("to"))
                .chooseArrivalDate(Integer.parseInt(manager.getAsString("afterHowManyDaysFromNow")))
                .search()
                .selectService(Integer.parseInt(manager.getAsString("serviceNum")))
                .selectSeat(Integer.parseInt(manager.getAsString("seatNum")))
                .selectBoardAndDroppingPoints()
                .fillCustomerDetails(manager.getAsString("customerMobile"),
                        manager.getAsString("customerEmail"))
                .fillPassengerDetails(manager.getAsString("passengerName"),
                        manager.getAsString("passengerGender"),
                        manager.getAsString("passengerAge"),
                        manager.getAsString("passengerOoncession"),
                        manager.getAsString("passengerCountry"),
                        manager.getAsString("passengerPassport"),
                        manager.getAsString("passengerAddress"),
                        manager.getAsString("passengerdobYear"),
                        manager.getAsString("passengerdobMonth"),
                        Integer.parseInt(manager.getAsString("passengerdobDay")))
                .makePayment();

//        Assert.assertEquals(BrowserActions.getPageUrl(driver),"https://ksrtc.in/oprs-web/booking/revamp/paxInfo.do");
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
