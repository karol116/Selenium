package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
    @FindBy(css = "#login")
    static WebElement login;
    @FindBy(css = "#password")
    static WebElement password;
    @FindBy(css = "#login-btn")
    static WebElement logInButton;
    @FindBy(css = "#register-btn")
    static WebElement signUpButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage moveToRegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        signUpButton.click();

        return PageFactory.initElements(driver, RegistrationPage.class);
    }

    public TitlesCatalog logInUser(WebDriver driver, String user, String pass) {
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        login.sendKeys(user);
        password.sendKeys(pass);
        logInButton.click();

        return new TitlesCatalog(driver);
    }
}