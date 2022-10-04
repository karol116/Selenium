package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.passay.EnglishCharacterData.Digit;
import static org.passay.EnglishCharacterData.Special;
import static org.passay.PolishCharacterData.*;

public class RegistrationPage extends AbstractPage {
    @FindBy(css = "#login")
    static WebElement login;
    @FindBy(css = "#password")
    static WebElement password;
    @FindBy(css = "#password-repeat")
    static WebElement repeatedPassword;
    @FindBy(css = "#register-btn")
    static WebElement signUpButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String generateRandomCharacters(int minNumberOfChars, int maxNumberOfChars) {
        Random random = new Random();
        int randomNumber = random.nextInt(maxNumberOfChars - minNumberOfChars + 1) + minNumberOfChars;
        List<CharacterRule> rules = new ArrayList<>();
        rules.add(new CharacterRule(LowerCase));
        rules.add(new CharacterRule(UpperCase));
        rules.add(new CharacterRule(Digit));
        rules.add(new CharacterRule(Special));
        String pass = new PasswordGenerator().generatePassword(randomNumber, rules);
        System.out.println(pass);
        return pass;
    }

    public RegistrationPage fillRegisterFields(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(repeatedPassword));
        login.sendKeys(user);
        password.sendKeys(pass);
        repeatedPassword.sendKeys(pass);
        return PageFactory.initElements(driver, RegistrationPage.class);
    }

    public TitlesCatalog submitRegisterButton() {
        signUpButton.click();
        return PageFactory.initElements(driver, TitlesCatalog.class);
    }
}