package qTrip;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonWDM {

    private SingletonWDM(){}
    public static WebDriver createDriver(){
        /*System.setProperty("webdriver.chrome.driver", "");
        WebDriver driver = new ChromeDriver();
        return driver;*/

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }
}
