package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;


    @FindBy(id = "input-email")
    private WebElement emailTextBox;

    @FindBy(id = "input-password")
    private WebElement passwordTextBox;

    @FindBy(css = "input.btn.btn-primary")
    private WebElement loginButton;

    @FindBy(css = "#account-login > div.alert.alert-danger.alert-dismissible")
    private WebElement invalidEmailPasswordWarning;

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgotPasswordLink;

    @FindBy(css = "#account-login > div.alert.alert-success.alert-dismissible")
    private WebElement emailConfirmationMessage;

    @FindBy(id = "input-email")
    private WebElement emailForForgotPassword;

    @FindBy(css = "input.btn.btn-primary")
    private WebElement continueButtonForgotEmail;

    @FindBy (css = "#account-login > div.alert.alert-danger.alert-dismissible")
    private WebElement exceededLoginAttempts;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String emailText) {
        emailTextBox.sendKeys(emailText);
    }

    public void enterPassword(String passwordText) {
        passwordTextBox.sendKeys(passwordText);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void forgotPassword(){
        forgotPasswordLink.click();
    }
    public void enterEmailToForgotPasswordFieldBox(String emailForgotText){
        emailForForgotPassword.sendKeys(emailForgotText);
    }
    public void continueButtonForgotEmailPage(){
        continueButtonForgotEmail.click();
    }

    public boolean retrieveWarningEmailPasswordMismatchText() {
        boolean warningMessage = invalidEmailPasswordWarning.isDisplayed();
        return warningMessage;
    }
    public boolean confirmationLinkSentToEmailIsDisplayed(){
        boolean confirmationLink = emailConfirmationMessage.isDisplayed();
        return confirmationLink;
    }
    public boolean retrieveWarningMessageForAccountExceededLoginAttempts(){
        boolean attemptWarningMessage = exceededLoginAttempts.isDisplayed()  ;
        return attemptWarningMessage;
    }
}
