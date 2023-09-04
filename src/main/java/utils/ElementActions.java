package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

public class ElementActions {
    private WebDriver driver;
    public enum SelectType {
        TEXT, VALUE;
    }
    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public ElementActions waitForVisibility(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return this;
    }

    public ElementActions waitForInvisibility(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        return this;
    }

    public Boolean isDisplayed(By elementLocator){
        try{
            return driver.findElement(elementLocator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public ElementActions click(By elementLocator) {
        click(driver, elementLocator);
        return this;
    }
    private void click(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {

            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(elementLocator));
            } catch (Exception rootCauseException) {
                rootCauseException.initCause(exception);
                fail("Couldn't click on the element:" + elementLocator, rootCauseException);
            }
        }
    }
    public ElementActions type(By elementLocator, String text) {
        type(driver, elementLocator, text, true);
        return this;
    }
    public ElementActions type(By elementLocator, String text, boolean clearBeforeTyping) {
        type(driver, elementLocator, text, clearBeforeTyping);
        return this;
    }
    public void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
                driver.findElement(elementLocator).clear();
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                            driver.findElement(elementLocator));
                }
            } else {
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(elementLocator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(elementLocator));
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
                "The data is not inserted successfully to the field, the inserted data should be: [" + text
                        + "]; But the current field value is: ["
                        + driver.findElement(elementLocator).getAttribute("value") + "]");
    }


    public  void select(WebDriver driver, By elementLocator, SelectType selectType, String option) {
        try {
            Select select = new Select(driver.findElement(elementLocator));
            assertFalse(select.isMultiple());
            switch (selectType) {
                case TEXT -> select.selectByVisibleText(option);
                case VALUE -> select.selectByValue(option);
                default -> fail("Unexpected value: " + selectType);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public ElementActions select(By elementLocator, SelectType selectType, String option) {
        select(driver, elementLocator, selectType, option);
        return this;
    }

    public void mouseHover(WebDriver driver, By elementLocator) {
        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    public ElementActions mouseHover(By elementLocator) {
        mouseHover(driver, elementLocator);
        return this;
    }

    public void doubleClick(WebDriver driver, By elementLocator) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public ElementActions doubleClick(By elementLocator) {
        doubleClick(driver, elementLocator);
        return this;
    }

    public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
        try {
            driver.findElement(elementLocator).sendKeys(key);
        } catch (Exception e) {
        }
    }

    public ElementActions clickKeyboardKey(By elementLocator, Keys key) {
        clickKeyboardKey(driver, elementLocator, key);
        return this;
    }
    public String getText( By elementLocator) {
        return getText(driver,elementLocator);
    }

    private  String getText(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            String text = driver.findElement(elementLocator).getText();
            return text;
        } catch (Exception e) {
        }
        return null;
    }

    public static String getAttributeValue(WebDriver driver, By elementLocator, String attributeName) {
        try {
            String attributeValue = driver.findElement(elementLocator).getAttribute(attributeName);
            return attributeValue;
        } catch (Exception e) {
        }
        return null;
    }




}
