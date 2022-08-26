package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBookAppTestSuite {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        wait = new WebDriverWait(driver, 10);
    }

//    @After
//    public void tearDown() {
//        driver.close();
//    }

    @Test
    public void shouldRegisterUser() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        RegistrationPage registrationPage = mainPage.moveToRegistrationPage();

        String randomLogin = registrationPage.generateRandomCharacters(2, 15);
        String randomPass = registrationPage.generateRandomCharacters(2, 15);

        registrationPage.fillRegisterFields(randomLogin, randomPass).submitRegisterButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#app > div > form > div.alert.alert--success > p")));
        String successfulRegistrationMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")).getText();

        Assertions.assertEquals("You have been successfully registered!", successfulRegistrationMessage);
    }

    @Test
    public void shouldNotRegisterExistingUser() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        RegistrationPage registrationPage = mainPage.moveToRegistrationPage();
        registrationPage.fillRegisterFields("user111", "stR091").submitRegisterButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p")));
        String unSuccessfulRegistrationMessage = driver.findElement(By.xpath("//div/p")).getText();

        Assertions.assertEquals("This user already exist!", unSuccessfulRegistrationMessage);
    }

    @Test
    public void shouldLogInRegisteredUser() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.logInUser("user111", "stR091");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-title-button")));

        String nameOfAddNewTitleButton = driver.findElement(By.cssSelector("#add-title-button")).getText();
        Assertions.assertEquals("ADD NEW",nameOfAddNewTitleButton);
    }

    @Test
    public void shouldNotLogInNotRegisteredUser() {

    }
}
