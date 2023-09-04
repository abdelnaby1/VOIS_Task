package Listener;

import Reporting.ExtentReport;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private static final int   maxTry = Integer.parseInt(System.getProperty("retry.maxTry"));
    private static final Boolean retry = Boolean.valueOf(System.getProperty("retry"));
    private int count  = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retry){
            if (!iTestResult.isSuccess ()) {
                if (this.count < maxTry) {
                    ExtentReport.info ("Retrying test " + iTestResult.getName () + " with status " + getResultStatusName (
                            iTestResult.getStatus ()) + " for the " + (this.count + 1) + " time(s).");
                    this.count++;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    public String getResultStatusName (final int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }
}
