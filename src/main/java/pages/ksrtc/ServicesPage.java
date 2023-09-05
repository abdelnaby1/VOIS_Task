package pages.ksrtc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ServicesPage {
    private WebDriver driver;
    private By selectSeatBtn(int serviceNum){
        serviceNum--;
        return By.xpath("//div[contains(@class,'select-service')]//input[contains(@id,'SrvcSelectBtnForward"+serviceNum+"')]");
    }
    private By boardingPointLoc = By.cssSelector("#Forwardboarding .bordingPoint-list li:first-child");
    private By droppingPointLoc = By.cssSelector("#Forwarddroping .bordingPoint-list li:first-child");
    private By mobileNumberField = By.id("mobileNo");
    private By emailField = By.id("email");
    private By passengerNameField = By.id("passengerNameForward0");
    private By passengerGenderField = By.id("genderCodeIdForward0");
    private By passegnerAgeField = By.id("passengerAgeForward0");
    private By passengerConcessionField = By.id("concessionIdsForward0");
    private By passngerNationalityField = By.id("nationalityForward0");
    private By passengerPassportField = By.id("passportNoForward0");
    private By passengerAddress = By.id("foreignerAddressForward0");
    private By passengerDob = By.id("dobForward0");
    private By passengerDobMonthLoc = By.xpath("//*[contains(@class,'ui-datepicker-month')]");
    private By passengerDobYearLoc = By.xpath("//*[contains(@class,'ui-datepicker-year')]");
    private By passengerDobDayLoc(int day){
        return By.xpath("//td[contains(@data-handler,'selectDay')]/a[contains(text(),'"+day+"')]");
    }
    private By makePaymentBtn = By.id("PgBtn");
    private By availableSeats(int seatNumber){
        return By.xpath("(//*[contains(@class,'availSeatClassS')])["+seatNumber+"]");
    }
    public ServicesPage(WebDriver driver) {
        this.driver = driver;
    }
    public ServicesPage selectService(int serviceNum){
        new ElementActions(driver)
                .click(selectSeatBtn(serviceNum));
        return this;
    }
    public ServicesPage selectBoardAndDroppingPoints(){
        new ElementActions(driver)
                .click(boardingPointLoc)
                .click(droppingPointLoc);
        return this;
    }
    public ServicesPage selectSeat(int seatNum){
        new ElementActions(driver)
                .click(availableSeats(seatNum));
        return this;
    }
    public ServicesPage fillCustomerDetails(String mobileNum,String email){
        new ElementActions(driver)
                .type(mobileNumberField,mobileNum)
                .type(emailField,email);
        return this;
    }
    public ServicesPage fillPassengerDetails(String name,String gender,
                                             String age,String concession,
                                             String country,String passport,
                                             String address,String dobYear,
                                             String dobMonth,int dobDay){

        new ElementActions(driver)
                .type(passengerNameField,name)
                .select(passengerGenderField, ElementActions.SelectType.TEXT,gender.toUpperCase())
                .type(passegnerAgeField,age)
                .select(passengerConcessionField, ElementActions.SelectType.TEXT,concession) //
                .select(passngerNationalityField, ElementActions.SelectType.TEXT,country)
                .type(passengerPassportField,passport)
                .type(passengerAddress,address)
                .click(passengerDob)
                .select(passengerDobYearLoc, ElementActions.SelectType.TEXT,dobYear)
                .select(passengerDobMonthLoc, ElementActions.SelectType.TEXT,dobMonth)
                .click(passengerDobDayLoc(dobDay));

        return this;
    }
    public void makePayment(){
        new ElementActions(driver)
                .click(makePaymentBtn);
    }

}
