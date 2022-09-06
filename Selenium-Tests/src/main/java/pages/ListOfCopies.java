package pages;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class ListOfCopies extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"add-item-button\"]")
    static WebElement addNewBookCopyButton;
    @FindBy(xpath = "//form/button")
    static WebElement addCopyButton;
    @FindBy(xpath = "//ul/li/div/button[1]")
    static WebElement editCopyButton;
    @FindBy(xpath = "//ul/li/div/button[2]")
    static List<WebElement> removeCopyButtons;
    @FindBy(xpath = "//div/div/div/div/button")
    static WebElement returnButton;
    @FindBy(xpath = "//div/div/input")
    static WebElement inputField;
    @FindBy(xpath = "//li[1]/div/a/button")
    static WebElement showHistoryButton;
    @FindBy(xpath = "//div[2]/p")
    static WebElement noCopiesMessageField;

    public ListOfCopies(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void addCopyOfBookToList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addNewBookCopyButton)).click();
        addCopyButton.click();
    }

    public void removeCopyOfBook(int indexOfRemoveCopyButton) throws InterruptedException {
        Thread.sleep(1000);
        removeCopyButtons.get(indexOfRemoveCopyButton - 1).click();
    }

    public TitlesCatalog returnToTitlesCatalog() {
        returnButton.click();
        return PageFactory.initElements(driver, TitlesCatalog.class);
    }

//    public void updateCopy(WebDriver driver, String cssSelector, String date) throws InterruptedException {
//      Thread.sleep(1000);
//        editCopyButton.click();
//}

    public RentsHistory showRentalHistoryOfBookCopy() throws InterruptedException {
        Thread.sleep(1000);
        showHistoryButton.click();
        return PageFactory.initElements(driver, RentsHistory.class);
    }
}