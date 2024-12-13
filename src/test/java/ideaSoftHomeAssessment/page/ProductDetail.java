package ideaSoftHomeAssessment.page;

import ideaSoftHomeAssessment.base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetail extends BaseStep {

    public static final By PRODUCT_QTY_INPUT = By.id("qty-input");
    public static final By BASKET_ADD_BUTTON = By.cssSelector(".add-to-cart-button");
    public static final By SUCCESSFULL_MESSAGE = By.cssSelector(".shopping-information-cart-inside");
    public static final By BASKET_BUTTON = By.cssSelector("a[title='Sepet']");
    public static final By PRODUCT_QTY_VALUE = By.xpath("//input[@data-selector='qty']");

    public static final String PRODUCT_QTY_TEXT = "5";
    public static final String SUCCESSFULLY_MESSAGE = "SEPETİNİZE EKLENMİŞTİR";
    public static final String BASKET_PAGE_URL = "https://qatestcase.myideasoft.com/sepet";

    public ProductDetail(WebDriver driver) {
        super(driver);
    }

    public void productQuantityEntered() {
        sendText(PRODUCT_QTY_INPUT, PRODUCT_QTY_TEXT);
    }

    public void addBasketProductAndCheckSuccessfullyMessage() {
        clickElement(BASKET_ADD_BUTTON);
        isElementVisibility(SUCCESSFULL_MESSAGE);
        checkElementText(SUCCESSFULL_MESSAGE, SUCCESSFULLY_MESSAGE);
    }

    public void goBasketAndCheckProductQty() {
        clickElement(BASKET_BUTTON);
        checkPageUrl(BASKET_PAGE_URL);
        checkBasketQtyValue(PRODUCT_QTY_VALUE, PRODUCT_QTY_TEXT);

    }
}