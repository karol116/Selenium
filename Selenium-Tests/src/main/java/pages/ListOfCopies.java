package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListOfCopies extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"add-item-button\"]")
    static WebElement addNewBookCopyButton;
    @FindBy(xpath = "//form/button")
    static WebElement addCopyButton;
    @FindBy(xpath = "//ul/li/div/button[2]")
    static List<WebElement> removeCopyButtons;
    @FindBy(xpath = "//div/div/div/div/button")
    static WebElement returnButton;

    public ListOfCopies(WebDriver driver) {
        super(driver);
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
}
