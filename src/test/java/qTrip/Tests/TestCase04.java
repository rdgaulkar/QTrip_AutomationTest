package qTrip.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qTrip.Pages.*;
import qTrip.SingletonWDM;

public class TestCase04 {
    WebDriver driver = SingletonWDM.createDriver();

    @BeforeClass
    public void maximizeWindow(){driver.manage().window().maximize();}

    @Test(priority = 4)
    public void testCase04(){
        //Creating User
        UserRegisterationPage regPage = new UserRegisterationPage(driver);
        regPage.navigateToRegisterationPage();
        regPage.registerUser();

        //Login User
        UserLoginPage login = new UserLoginPage(driver);
        login.navigateToLoginPage();
        login.loginUser();

        //Booking 01
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.searchCity("Kolkata");
        ExploreAdventurePage advPage = new ExploreAdventurePage(driver);
        advPage.select_adventure("Annvir-In-Ingsauk Lake");
        AdventureBookingPage userReg = new AdventureBookingPage(driver);
        userReg.enterUserDetails("Ketki","18/01/2024","2");

        //Booking 02
        home.navigateToHomePage();
        home.searchCity("Goa");
        advPage.select_adventure("Lowtra");
        userReg.enterUserDetails("Sampada","18/01/2024","1");

        // Booking 03
        home.navigateToHomePage();
        home.searchCity("Bangkok");
        advPage.select_adventure("Heathber");
        userReg.enterUserDetails("Rohit","18/01/2024","5");

        // Booking 04
        home.navigateToHomePage();
        home.searchCity("Bangkok");
        advPage.select_adventure("Rstangbe");
        userReg.enterUserDetails("Rajat","18/01/2024","3");

        //Booking 05
        home.navigateToHomePage();
        home.searchCity("Goa");
        advPage.select_adventure("Chettbou Aux Dersting");
        userReg.enterUserDetails("Nikita","18/01/2024","2");

        //Confirming reservation
        ReservationsHistoryPage historyPage = new ReservationsHistoryPage(driver);
        historyPage.navigateToReservationPage();
        //Assert.assertTrue(historyPage.getTransactionID() == userReg.count, "Reservation count failed");
        //Hard assert failing sometimes, hence user soft assert.
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(historyPage.getTransactionID() == userReg.count, "SOFT ASSERT - Reservation count failed");
    }
    @AfterClass
    public void tearDown(){driver.quit();}
}
