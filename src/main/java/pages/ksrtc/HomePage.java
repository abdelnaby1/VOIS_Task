package pages.ksrtc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class HomePage {
    private WebDriver driver;
    private String url = "https://ksrtc.in/oprs-web/guest/home.do?h=1";
    private By routeLoc(String from, String to){
        //Chikkamagaluru - Bengaluru
//        return By.xpath("//a[contains(text(),'"+from+" - "+to+"')]");
        return By.xpath("//a[text()[normalize-space()='"+from+" - "+to+"']]");
    }
    private By dayLoc(int days){
        days++;
        return By.xpath("(//td[contains(@data-handler,'selectDay')]/a)["+days+"]");
    }
    private By searchBtn = By.xpath("//button[text()[normalize-space()='Search for Bus']]");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage gotoUrl(){
        driver.get(url);
        return this;
    }
    public HomePage chooseRoute(String from, String to){
        new ElementActions(driver)
                .click(routeLoc(from,to));
        return this;
    }
    public HomePage chooseArrivalDate(int days){
        new ElementActions(driver)
                .click(dayLoc(days));
        return this;
    }
    public ServicesPage search(){
        new ElementActions(driver)
                .click(searchBtn);
        return new ServicesPage(driver);
    }
}
