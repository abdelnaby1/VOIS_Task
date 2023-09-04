package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverManager extends DriverManager{
    @Override
    protected WebDriver createDriver() {
        if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {

                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getFirefoxOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

        } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
            return new FirefoxDriver(getFirefoxOptions());

        }
        // else return chrome local
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
