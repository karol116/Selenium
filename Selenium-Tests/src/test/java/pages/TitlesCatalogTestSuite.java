package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TitlesCatalogTestSuite {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    TitlesCatalog titlesCatalog;

    @Before
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        wait = new WebDriverWait(driver, 10);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        titlesCatalog = mainPage.logInUser("user111", "stR091");
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }


    @Test
    public void shouldAddBookToTitlesCatalog() {
        titlesCatalog.addTitle("Czysty kod", "Robert C. Martin", 2009);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String authorName = driver.findElement(By.xpath("//li/div[1]/div[1]")).getText();
        Assertions.assertEquals("CZYSTY KOD", authorName);

        titlesCatalog.removeTitle(1);
    }

    @Test
    public void shouldUpdateBookInformation() throws InterruptedException {
        titlesCatalog.addTitle("Czysty kod", "Robert C. Martin", 2009);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        titlesCatalog.updateTitle(1, "Pasja Testowania", "Krzysztof Jadczyk", 2020);
        Thread.sleep(1500);
        String title = driver.findElement(By.xpath("//div/div/ul/li[1]/div/div[1]")).getText();

        Assertions.assertEquals("PASJA TESTOWANIA", title);
        titlesCatalog.removeTitle(1);
    }

    @Test
    public void shouldRemoveBookFromTitlesCatalog() throws InterruptedException {
        titlesCatalog.addTitle("Czysty kod", "Robert C. Martin", 2009);
        titlesCatalog.addTitle("Docker Praktyczne Zastosowania", "Sean P. Kane", 2018);
        wait.until(elementToBeClickable(By.xpath("//ul/li[1]/div/button[2]")));
        titlesCatalog.removeTitle(1);
        wait.until(elementToBeClickable(By.xpath("//ul/li[2]/div/button[2]")));
        titlesCatalog.removeTitle(2);
        wait.until(visibilityOfElementLocated(By.xpath("//div/p")));

        String noTitlesMessage = driver.findElement(By.xpath("//div/p")).getText();

        Assertions.assertEquals("No titles", noTitlesMessage);
    }
}