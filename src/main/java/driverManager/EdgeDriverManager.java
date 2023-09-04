package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager extends DriverManager{
    @Override
    protected WebDriver createDriver() {
        if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {

                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getEdgeOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

        } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
            return new EdgeDriver(getEdgeOptions());

        }
        // else return chrome local
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
