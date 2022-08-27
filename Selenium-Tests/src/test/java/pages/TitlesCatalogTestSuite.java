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

import java.util.concurrent.TimeUnit;

public class TitlesCatalogTestSuite {
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
    public void shouldAddBookToTitlesCatalog() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        TitlesCatalog titlesCatalog = mainPage.logInUser("user111", "stR091");
        titlesCatalog.addTitle("Czysty kod", "Robert C. Martin", 2009);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String authorName = driver.findElement(By.xpath("//li/div[1]/div[1]")).getText();
        Assertions.assertEquals("CZYSTY KOD", authorName);
        titlesCatalog.removeTitle(1);
    }

    @Test
    public void shouldUpdateBookInformation() {
    }

    @Test
    public void shouldRemoveBookFromTitlesCatalog() {
    }
}