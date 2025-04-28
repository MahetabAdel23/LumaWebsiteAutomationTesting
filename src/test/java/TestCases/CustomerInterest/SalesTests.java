package TestCases.CustomerInterest;

import TestCases.AccountTests.AddressOrder;
import objects.BrowserOption;
import objects.ItemWomanCategoryPage;
import objects.SalesPage;
import objects.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesTests {
//2tc
    WebDriver driver;
    Logger logger;

    SoftAssert softAssert=new SoftAssert();
    @BeforeMethod(alwaysRun = true)//By applying always run set to true.,We ensure that setup and teardown methods are always executed, providing the stable environment for,all tests and preventing null pointer exception here because they weren't executed.
    @Parameters("browser")
    public void setUp (@Optional("chrome") String browser) {
        logger = Logger.getLogger(AddressOrder.class.getName());
        logger.setLevel(Level.INFO);// we will get a clear view of the test flow and important events that occur during the test execution.

        driver = BrowserOption.BrowserFactory(browser);
        driver.get("https://magento.softwaretestingboard.com/");
        SalesPage salesPage=new SalesPage(driver);
        salesPage.clickSaleButton();
        softAssert.assertTrue(salesPage.validateSaleTittleDisplayedAfterWaite(),"Sale title not displayed");
        softAssert.assertAll();

    }

    @Test(groups = {"RegressionTests","SmokeTests"})
    public void SalesTests_Buy4Price3Test(){
        SoftAssert softAssert=new SoftAssert();
        SalesPage salesPage=new SalesPage(driver);
        salesPage.clickBuy4Price3Locator();
        salesPage.clickItemImage();
        salesPage.executeProductToCart();
        ItemWomanCategoryPage itemWomanCategoryPage = new ItemWomanCategoryPage(driver);
        softAssert.assertTrue(itemWomanCategoryPage.AddToCartSuccessfulMessageDisplayed(),"Item is not added to cart");
        itemWomanCategoryPage.navigateIntoCartPage();
        ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.waitUntilDiscountDisplayed(),"discountTitle is not displayed");
        int discount=shoppingCartPage.getDiscountPriceValue();
        logger.info("DiscountPriceValue= "+discount);
        softAssert.assertTrue(salesPage.validateSaleBuy4Price3SuccessfullyDone());
        softAssert.assertAll();
    }

     @Test(groups = {"RegressionTests","NegativeTests"})
    public void SalesTests_LumaGearStoreTest()
    {
        SoftAssert softAssert=new SoftAssert();
        SalesPage salesPage=new SalesPage(driver);
        salesPage.executeLumaGearSteals();
        softAssert.assertTrue(salesPage.validateQtyIsnotAvailableDisplayAfterWait());
        softAssert.assertAll();
    }

    @AfterTest
    public void teardown()
    {
        logger.info("closing browser");
        driver.quit();
    }
}
