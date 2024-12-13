package ideaSoftHomeAssessment.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BaseStep {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseStep.class);
    private static WebDriverWait wait;

    public BaseStep(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean isElementVisibility(By by) {
        try {
            waitForElement(by);
            logger.info("Element is visible: " + by);
            return true;
        } catch (Exception e) {
            logger.warn("Element Is Not Visible: " + by, e);
            return false;
        }
    }

    public void checkPageUrl(String url) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, url, "Page Url Did Not Match!");
        logger.info("Page URL Verified: " + currentUrl);
    }

    public boolean isElementClickable(By by, int count, int maxCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxCount));
        while (count < maxCount) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                logger.info("Element clicked: " + by);
                return true;
            } catch (Exception e) {
                count++;
                logger.warn("Attempt " + count + " failed to click element: " + by, e);
            }
        }
        logger.error("Element is not clickable after " + maxCount + " attempts: " + by);
        return false;
    }

    public void clickElement(By by) {
        scrollToElement(by);
        int count = 0;
        int maxCount = 15;
        boolean isClicked = isElementClickable(by, count, maxCount);
        if (!isClicked) {
            throw new RuntimeException("Element is not clickable after " + maxCount + " attempts: " + by);
        }
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void sendText(By by, String text) {
        WebElement element = waitForElement(by);
        element.clear();
        element.sendKeys(text);
        logger.info("Text sent to input: " + text);
    }

    public void pressEnter(By by) {
        WebElement element = waitForElement(by);
        element.sendKeys(Keys.ENTER);
        logger.info("Enter key pressed on element: " + by);
    }

    public void checkElementText(By by, String text) {
        scrollToElement(by);
        WebElement element = waitForElement(by);
        String actualText = element.getText();
        Assert.assertEquals(actualText, text, "Element text did not match! Found: " + actualText);
        logger.info("Element text verified: " + actualText);
    }

    public void checkBasketQtyValue(By by, String qtyValue) {
        WebElement element = waitForElement(by);
        String actualValue = element.getAttribute("value");
        Assert.assertEquals(actualValue, qtyValue, "Input value did not match!");
        logger.info("Input value verified. Value: " + qtyValue + ", Actual: " + actualValue);
    }
}