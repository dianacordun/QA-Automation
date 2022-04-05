package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class Base {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();

    }

    @AfterMethod
    public void afterMethod(){
        if(driver != null) {
            driver.quit();
        }
    }

    public void selectElement(WebElement element, String value){
        Select s = new Select(element);
        s.selectByValue(value);
    }

}
