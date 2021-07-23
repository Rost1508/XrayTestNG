package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class Guests extends BasePage {

    /**Constructor*/
    public Guests(WebDriver driver) {
        super(driver);
    }

    By NEW_GUEST = By.xpath("//span[text()='Guests']/following::div[@title='New']");
    By FIRST_NAME_FIELD = By.xpath("//div//input[@name='thn__FirstName__c']");
    By SEND_TO_MEWS_CHECKBOX = By.xpath("//div[@class='isModal inlinePanel oneRecordActionWrapper']//lightning-input//span[text()='Send to Mews']");
    By SAVE_BUTTON = By.xpath("//div[@class='isModal inlinePanel oneRecordActionWrapper']//button[@name='SaveEdit']");
    By HELP_ERROR_MESSAGE = By.xpath("//div[@data-help-message]");
    By CLOSE_WINDOW_BUTTON = By.
            xpath("//div[@class='modal-container slds-modal__container']//button[@title='Close this window']");

    @Step("Open Guests page")
    public Guests goToGuests() throws InterruptedException {
        driver.navigate().to("https://agility-efficiency-64-dev-ed.lightning.force.com/lightning/o/thn__Guest__c/list?filterName=Recent");
        try{if(wait2.until(ExpectedConditions.alertIsPresent())!=null){
            Alert alert = wait2.until(alertIsPresent());
            alert.accept();
        }}catch (TimeoutException e){
        }
        return this;
    }

    @Step
    public void clickNew(){
        wait1.until(ExpectedConditions.elementToBeClickable(NEW_GUEST)).click();
    }


    @Step("Fill out the Guest form where Send_to_Mews__c == true")
    public void createGuest(String name) throws InterruptedException {
        wait1.until(ExpectedConditions.presenceOfElementLocated(FIRST_NAME_FIELD));
        writeText(FIRST_NAME_FIELD, (name));
        wait1.until(ExpectedConditions.presenceOfElementLocated(SEND_TO_MEWS_CHECKBOX)).click();
        wait1.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    }

    @Step("Read help error message")
    public String readHelpErrorMessage() throws InterruptedException {
        return readRecalculateMessage(HELP_ERROR_MESSAGE);
    }

    @Step("Close window")
    public void closeWindow(){
        wait1.until(ExpectedConditions.elementToBeClickable(CLOSE_WINDOW_BUTTON)).click();
    }
}
