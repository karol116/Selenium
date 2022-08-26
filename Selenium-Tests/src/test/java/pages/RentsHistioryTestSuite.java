package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentsHistioryTestSuite {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ta-ebookrental-fe.herokuapp.com/login");
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void shouldAddBookRentToRentsHistoryCatalog() {

    }

    @Test
    public void shouldUpdateBookRentInformation() {
    }

    @Test
    public void shouldRemoveBookRentFromRentsHistoryCatalog() {
    }

    @Test
    public void shouldChangeAvailabilityAfterBookCopyRentToNotAvailable() {
    }

    @Test
    public void shouldNotRentCopyToTwoPersonsInOverlapPeriodOfTime() {
    }
}