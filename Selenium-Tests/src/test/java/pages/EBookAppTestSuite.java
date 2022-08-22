package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EBookAppTestSuite {
    WebDriver driver;

    @Before
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
    }

//    @After
//    public void tearDown() {
//        driver.close();
//    }

    @Test
    public void shouldRegisterUser() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = mainPage.moveToRegistrationPage(driver);
        registrationPage.fillRegisterFields(driver).submitRegisterButton();
    }

    @Test
    public void logInRegisteredUser() {
    MainPage mainPage = new MainPage(driver);
    TitlesCatalog titlesCatalog = mainPage.logInUser(driver,"yoyo","yoyo1");
    titlesCatalog.showTitleCopies(driver,1);


    }

    @Test
    public void logInNotRegisteredUser() {
    }
}
