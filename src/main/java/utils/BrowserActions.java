package utils;

import driverManager.DriverFactory;
import driverManager.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class BrowserActions {
    static WebDriver driver;

    public enum ConfirmAlertType {
        ACCEPT, DISMISS;
    }

    public enum CookieBuilderType {
        ADD, DELETE;
    }

    public static void navigateToUrl(WebDriver driver, String url) {
        try {
            driver.get(url);
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();

            } catch (WebDriverException rootCauseException) {
                System.out.println(rootCauseException.getMessage());

//                Logger.logMessage(rootCauseException.getMessage());
            } finally {
                driver = null;
            }
        } else {
            System.out.println("already closed suc");
//            Logger.logMessage("Windows are already closed and the driver object is null.");
        }
    }

    public static void maximizeWindow(WebDriver driver) {
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setWindowResolution(WebDriver driver) {
        String width = System.getProperty("width");
        String height = System.getProperty("height");
        try {
            Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
            driver.manage().window().setSize(dimension);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void confirmAlert(WebDriver driver, ConfirmAlertType confirmAlerType) {
        Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        switch (confirmAlerType) {
            case ACCEPT:
                alert.accept();
                break;
            case DISMISS:
                Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
                break;
        }
    }

    public static void cookieBuilder(WebDriver driver, CookieBuilderType cookieBuilderType, String name, String value,
                                     String domain) {
        Cookie cookie = new Cookie.Builder(name, value).domain(domain).build();

        switch (cookieBuilderType) {
            case ADD:
                driver.manage().addCookie(cookie);
                break;
            case DELETE:
                driver.manage().deleteCookie(cookie);
                break;
        }
    }


    public static String getPageTitle(WebDriver driver) {
        String title = "";
        try {
            title = driver.getTitle();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return title;
    }
    public static String getPageUrl(WebDriver driver) {
        String url = "";
        try {
            url = driver.getCurrentUrl();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return url;
    }
}
