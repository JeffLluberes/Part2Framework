package TestCases;

import Pages.*;
import TestBase.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Checkout extends TestBase {
    public Checkout() throws Exception {
        super();

    }
    public WebDriver driver;
    public SearchProductPage searchProductPage;
    public CartPage cartPage;
    public LoginPage loginPage;
    public Homepage homepage;
    public CheckoutPage checkoutPage;

    @BeforeMethod
    public void startCheckout(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        homepage = new Homepage(driver);
        homepage.clickMyAccountDropDown();
        homepage.clickloginLink();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(prop.getProperty("validEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();
    }
    @Test(priority = 1)
    public void validateProductInCheckoutPage(){
        searchProductPage = new SearchProductPage(driver);
        searchProductPage.enterToSearchBoxTextField(dataProp.getProperty("product2"));
        searchProductPage.clickOnSearchButton();
        searchProductPage.clickOnAddToCart();
        searchProductPage.addProductToCart();
        cartPage = new CartPage(driver);
        cartPage.clickOnViewItemLink();
        cartPage.clickOnViewCartLink();
        Assert.assertTrue(cartPage.productInCartDisplayed());
    }
    @Test(priority = 2)
    public void completeCheckOut() throws InterruptedException {
        searchProductPage =new SearchProductPage(driver);
        searchProductPage.enterToSearchBoxTextField(dataProp.getProperty("product2"));
        searchProductPage.clickOnSearchButton();
        searchProductPage.clickOnAddToCart();
        searchProductPage.addProductToCart();
        cartPage = new CartPage(driver);
        cartPage.clickOnViewItemLink();
        cartPage.clickOnViewCartLink();
        cartPage.clickOnCheckOutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickOnNewAddressRadioButton();
        checkoutPage.firstNameFieldInBilling(dataProp.getProperty("firstNameBill"));
        checkoutPage.lastNameFieldBilling(dataProp.getProperty("lastNameBill"));
        checkoutPage.addressFieldBilling(dataProp.getProperty("address1"));
        checkoutPage.cityFieldBilling(dataProp.getProperty("cityBill"));
        checkoutPage.postalCodeBilling(dataProp.getProperty("postalCode"));
        checkoutPage.selectCountry();
        checkoutPage.UnitedStatesViaDropdown(dataProp.getProperty("country1"));
        checkoutPage.selectRegion();
        checkoutPage.newYorkViaDropdown(dataProp.getProperty("region1"));
        checkoutPage.clickOnContinueButtonBillingDetails();
        checkoutPage.clickOnContinueButtonForDeliveryDetails();
        checkoutPage.clickOnContinueButtonForDeliveryMethod();
        checkoutPage.acceptTermsAndConditionsInPaymentMethod();
        Thread.sleep(2000);
        checkoutPage.clickOnContinueButtonInPaymentMethod();
        checkoutPage.clickOnConfirmOrderButton();
        Assert.assertTrue(checkoutPage.confirmOrderWasPlaced());
    }
}
