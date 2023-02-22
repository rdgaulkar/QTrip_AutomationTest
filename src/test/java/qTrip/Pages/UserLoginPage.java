package qTrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserLoginPage /*extends userRegisterationPage*/{

    WebDriver driver;
    String loginPage_URL = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";

    public UserLoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage(){
        if(!driver.getCurrentUrl().equals(loginPage_URL))
            driver.get(loginPage_URL);
    }

    public void loginUser(){
            WebElement login_email_textfield =driver.findElement(By.name("email"));
            WebElement login_password_textfield =driver.findElement(By.name("password"));

            login_email_textfield.sendKeys(UserRegisterationPage.email);
            login_password_textfield.sendKeys(UserRegisterationPage.password);

            driver.findElement(By.xpath("//button[contains(text(),'Login to QTrip')]")).click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement logoutButton = driver.findElement(By.xpath("//div[@class='nav-link login register']"));
            Assert.assertTrue(logoutButton.isDisplayed(), "TC01 - User login FAILED !!");
            System.out.println("TC01 - User login PASSED !!");

    }

    public void logoutUser(){
        driver.findElement(By.xpath("//div[contains(text(), 'Logout')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Login Here')]")).isDisplayed(),"TC01 - User logout FAILED !!");
        System.out.println("TC01 - User logout PASSED !!");
    }
}
