package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TitlesCatalog extends AbstractPage {

    public TitlesCatalog(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public AddTitlePage addTitle(){
        return new AddTitlePage(driver);
    }
    public ListOfCopies showTitleCopies(int indexOfTitle) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-title-button")));

        List<WebElement> booksCopies = driver.findElements(By.xpath("//div[2]/a/button"));
        booksCopies.get(indexOfTitle - 1).click();
        return PageFactory.initElements(driver, ListOfCopies.class);
    }

}
