package qTrip.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qTrip.Pages.*;
import qTrip.SingletonWDM;

import static org.openqa.selenium.devtools.v105.page.Page.navigate;

public class TestCase03 {
    WebDriver driver = SingletonWDM.createDriver();

    @BeforeClass
    public void maximizeWindow(){driver.manage().window().maximize();}

    @Test(priority = 3)
    public void testCase03(){
        // Register User
        UserRegisterationPage regPage = new UserRegisterationPage(driver);
        regPage.navigateToRegisterationPage();
        regPage.registerUser();

        // Login User
        UserLoginPage login = new UserLoginPage(driver);
        login.navigateToLoginPage();
        login.loginUser();

        // Searching City
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.searchCity("Bangkok");

        // Selecting adventure
        ExploreAdventurePage advPage = new ExploreAdventurePage(driver);
        advPage.select_Duration("");
        advPage.select_Category("");
        advPage.select_adventure("Rstangbe");

        //User booking
        AdventureBookingPage booking = new AdventureBookingPage(driver);
        booking.enterUserDetails("Rohit", "18/18/2023", "2");
        booking.navigateToHistoryPage();

        //Checking Transactions
        ReservationsHistoryPage history = new ReservationsHistoryPage(driver);
        int IDCounts = history.getTransactionID();
        history.cancelTranslation();
        System.out.println("ID count: "+IDCounts);
        System.out.println("Count before cancellation: "+booking.count);
        System.out.println("Count after cancellation: "+history.countAfterCancellation);
        Assert.assertTrue(history.countAfterCancellation == IDCounts-1, "Transaction verification FAILED !!");
        //SoftAssert softAssert = new SoftAssert();
        //softAssert.assertTrue(history.getTransactionID() == booking.count, "SOFT ASSERT - Reservation count failed");
    }

    @AfterClass
    public void tearDown(){ driver.quit();}
}
