package driverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

public abstract class DriverManager {


    static final String executionTypeProperty = System.getProperty("execution.type");
    static final String host = System.getProperty("remote.execution.host");
    static final String port = System.getProperty("remote.execution.port");


    public enum ExecutionType {
        LOCAL("Local"), REMOTE("Remote"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

        private final String value;

        ExecutionType(String type) {
            this.value = type;
        }

        private String getValue() {
            return value;
        }
    }
    protected abstract WebDriver createDriver();

}
