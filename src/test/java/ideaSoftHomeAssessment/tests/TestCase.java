package ideaSoftHomeAssessment.tests;

import dataprovider.ExcelDataProvider;
import ideaSoftHomeAssessment.base.BaseTest;
import ideaSoftHomeAssessment.page.HomePage;
import ideaSoftHomeAssessment.page.ProductDetail;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase extends BaseTest {

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        productDetail = new ProductDetail(driver);
    }

    @Test(dataProvider = "sendText", dataProviderClass = ExcelDataProvider.class)
    public void checkingTheDetailsOfJobPostingsOnInsider(String text) {
        homePage.clickSaerchBoxAndSearchProduct(text);
        homePage.goProductDetail();
        productDetail.productQuantityEntered();
        productDetail.addBasketProductAndCheckSuccessfullyMessage();
        productDetail.goBasketAndCheckProductQty();
    }
}