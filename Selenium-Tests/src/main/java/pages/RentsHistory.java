package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RentsHistory extends AbstractPage {

    @FindBy(xpath = "//body/div/div/div/button")
    static WebElement rentCopyButton;
    @FindBy(xpath = "//form/div/label/input")
    static WebElement customerNameField;
    @FindBy(xpath = "//form/button")
    static WebElement confirmRentButton;
    @FindBy(xpath = "//ul/li/div/button[1]")
    static WebElement editCopyRentInformation;
    @FindBy(xpath = "//ul/li/div/button[2]")
    static List<WebElement> removeRentButtons;
    @FindBy(css = "#return-button")
    static WebElement returnButton;

    public RentsHistory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void addBookToRentHistoryCatalog(String customerName) throws InterruptedException {
        Thread.sleep(1000);
        rentCopyButton.click();
        customerNameField.sendKeys(customerName);
        confirmRentButton.click();
    }

    public void updateTitle(String customerName) throws InterruptedException {
        Thread.sleep(1000);
        editCopyRentInformation.click();
        customerNameField.clear();
        customerNameField.sendKeys(customerName);
        confirmRentButton.click();
    }

    public void removeBookRent(int rentIndexNumber) throws InterruptedException {
        Thread.sleep(1000);
        removeRentButtons.get(rentIndexNumber - 1).click();
    }

    public ListOfCopies returnToListOfCopies() {
        returnButton.click();
        return PageFactory.initElements(driver, ListOfCopies.class);
    }
}