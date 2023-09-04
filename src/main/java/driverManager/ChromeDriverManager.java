package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager extends DriverManager {

        @Override
        public WebDriver createDriver() {
            if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {
                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getChromeOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions());
            }
            // else return chrome local
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        }
        private ChromeOptions getChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            return options;
        }
//    private ChromeOptions get() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--window-size=1920,1080");
//        return options;
//    }
}
