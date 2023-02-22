package qTrip.Tests;

import org.testng.annotations.*;
import qTrip.Pages.UserLoginPage;
import qTrip.Pages.UserRegisterationPage;
import qTrip.SingletonWDM;
import org.openqa.selenium.WebDriver;

public class TestCase01 {

    WebDriver driver = SingletonWDM.createDriver();

    @BeforeClass
    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    @Test(description = "User Onboarding flow", priority = 1)
    public void testCase01(){

        //User Registration
        UserRegisterationPage user_reg = new UserRegisterationPage(driver);
        user_reg.navigateToRegisterationPage();
        user_reg.registerUser();

        //User Login & logout
        UserLoginPage user_login = new UserLoginPage(driver);
        user_login.navigateToLoginPage();
        user_login.loginUser();
        user_login.logoutUser();
    }

    @AfterClass
    public void tearDown(){ driver.quit();}

}
