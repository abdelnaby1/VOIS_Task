package Listener;

import Reporting.ExtentReport;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.PropertiesReader;


public class TestngListener implements ISuiteListener, ITestListener, IInvokedMethodListener {
    // ISuiteListener

    @Override
    public void onStart(ISuite suite) {
        PropertiesReader.loadProperties();
        System.out.println("\n" + "**********************************************");
        System.out.println("Starting Test Suite; By " +System.getProperty("user.name")+"! *");
        System.out.println("**********************************************" + "\n");
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("\n" + "**********************************************");
        System.out.println("Finished Test Suite; By " +System.getProperty("user.name")+"! *");
        System.out.println("**********************************************" + "\n");
        ExtentReport.flushReports();
    }


    // ITestListener
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n" + "**************************************************** " + "Test: ["
                + context.getName() + "] Started" + " ****************************************************" + "\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n" + "**************************************************** " + "Test: ["
                + context.getName() + "] Finished" + " ****************************************************" + "\n");
    }
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver != null) {
            ExtentReport.pass(ExtentReport.attachScreenshotToExtentReport());
        }
        System.out.println("result will be " +result.getMethod().getMethodName());
        ExtentReport.pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Passed!", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver != null) {
            ExtentReport.fail(ExtentReport.attachScreenshotToExtentReport());
        }
        ExtentReport.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Failed!", ExtentColor.RED));
        if (result.getThrowable() != null) {
            ExtentReport.fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver != null) {
            ExtentReport.fail(ExtentReport.attachScreenshotToExtentReport());
        }
        ExtentReport.skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Skipped!", ExtentColor.YELLOW));
        if (result.getThrowable() != null) {
            ExtentReport.skip(result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void  onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    // IInvokedMethodListener


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        ITestNGMethod testMethod = method.getTestMethod();
        if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {
            ExtentReport.createTest(testMethod.getDescription());
        } else {
            ExtentReport.createTest(testResult.getName());
        }
        System.out.println("\n" + "============================================================================================");
        if (method.isConfigurationMethod()) {
            System.out.println("Starting Configuration Method [" + testResult.getName() + "]");
            if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {
                ExtentReport.removeTest(testMethod.getDescription());
            } else {
                ExtentReport.removeTest(testResult.getName());
            }
        } else {
            System.out.println("Starting Test Case: [" + testResult.getName() + "]");
        }
        System.out.println("============================================================================================" + "\n");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // prevent the ERROR io.qameta.allure.AllureLifecycle - Could not add attachment: no test is running
        ITestContext context = testResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (ITestResult.FAILURE == testResult.getStatus() && driver != null) {
//            Logger.attachScreenshotToAllureReport(driver);
        }

        System.out.println("\n" + "===========================================================================================");
        if (method.isConfigurationMethod()) {
            System.out.println("Finished Configuration Method  [" + testResult.getName() + "]");
        } else {
            System.out.println("Finished Test Case: [" + testResult.getName() + "]");
        }
        System.out.println("===========================================================================================" + "\n");
    }

//    @Override
//    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//        IInvokedMethodListener.super.beforeInvocation(method, testResult, context);
//    }
//
//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//        IInvokedMethodListener.super.afterInvocation(method, testResult, context);
//    }
}
