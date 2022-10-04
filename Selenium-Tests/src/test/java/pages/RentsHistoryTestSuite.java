package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RentsHistoryTestSuite {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    TitlesCatalog titlesCatalog;
    ListOfCopies listOfCopies;

    @Before
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        titlesCatalog = mainPage.logInUser("user111", "stR091");
        titlesCatalog.addTitle("Title", "Author", 1999);
        listOfCopies = titlesCatalog.showTitleCopies(1);
    }

//    @After
//    public void tearDown() {
//        driver.close();
//    }

    @Test
    public void shouldAddBookRentToRentsHistoryCatalog() throws InterruptedException {
        listOfCopies.addCopyOfBookToList();
        RentsHistory rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
        rentsHistory.addBookToRentHistoryCatalog("name");
        String customerName = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        assertEquals("name", customerName);
    }

    @Test
    public void shouldUpdateBookRentInformation() throws InterruptedException {
        listOfCopies.addCopyOfBookToList();
        RentsHistory rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
        rentsHistory.addBookToRentHistoryCatalog("name");
        rentsHistory.updateTitle("updatedName");

        String updatedName = driver.findElement(By.xpath("//ul/li/div/div[1]")).getText();
        assertEquals("updatedName", updatedName);
    }

    @Test
    public void shouldRemoveBookRentFromRentsHistoryCatalog() throws InterruptedException {
        listOfCopies.addCopyOfBookToList();
        RentsHistory rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
        rentsHistory.addBookToRentHistoryCatalog("name");
        rentsHistory.removeBookRent(1);
    }

    @Test
    public void shouldChangeAvailabilityAfterBookCopyRentToNotAvailable() throws InterruptedException {
        listOfCopies.addCopyOfBookToList();
        RentsHistory rentsHistory = listOfCopies.showRentalHistoryOfBookCopy();
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
    public void shouldNotRentCopyToTwoPersonsInOverlapPeriodOfTime() {
    }

    @Test
    public void defaultExpirationDateShouldBeEqualsRentDatePlusThreeDays() {

    }
}