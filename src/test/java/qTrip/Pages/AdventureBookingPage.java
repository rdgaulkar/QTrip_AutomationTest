package qTrip.Pages;

import dev.failsafe.internal.util.Durations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AdventureBookingPage {
    WebDriver driver;
    public int count = 0;
    public AdventureBookingPage(WebDriver driver){this.driver = driver;}

    public void enterUserDetails(String name, String date, String noOfPersons){
        WebElement user_Name = driver.findElement(By.name("name"));
        WebElement travelDate = driver.findElement(By.name("date"));
        WebElement numberOfPersons = driver.findElement(By.name("person"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(user_Name));
        user_Name.clear();
        user_Name.sendKeys(name);

        travelDate.clear();
        travelDate.sendKeys(date);

        numberOfPersons.clear();
        numberOfPersons.sendKeys(noOfPersons);

        driver.findElement(By.xpath("//button[contains(text(),'Reserve')]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        Assert.assertFalse(driver.findElement(By.id("reservation-panel-available")).isDisplayed(), "User booking FAILED !!!");
    }

    public void navigateToHistoryPage(){
        WebElement reservationButton = driver.findElement(By.xpath("//a[contains(text(),'Reservations')]"));
        reservationButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
