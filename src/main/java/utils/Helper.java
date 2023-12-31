package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Random;

public class Helper {
    private static int getTimeout() {
        return Integer.parseInt(System.getProperty("webdriver.wait"));
    }

    public static WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(getTimeout()));
    }

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getTimeout()));
    }
    public static int getRandomNumberBetweenTwoValues(int lowValue, int highValue) {
        return new Random().nextInt(highValue - lowValue) + lowValue;
    }


}
