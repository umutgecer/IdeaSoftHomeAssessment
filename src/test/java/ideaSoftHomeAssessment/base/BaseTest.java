package ideaSoftHomeAssessment.base;

import ideaSoftHomeAssessment.page.ProductDetail;
import ideaSoftHomeAssessment.page.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected HomePage homePage;
    protected ProductDetail productDetail;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://qatestcase.myideasoft.com/ ");

        logger.info("...STARTING TEST...");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("...TEST FINISHED...");
        }
    }
}