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

import java.time.LocalDate;
//TO DO
//Adding methods cleaning entered data

public class ListOfCopiesTestSuite {
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
//
//    @After
//    public void tearDown() {
//        driver.close();
//    }


    @Test
    public void shouldAddCopyOfBookToListOfCopies() throws InterruptedException {

        listOfCopies.addCopyOfBookToList();
        Thread.sleep(1000);
        String indexNumber = driver.findElement(By.xpath("//ul/li/div/div[3]")).getText();
        Assertions.assertEquals("Available", indexNumber);

        //
        listOfCopies.removeCopyOfBook(1);
        titlesCatalog = listOfCopies.returnToTitlesCatalog();
        Thread.sleep(1000);
        titlesCatalog.removeTitle(1);


    }

    @Test
    public void shouldRemoveCopyOfBookFromListOfCopies() throws InterruptedException {
        listOfCopies.addCopyOfBookToList();
        listOfCopies.removeCopyOfBook(1);
        Thread.sleep(1000);
        String noTitlesMessage = driver.findElement(By.xpath("//div/p")).getText();
        Assertions.assertEquals("No copies...", noTitlesMessage);
    }

//    @Test
//    public void shouldUpdateCopyPurchaseDate() throws InterruptedException {
//        listOfCopies.addCopyOfBookToList();
//        listOfCopies.updateCopy(driver, "#id","01/09/2020");
//    }

//Test failed
    @Test
    public void defaultPurchaseDateShouldBeEqualsCurrentDate() {
        listOfCopies.addCopyOfBookToList();
        String dateOfPurchase = driver.findElement(By.xpath("//ul/li/div/div[2]")).getText();
        String expectedDateOfPurchase = LocalDate.now().plusDays(3).toString();

        Assertions.assertEquals(expectedDateOfPurchase, dateOfPurchase);
    }
}