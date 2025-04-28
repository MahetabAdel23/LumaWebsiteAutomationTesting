package TestCases.ItemsTests;

import objects.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CartTests {
//tc7
    WebDriver driver;
    Logger logger;

    SoftAssert softAssert=new SoftAssert();
    @BeforeMethod(alwaysRun = true)//By applying always run set to true.,We ensure that setup and teardown methods are always executed, providing the stable environment for,all tests and preventing null pointer exception here because they weren't executed.
    @Parameters("browser")
    public void setUp (@Optional("chrome") String browser)
    {
        logger= Logger.getLogger(CartTests.class.getName());
        logger.setLevel(Level.INFO);// we will get a clear view of the test flow and important events that occur during the test execution.

        driver= BrowserOption.BrowserFactory(browser);
        driver.get("https://magento.softwaretestingboard.com/");
        HomePage homePage = new HomePage(driver);
        homePage.NavigateWomenCategoryOptions();
        ItemWomanCategoryPage jacketWomanCategoryPage=new ItemWomanCategoryPage(driver);
        jacketWomanCategoryPage.addItemToCart();
        softAssert.assertTrue(jacketWomanCategoryPage.AddToCartSuccessfulMessageDisplayed(),"Item is not added to cart");
        jacketWomanCategoryPage.navigateIntoCartPage();
    }

    //TC No1
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void CartTests_TotalPriceCalulatedCorrectlyTest()
    {//chrome faster
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
        softAssert.assertTrue(shoppingCartPage.validateTotalPriceCalulatedCorrectly());
        softAssert.assertAll();
    }


    //TC No2
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void CartTests_EditQtyCartItemTest()
    {
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
        shoppingCartPage.clickEditItemButton();
        softAssert.assertTrue(shoppingCartPage.isUpdateCartButtonDisplayed(),"Page title is not displayed");
        shoppingCartPage.UpdateQtyItem("2");
        shoppingCartPage.confirmUpdateInItemCart();
        int qtyValue = shoppingCartPage.ReadQtyElementAfterUpdated();
        softAssert.assertEquals(qtyValue,2,"update failed");
        shoppingCartPage.confirmUpdateInItemCart();
        softAssert.assertAll();

    }


    //TC No3
    @Test(groups = {"RegressionTests","SmokeTests"})
    public void CartTests_DeleteItemInCartTest()
    {
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartPage shoppingCartPage=new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
        softAssert.assertTrue(shoppingCartPage.ClearItemInCartButton(),"item is not deleted");
        softAssert.assertAll();
    }


        //TC No4
        @Test(groups = {"RegressionTests","NegativeTests"})
        public void CartTests_InValidDiscountCodeInCartTest()
        {
            SoftAssert softAssert = new SoftAssert();
            ShoppingCartPage shoppingCartPage=new ShoppingCartPage(driver);
            softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
            shoppingCartPage.executeDiscount("50Off");
            softAssert.assertTrue(shoppingCartPage.IsInvalidDiscountCodeMessageDisplayed(),"Discount code is valid");
            softAssert.assertAll();

        }

          //TC No5
          @Test(groups = {"RegressionTests","SmokeTests"})
          public void CartTests_UpdateQuantatyCardItemTest()
          {
              SoftAssert softAssert = new SoftAssert();
              ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
              softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
              logger.info("Cart Quantaty 4");
              shoppingCartPage.ConfirmUpdateQty("4");
              ItemWomanCategoryPage jacketWomanCategoryPage=new ItemWomanCategoryPage(driver);
              softAssert.assertTrue(jacketWomanCategoryPage.DiscountDisplay());
              logger.info("Discount value is: " + jacketWomanCategoryPage.ReadDiscountValue());
              softAssert.assertTrue(jacketWomanCategoryPage.UpdateSummaryQtySubtotalValueDisplay());
              int SubtotalCalculatedPrice=shoppingCartPage.getQtyElement()*shoppingCartPage.getItemPriceValue();
              logger.info("SubtotalCalculatedPrice is: " + SubtotalCalculatedPrice);
              softAssert.assertEquals(jacketWomanCategoryPage.ReadUpdateSummaryQtySubtotalValue(),SubtotalCalculatedPrice,"not equal");
              softAssert.assertAll();
          }


          //TC No6
          @Test(groups = {"RegressionTests","NegativeTests"})//bug
          public void CartTests_CheckoutCardItemTableRateRadioTest()throws InterruptedException{
              SoftAssert softAssert = new SoftAssert();
              ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
              softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
              shoppingCartPage.clickProceedToCheckoutButton();
              softAssert.assertTrue(shoppingCartPage.ShippingAddressTitleDisplayed(),"not in the Shipping page");
              AddressPage addressPage=new AddressPage(driver);
              softAssert.assertTrue(addressPage.WaitEmailFieldDisplayed());
              addressPage.clickShippingMethodsTableRateRadio();
              addressPage.executShippingDataWithoutSignIn(2);
              softAssert.assertTrue(shoppingCartPage.ShippingAddressTitleDisplayed(),"not in the Shipping page");

              softAssert.assertAll();

          }
//no7
@Test(groups = {"RegressionTests","SmokeTests"})
    public void CartTests_CheckoutShippingValueAddedTest()throws InterruptedException{
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(),"Page title is not displayed");
        shoppingCartPage.clickProceedToCheckoutButton();
        softAssert.assertTrue(shoppingCartPage.ShippingAddressTitleDisplayed(),"not in the Shipping page");
        AddressPage addressPage=new AddressPage(driver);
        softAssert.assertTrue(addressPage.WaitEmailFieldDisplayed());
        addressPage.clickShippingMethodsFixedRadio();
        addressPage.executShippingDataWithoutSignIn(1);
        softAssert.assertTrue(shoppingCartPage.ShippingAddressTitleDisplayed(),"not in the Shipping page");
        softAssert.assertTrue(driver.getPageSource().contains("Payment Method"),"Payment Method is not displayed");
        softAssert.assertTrue(shoppingCartPage.validateShippingValueAdded());
        softAssert.assertTrue(shoppingCartPage.validateOrderSummaryTittleDispalyedAfterWait(),"OrderSummaryTittle not displayed");
        softAssert.assertEquals(shoppingCartPage.ReadShippingValue()+shoppingCartPage.ReadUpdateSummaryQtySubtotalValue(),shoppingCartPage.ReadTotalOrderPriceValue());
        Thread.sleep(2000); // Pauses the execution for 2 seconds

       shoppingCartPage.clickPlaceOrderButton();
        softAssert.assertTrue(shoppingCartPage.validateOrderPlacedSuccesfuly(),"order did not placedSucessfuly");
        softAssert.assertAll();

    }

    //TC No8
    @Test(groups = {"RegressionTests","NegativeTests"})
    public void CartTests_ExciedQuantityCardItemTest() {
        SoftAssert softAssert = new SoftAssert();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        softAssert.assertTrue(shoppingCartPage.isPageTitleDisplayed(), "Page title is not displayed");
        logger.info("Cart Quantaty 10000");
        shoppingCartPage.ConfirmUpdateQty("10000");
        shoppingCartPage.clickOnOk();
        softAssert.assertAll();

    }

@AfterMethod()
public void teardown()
{
    logger.info("closing browser");
    driver.quit();
}

}
