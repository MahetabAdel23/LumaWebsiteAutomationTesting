package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SalesPage {
    WebDriver driver;
    public SalesPage(WebDriver driver) {
        this.driver = driver;
    }
    By SaleButtonLocator = By.xpath("//a[@href='https://magento.softwaretestingboard.com/sale.html']");
    By SaleTittleLocator = By.xpath("//span[@class='base' and text()='Sale']");
    By Buy4Price3Locator= By.xpath("//span[@class='info' and text()='4 tees for the price of 3. Right now']");
    By ItemImageLocator= By.xpath("//img[@alt='Desiree Fitness Tee']");
    By SizeXSLocator= By.xpath("//div[@id=\"option-label-size-143-item-166\"]");
    By ColorOrangeLocator= By.xpath("//div[@id='option-label-color-93-item-56']");
    By QuantityLocator= By.xpath("//input[@id='qty']");
    By AddToCartLocator= By.xpath("//button[@id=\"product-addtocart-button\"]");


    By LumaGearStealsLocator= By.xpath("//strong[text()='Luma Gear Steals']");
    By LumaWaterBottleLocator= By.xpath("//span[text()='$4 Luma water bottle']");
    By YogaKitItemLocator= By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/sprite-yoga-companion-kit.html\" and @class=\"product photo product-item-photo\"]");
    By CustomizeAndAddtoCartButtonLocator = By.xpath("//button[@id=\"bundle-slide\"]");
    By AddtocartButtonLocator= By.xpath("//button[@id=\"product-addtocart-button\"]");
    By QtyIsnotAvailableLocator= By.xpath("//div[text()='The requested qty is not available']");

    public void clickSaleButton(){
        ElementsUtils.clickOn(driver,SaleButtonLocator);
    }
    public boolean validateSaleTittleDisplayedAfterWaite(){
      return BasePage.waitForIsDisplayed(driver,SaleTittleLocator);
    }

    public void clickBuy4Price3Locator(){
        ElementsUtils.clickOn(driver,Buy4Price3Locator);
    }
    public void clickItemImage(){
        ElementsUtils.clickOn(driver,ItemImageLocator);
    }

    public void executeProductToCart(){
        ElementsUtils.clickOn(driver,SizeXSLocator);
        ElementsUtils.clickOn(driver,ColorOrangeLocator);
        ElementsUtils.clearData(driver,QuantityLocator);
        ElementsUtils.sendData(driver,QuantityLocator,"4");
        ElementsUtils.clickOn(driver,AddToCartLocator);
    }

    public boolean validateSaleBuy4Price3SuccessfullyDone(){
        ShoppingCartPage shoppingCartPage=new ShoppingCartPage(driver);
        int itemPrice=shoppingCartPage.getItemPriceValue();
        int itemSubtotalPrice=shoppingCartPage.getItemSubtotalPriceValue();
        int discountPrice=shoppingCartPage.getDiscountPriceValue();
        System.out.println("discountPrice="+discountPrice);
        System.out.println("total price after discount is "+(itemSubtotalPrice-itemPrice));
        if(shoppingCartPage.validateTotalPriceCalulatedCorrectly()==true &&
                discountPrice==itemPrice)
        {
            System.out.println("Discount price is correct"+" "+discountPrice);
                return true;

        }
        else {
            return false;
        }

    }
    public void executeLumaGearSteals(){
        ElementsUtils.clickOn(driver,LumaGearStealsLocator);
        ElementsUtils.clickOn(driver,LumaWaterBottleLocator);
        ElementsUtils.clickOn(driver,YogaKitItemLocator);
        ElementsUtils.clickOn(driver, CustomizeAndAddtoCartButtonLocator);
        if(BasePage.waitForIsDisplayed(driver,AddtocartButtonLocator)==true)
        {
            ElementsUtils.clickOn(driver,AddtocartButtonLocator);
        }
        else
        {
            System.out.println("CustomizeAndAddtoCartButtonLocator not displayed");
        }
    }
    public boolean validateQtyIsnotAvailableDisplayAfterWait(){
        return BasePage.waitForIsDisplayed(driver,QtyIsnotAvailableLocator);
    }

}
