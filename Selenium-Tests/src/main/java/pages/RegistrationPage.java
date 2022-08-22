package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends AbstractPage {
    @FindBy(css = "#login")
    static WebElement login;
    @FindBy(css = "#password")
    static WebElement password;
    @FindBy(css = "#password-repeat")
    static WebElement repeatedPassword;
    @FindBy(css = "#login-btn")
    static WebElement logInButton;
    @FindBy(css = "#register-btn")
    static WebElement signUpButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage fillRegisterFields(WebDriver driver) {
        PageFactory.initElements(driver, RegistrationPage.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(repeatedPassword));

        login.sendKeys("111grgrt");
        password.sendKeys("wgfer");
        repeatedPassword.sendKeys("wgfer");

        return PageFactory.initElements(driver, RegistrationPage.class);
    }

    public TitlesCatalog submitRegisterButton() {
        signUpButton.click();
        return PageFactory.initElements(driver, TitlesCatalog.class);
    }
}