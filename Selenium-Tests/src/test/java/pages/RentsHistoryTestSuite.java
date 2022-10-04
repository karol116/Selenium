package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RentsHistoryTestSuite {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    TitlesCatalog titlesCatalog;
    ListOfCopies listOfCopies;
    RentsHistory rentsHistory;

    @Before
    public void testSetUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        titlesCatalog = mainPage.logInUser("user111", "stR091");
        titlesCatalog.addTitle("Title", "Author", 1999);
        listOfCopies = titlesCatalog.showTitleCopies(1);
        listOfCopies.addCopyOfBookToList();
        rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void shouldAddBookRentToRentsHistoryCatalog() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("name");
        String customerName = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        assertEquals("name", customerName);
    }

    @Test
    public void shouldUpdateBookRentInformation() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("name");
        rentsHistory.updateTitle("updatedName");

        String updatedName = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        assertEquals("updatedName", updatedName);
    }

    @Test
    public void shouldRemoveBookRentFromRentsHistoryCatalog() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("name");
        rentsHistory.removeBookRent(1);

        wait.until(visibilityOfElementLocated(By.xpath("//div/div/div/div[2]/p")));
        String rentsHistoryInformation = driver.findElement(By.xpath("//div/div/div/div[2]/p")).getText();

        assertEquals("No rents...", rentsHistoryInformation);
    }

    @Test
    public void shouldChangeAvailabilityAfterBookCopyRentToNotAvailable() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("testName");
        listOfCopies = rentsHistory.returnToListOfCopies();

        WebElement bookAvailabilityField = driver.findElement(By.xpath("//ul/li[1]/div/div[3]"));
        String actualAvailabilityStatus = bookAvailabilityField.getText();

        //clean data after tests
        rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
        rentsHistory.removeBookRent(1);
        listOfCopies = rentsHistory.returnToListOfCopies();
        listOfCopies.removeCopyOfBook(1);
        titlesCatalog = listOfCopies.returnToTitlesCatalog();
        Thread.sleep(1500);
        titlesCatalog.removeTitle(1);

        assertNotEquals("Available", actualAvailabilityStatus);
    }

    @Test
    public void shouldNotRentCopyToTwoPersonsAtTheSameTime() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("user1");
        rentsHistory.addBookToRentHistoryCatalog("user2");
        Thread.sleep(2000);
        List<WebElement> rentDate = driver.findElements(By.xpath("//ul/li/div/div[2]"));

        String firstRentDate = rentDate.get(0).getText();
        String secondRentDate = rentDate.get(1).getText();

        assertNotEquals(firstRentDate, secondRentDate);
    }

    @Test
    public void defaultExpirationDateShouldBeEqualsRentDatePlusThreeDays() throws InterruptedException {
        rentsHistory.addBookToRentHistoryCatalog("user1");
        rentsHistory.addBookToRentHistoryCatalog("user2");
        Thread.sleep(2000);
        List<WebElement> expirationDateOfRents = driver.findElements(By.xpath("//ul/li/div/div[3]"));

        String actualRentExpirationDate = expirationDateOfRents.get(0).getText();
        LocalDate date = LocalDate.now();
        String expectedRentExpirationDate = "(expiration: " + date.plusDays(3).format(DateTimeFormatter.ISO_LOCAL_DATE) + ")";

        assertEquals(expectedRentExpirationDate, actualRentExpirationDate);
    }
}