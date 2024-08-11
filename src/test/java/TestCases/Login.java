package TestCases;

import Pages.Homepage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import TestBase.TestBase;
import net.bytebuddy.build.Plugin;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends TestBase {
    public Login() throws Exception {
        super();
    }
    public WebDriver driver;
    public Homepage homepage;
    public LoginPage loginPage;
    public MyAccountPage myAccountPage;



    @BeforeMethod
    public void loginSetup(){

        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        homepage = new Homepage(driver);
        homepage.clickMyAccountDropDown();
        homepage.clickloginLink();
    }
    @Test(priority = 1)
    public void verifyLoginWithValidCredentials(){
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(prop.getProperty("validEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.editAccountInfoLinkIsDisplayed();

    }

    @Test(priority = 2)
    public void verifyForgotPasswordLink(){
    loginPage = new LoginPage(driver);
    loginPage.forgotPassword();
    loginPage.enterEmailToForgotPasswordFieldBox(prop.getProperty("validEmail"));
    loginPage.continueButtonForgotEmailPage();
        Assert.assertTrue(loginPage.confirmationLinkSentToEmailIsDisplayed());

    }
    @Test(priority = 3)
    public void loginWithInvalidEmailValidPassword(){
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(dataProp.getProperty("invalidEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.retrieveWarningEmailPasswordMismatchText());

    }
    @Test(priority = 4)
    public void loginWithValidEmailInvalidPassword() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(prop.getProperty("validEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.retrieveWarningEmailPasswordMismatchText());
    }
    @Test(priority = 5)
    public void loginWithInvalidEmailInvalidPassword(){
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(dataProp.getProperty("invalidEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.retrieveWarningEmailPasswordMismatchText());
    }

    @Test(priority=6)
    public void validateWarningMessageForMultipleLoginAttemptsWIthInvalidCredentials() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(prop.getProperty("validEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);
        loginPage.clickOnLoginButton();
      Assert.assertTrue(loginPage.retrieveWarningMessageForAccountExceededLoginAttempts());
    }
    @Test
    @AfterMethod
    public void tearDOwn(){
        driver.quit();
    }
}
