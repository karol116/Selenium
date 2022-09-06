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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentsHistioryTestSuite {
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
    public void shouldChangeAvailabilityAfterBookCopyRentToNotAvailable() {
    }

    @Test
    public void shouldNotRentCopyToTwoPersonsInOverlapPeriodOfTime() {
    }

    @Test
    public void defaultExpirationDateShouldBeEqualsRentDatePlusThreeDays() {

    }
}