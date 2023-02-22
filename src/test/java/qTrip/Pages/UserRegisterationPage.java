package qTrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.UUID;

public class UserRegisterationPage {

    WebDriver driver;
//    static String email = UUID.randomUUID()+"@gmail.com";
//    static String password = UUID.randomUUID().toString();
    static String email;
    static String password;

    String registerationPage_URL = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public UserRegisterationPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToRegisterationPage(){
        if(!driver.getCurrentUrl().equals(registerationPage_URL))
            driver.get(registerationPage_URL);
    }

    public void registerUser(){
        email = UUID.randomUUID()+"@gmail.com";
        password = UUID.randomUUID().toString();

        WebElement registeration_email_textfield = driver.findElement(By.name("email"));
        WebElement registeration_password_textfield = driver.findElement(By.name("password"));
        WebElement registeration_confirmPassword_textfield = driver.findElement(By.name("confirmpassword"));

        registeration_email_textfield.sendKeys(email);
        registeration_password_textfield.sendKeys(password);
        registeration_confirmPassword_textfield.sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(), 'Register Now')]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginHeader = driver.findElement(By.xpath("//h2[contains(text(),'Login')]"));
        Assert.assertTrue(loginHeader.isDisplayed(), "User Registration FAILED !!");
        System.out.println("TC01 - User registration PASSED !!");
    }
}
