package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import driverManager.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReport {
    private static ExtentReports report;
    private static ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public static void initReports() {
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports//ExtentReport.html");
        spark.config().setReportName(System.getProperty("report.name"));
        spark.config().setDocumentTitle(System.getProperty("document.title"));

        report = new ExtentReports();
        report.attachReporter(spark);

        report.setSystemInfo("Java Version",System.getProperty("java.version"));
        report.setSystemInfo("Java Home",System.getProperty("java.home"));
        report.setSystemInfo("OS Name",System.getProperty("os.name"));
        report.setSystemInfo("OS Version",System.getProperty("os.version"));
        report.setSystemInfo("OS Architecture",System.getProperty("os.arch"));
        report.setSystemInfo("User",System.getProperty("user.name"));
        report.setSystemInfo("Tester Name",System.getProperty("tester.name"));
        report.setSystemInfo("Tester Email",System.getProperty("tester.email"));
    }
    public static  void flushReports() {
        report.flush();
    }
    public static synchronized void createTest(String testcaseName) {
        test = report.createTest(testcaseName);
        extentTest.set(test);
    }
    public static synchronized void removeTest(String testcaseName) {
        report.removeTest(testcaseName);
        extentTest.remove();
    }

    public static synchronized void info(String message) {
        if (test != null) {
            extentTest.get().info(message);
        }
    }

    public static synchronized void info(Markup m) {
        extentTest.get().info(m);
    }

    public static synchronized void pass(String message) {
        extentTest.get().pass(message);
    }

    public static synchronized void pass(Markup m) {
        extentTest.get().pass(m);
    }
    public static synchronized void pass(Media m) {
        extentTest.get().pass(m);
    }

    public static synchronized void fail(String message) {
        extentTest.get().fail(message);
    }

    public static synchronized void fail(Markup m) {
        extentTest.get().fail(m);
    }

    public static synchronized void fail(Throwable t) {
        extentTest.get().fail(t);
    }

    public static synchronized void fail(Media media) {
        extentTest.get().fail(media);
    }

    public static synchronized void skip(String message) {
        extentTest.get().skip(message);
    }

    public static synchronized void skip(Markup m) {
        extentTest.get().skip(m);
    }

    public static synchronized void skip(Throwable t) {
        extentTest.get().skip(t);
    }
    public static synchronized Media attachScreenshotToExtentReport() {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(
                ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64), "Screenshot Of the Page").build();
    }
}
