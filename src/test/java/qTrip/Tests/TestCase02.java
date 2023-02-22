package qTrip.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qTrip.Pages.ExploreAdventurePage;
import qTrip.Pages.HomePage;
import qTrip.Pages.UserLoginPage;
import qTrip.Pages.UserRegisterationPage;
import qTrip.SingletonWDM;

public class TestCase02 {

    WebDriver driver = SingletonWDM.createDriver();

    @BeforeClass
    public void maximizeWindow(){driver.manage().window().maximize();}

    @Test(priority = 2)
    public void testCase02(){

        boolean status = false;
        HomePage home = new HomePage(driver);

        home.navigateToHomePage();
        WebElement logInHear_link = driver.findElement(By.xpath("//a[contains(text(), 'Login Here')]"));
        WebElement register_Link = driver.findElement(By.xpath("//a[contains(text(), 'Register')]"));

        //Perform user registration & login on home page if user is not already logged in.
        if(logInHear_link.isDisplayed()) {
            register_Link.click();
            UserRegisterationPage register_page = new UserRegisterationPage(driver);
            register_page.navigateToRegisterationPage();
            register_page.registerUser();
            UserLoginPage login_page = new UserLoginPage(driver);
            login_page.navigateToLoginPage();
            login_page.loginUser();
        }

        //Searching city
        home.searchCity("goa");


        ExploreAdventurePage advPage = new ExploreAdventurePage(driver);
        advPage.verify_filterByDuration();
        advPage.verify_addCategory();
        int start_searchCount = advPage.numberOfSearch();
        advPage.select_Duration("6-12 Hours");
        advPage.select_Category("Cycling Routes");
        advPage.clearAll();
        int end_searchCount = advPage.numberOfSearch();
        Assert.assertTrue(start_searchCount==end_searchCount, "Record display verification failed !!");
    }

    @AfterClass
    public void tearDown(){driver.quit();}
}
