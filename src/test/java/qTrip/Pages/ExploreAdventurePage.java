package qTrip.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExploreAdventurePage {

    WebDriver driver;
    WebElement durationDropdown;
    Select hr_dropdown;
    WebElement categoryDropdown;
    Select cat_dropdown;
    int count = 0;

    public ExploreAdventurePage(WebDriver driver){
        this.driver = driver;
    }

    public void verify_filterByDuration(){
        durationDropdown = driver.findElement(By.id("duration-select"));
        hr_dropdown = new Select(durationDropdown);
        List<String> durationOptions = new ArrayList<>();
        for (WebElement ele: hr_dropdown.getOptions()) {
            durationOptions.add(ele.getText());
        }
        List<String> durationOption_string  = new ArrayList<>(Arrays.asList("Filter by Duration (Hours)","0-2 Hours","2-6 Hours","6-12 Hours","12+ Hours"));
        Assert.assertTrue(durationOptions.equals(durationOption_string), "Hour verification failed !!");
    }

    public void verify_addCategory(){
        categoryDropdown = driver.findElement(By.id("category-select"));
        cat_dropdown = new Select(categoryDropdown);
        List<String> categoryOptions = new ArrayList<>();
        for (WebElement ele: cat_dropdown.getOptions()) {
            categoryOptions.add(ele.getText());
        }
        List<String> categoryOptions_String = new ArrayList<>(Arrays.asList("Add Category","Cycling Routes","Hillside Getaways","Serene Beaches","Party Spots"));
        Assert.assertTrue(categoryOptions.equals(categoryOptions_String),"Category verification failed !!");
    }

    public int numberOfSearch(){
        List<WebElement> list_of_search = driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']"));
        count = list_of_search.size();
        return count;
    }

    public void select_Duration(String hr_visualText){
        if(hr_visualText.isEmpty())
            return;
        hr_dropdown.selectByVisibleText(hr_visualText);
    }

    public void select_Category(String category_visualText){
        if(category_visualText.isEmpty())
            return;
        cat_dropdown.selectByVisibleText(category_visualText);
    }

    public void clearAll(){
        driver.findElement(By.xpath("//div[@onclick='clearDuration(event)']")).click();
        driver.findElement(By.xpath("//div[@onclick='clearCategory(event)']")).click();
        driver.findElement(By.xpath("//div[@onclick='resetAdventuresData()']")).click();
    }

    public void select_adventure(String nameOfSite){
        WebElement adventure = driver.findElement(By.xpath(String.format("//h5[contains(text(),'%s')]//ancestor::a", nameOfSite)));
        adventure.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("/?adventure="), "Adventure selection failed !!");
    }
}
