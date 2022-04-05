package Tests;

import Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Test2 extends Base {

    @Test
    public void form() throws InterruptedException {
        /**
         * 1. Open browser
         */
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);

        /**
         * 2. Go to https://demoqa.com/automation-practice-form
         */
        driver.get("https://demoqa.com/automation-practice-form");

        /**
         * 3. Select Date of Birth 22/10/1995.
         */
        driver.findElement(By.id("dateOfBirthInput")).click();

        selectElement(driver.findElement(By.xpath("//select[@class ='react-datepicker__month-select']")), "9");
        selectElement(driver.findElement(By.xpath("//select[@class ='react-datepicker__year-select']")), "1995");
        driver.findElement(By.xpath("//div[text() = '22']")).click();

        /**
         *  4. Select a random option for “Hobbies”.
         */

        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class= 'custom-control custom-checkbox custom-control-inline']"));
        Random r = new Random();
        int randomNumber = r.nextInt(3);
        buttons.get(randomNumber).click();

        Thread.sleep(2000);

        /**
         * 5. Verify if button “Browse” from upload picture section, is displayed and print in console the status.
         */
        if (driver.findElement(By.xpath("//input[@class ='form-control-file']")).isDisplayed())
        {
            System.out.println("Browse button is displayed.");
        }else{
            System.out.println("Browse button isn't displayed.");
        }
    }

}
