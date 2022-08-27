package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.valueOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TitlesCatalog extends AbstractPage {

    @FindBy(css = "#add-title-button")
    static WebElement addNewTitleButton;
    @FindBy(xpath = "//div[1]/label")
    static WebElement title;
    @FindBy(xpath = "//div[2]/label")
    static WebElement author;
    @FindBy(xpath = "//div[3]/label")
    static WebElement year;
    @FindBy(xpath = "//form /button")
    static WebElement confirmAddedNewTitleButton;
    @FindBy(xpath = "//li/div[2]/button[2]")
    static List<WebElement> removeButtons;

    private final WebDriverWait wait = new WebDriverWait(driver, 2);

    public TitlesCatalog(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TitlesCatalog addTitle(String bookTitle, String authorName, int publicationYear) {
        wait.until(elementToBeClickable(addNewTitleButton));
        addNewTitleButton.click();
        title.sendKeys(bookTitle);
        author.sendKeys(authorName);
        year.sendKeys(valueOf(publicationYear));
        confirmAddedNewTitleButton.click();
        return new TitlesCatalog(driver);
    }

    public TitlesCatalog removeTitle(int indexOfTitleRemoveButtons) {
        wait.until(elementToBeClickable(removeButtons.get(indexOfTitleRemoveButtons - 1))).click();
        return new TitlesCatalog(driver);
    }

    public ListOfCopies showTitleCopies(int indexOfTitle) {
//        wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(1)));

        List<WebElement> booksCopies = driver.findElements(By.xpath("//div[2]/a/button"));
        booksCopies.get(indexOfTitle - 1).click();
        return PageFactory.initElements(driver, ListOfCopies.class);
    }

}
