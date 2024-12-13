package ideaSoftHomeAssessment.page;

import ideaSoftHomeAssessment.base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseStep {

    public static final By SEARCHBOX_INPUT = By.xpath("//input[@name='q']");
    public static final By PRODUCT_LIST_DIV = By.xpath("(//div[@class='row'])[4]");
    public static final By PRODUCT_CARD = By.xpath("//div[@class='showcase-image-container']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSaerchBoxAndSearchProduct(String text) {
        clickElement(SEARCHBOX_INPUT);
        sendText(SEARCHBOX_INPUT, text);
        pressEnter(SEARCHBOX_INPUT);
    }

    public void goProductDetail() {

        isElementVisibility(PRODUCT_LIST_DIV);
        isElementVisibility(PRODUCT_CARD);
        clickElement(PRODUCT_CARD);
    }

}