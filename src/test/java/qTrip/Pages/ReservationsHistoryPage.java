package qTrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReservationsHistoryPage {
    WebDriver driver;
    public ReservationsHistoryPage(WebDriver driver){this.driver = driver;}
    String reservationPage_URL = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";

    public void navigateToReservationPage(){
        if(driver.getCurrentUrl() != reservationPage_URL)
            driver.get(reservationPage_URL);
    }

    public int getTransactionID(){
        List<WebElement> transactionIDs = driver.findElements(By.xpath("//tbody/tr/th"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return transactionIDs.size();
    }

    public void cancelTranslation(){
        WebElement cancelButton = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
        //WebElement cancelButton = driver.findElement(By.xpath("//*[@id='1aa1e775e6448743']"));
        cancelButton.click();

        while(!cancelButton.isDisplayed()){
            cancelButton.click();
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOf(cancelButton));
        }
    }
}