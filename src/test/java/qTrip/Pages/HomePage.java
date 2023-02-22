package qTrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HomePage {

    WebDriver driver;
    String home_URL = "https://qtripdynamic-qa-frontend.vercel.app/";

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToHomePage(){
        if(!driver.getCurrentUrl().equals(home_URL))
            driver.get(home_URL);
    }

    public void searchCity(String cityName){
        WebElement citySearch_textField = driver.findElement(By.id("autocomplete"));
        WebElement searchedCity_dropdown = driver.findElement(By.xpath("//ul[@id='results']"));

        while (searchedCity_dropdown.getText().isEmpty()){
            citySearch_textField.clear();
            citySearch_textField.sendKeys(cityName+" ");
        }

        if(searchedCity_dropdown.getText().contains("No City found")){
            System.out.println("TC02 - VERIFICATION - 'No City found' search PASSED !!!");
        }
        else if(searchedCity_dropdown.getText().equalsIgnoreCase(cityName)){
            //Assert.assertTrue(searchedCity_dropdown.getText().contains(cityName), "VERIFICATION - city search FAILED !!!");
            System.out.println("TC02 - VERIFICATION - city search PASSED !!!");
            searchedCity_dropdown.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("TC02 FAILED " + searchedCity_dropdown.getText().toString());
        }
    }
}
