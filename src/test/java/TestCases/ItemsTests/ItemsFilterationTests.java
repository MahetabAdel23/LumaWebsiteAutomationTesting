package TestCases.ItemsTests;


import objects.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsFilterationTests {
//total5
    WebDriver driver;
    Logger logger;

    SoftAssert softAssert=new SoftAssert();
    @BeforeMethod(alwaysRun = true)//By applying always run set to true.,We ensure that setup and teardown methods are always executed, providing the stable environment for,all tests and preventing null pointer exception here because they weren't executed.
    @Parameters("browser")
    public void setUp (@Optional("chrome") String browser)
    {
        logger= Logger.getLogger(ItemsFilterationTests.class.getName());
        logger.setLevel(Level.INFO);// we will get a clear view of the test flow and important events that occur during the test execution.

        driver= BrowserOption.BrowserFactory(browser);
        driver.get("https://magento.softwaretestingboard.com/");
        HomePage home = new HomePage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.NavigateWomenCategoryOptions();
    }
    //TC No1
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void ItemsFilterationTests_sizeXsFilterTest() {
        logger.info("Filter Item with  size xs");
        SoftAssert softAssert = new SoftAssert();
        ItemWomanCategoryPage jacketWomanCategoryPage=new ItemWomanCategoryPage(driver);
        jacketWomanCategoryPage.chooseSizeXs();
        softAssert.assertTrue(jacketWomanCategoryPage.isSizeXsCheckDisplayed());
        softAssert.assertAll();
    }


    //TC No2
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void ItemsFilterationTests_multiFiltersAppliedCheckTest() {

        logger.info("Filter Item with more than 1 filters, color and size");
        SoftAssert softAssert = new SoftAssert();
        ItemWomanCategoryPage jacketWomanCategoryPage=new ItemWomanCategoryPage(driver);
        logger.info("Filter Jacket color To ColorBlack");
         jacketWomanCategoryPage.chooseColorBlack();
        softAssert.assertTrue(jacketWomanCategoryPage.isColorBlackCheckDisplayed(),"NOT found");
        logger.info("Filter Jacket size To XS");
        jacketWomanCategoryPage.chooseSizeXs();
        softAssert.assertTrue(jacketWomanCategoryPage.isSizeXsCheckDisplayed(),"NOT found");
        softAssert.assertAll();
    }


    //TC No3
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void ItemsFilterationTests_sortingJacketPriceDecTest() {
        SoftAssert softAssert = new SoftAssert();
        ItemWomanCategoryPage jacketWomanCategoryPage=new ItemWomanCategoryPage(driver);
        logger.info("before sorting");
        jacketWomanCategoryPage.printPrice();
        jacketWomanCategoryPage.SortByPriceValue();
        softAssert.assertTrue(jacketWomanCategoryPage.isDescenditingButtonDisplayed(),"element is not displayed");
        logger.info("After sorting");
        jacketWomanCategoryPage.printSortedPrice();
        softAssert.assertAll();
    }

    //n4
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void ItemsFilterationTests_SearchingOnExistItemTest()
    {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.executeSearchText("Jacket");
        ProductsPage productsPage=new ProductsPage(driver);
        //softAssert.assertTrue(productsPage.waitForJacketSearchDisplayed(),"not the same");
        softAssert.assertTrue(productsPage.waitForJacketSearchDisplayed(),"Sort by is visible ");
        softAssert.assertAll();

    }
    //n5
    @Test (groups = {"RegressionTests","NegativeTests"})
    public void ItemsFilterationTests_SearchingOnNonExistItemTest()
    {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.executeSearchText("dress");
        ProductsPage productsPage=new ProductsPage(driver);
        softAssert.assertTrue(productsPage.SortElementInvisibilityAfterWait(),"Sort by is visible ");
        softAssert.assertAll();
    }



    @AfterMethod()
    public void teardown()
    {
        logger.info("closing browser");
        driver.quit();
    }


}
