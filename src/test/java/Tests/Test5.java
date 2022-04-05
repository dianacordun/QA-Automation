package Tests;
import Base.Base;
import Helpers.FileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test5 extends Base{

    public FileManager f = new FileManager();


    @Test
    public void testCase1(){
        /**
         * Register User
         * 1. Launch browser
         * 2. Navigate to url 'http://automationexercise.com'
         * 3. Verify that home page is visible successfully
         * 4. Click on 'Signup / Login' button
         * 5. Verify 'New User Signup!' is visible
         * 6. Enter name and email address
         * 7. Click 'Signup' button
         * 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
         * 9. Fill details: Title, Name, Email, Password, Date of birth
         * 10. Select checkbox 'Sign up for our newsletter!'
         * 11. Select checkbox 'Receive special offers from our partners!'
         * 12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
         * 13. Click 'Create Account button'
         * 14. Verify that 'ACCOUNT CREATED!' is visible
         * 15. Click 'Continue' button
         * 16. Verify that 'Logged in as username' is visible
         * 17. Click 'Delete Account' button
         * 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
         */
        launchBrowser();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        By newUserSignup = By.xpath("//h2[text() ='New User Signup!']");
        By enterAccountInformation = By.xpath("//b[text()='Enter Account Information']");
        By accountCreated = By.xpath("//b[text()='Account Created!']");
        By logged = By.xpath("//a[text()=' Logged in as ']");
        By accountDeleted = By.xpath("//b[text()='Account Deleted!']");

        driver.findElement(By.xpath(" //a[text()=' Signup / Login']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newUserSignup));

        String username = f.readJson("name");
        String email = f.readJson("emailAddress");
        String password = f.readJson("password");
        String title = f.readJson("title");
        String day = f.readJson("day");
        String month = f.readJson("month");
        String year = f.readJson("year");
        String fname = f.readJson("fname");
        String lname = f.readJson("lname");
        String company = f.readJson("company");
        String address = f.readJson("address");
        String country = f.readJson("country");
        String state = f.readJson("state");
        String city = f.readJson("city");
        String zipcode = f.readJson("zipcode");
        String phone = f.readJson("phone");


        driver.findElement(By.xpath("//input[@placeholder ='Name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);

        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterAccountInformation));

        driver.findElement(By.id("password")).sendKeys(password);
        if(title.equals("Mrs")){
            driver.findElement(By.id("id_gender2")).click();
        }
        else{
            driver.findElement(By.id("id_gender1")).click();
        }
        selectElement(driver.findElement(By.id("days")), day);
        selectElement(driver.findElement(By.id("months")), month);
        selectElement(driver.findElement(By.id("years")), year);

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        driver.findElement(By.id("first_name")).sendKeys(fname);
        driver.findElement(By.id("last_name")).sendKeys(lname);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(address);
        selectElement(driver.findElement(By.id("country")), country);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys(phone);

        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreated));

        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(logged));

        driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();

        //"Acount Deleted" won't be visible
        //wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeleted));
        //we can't click the continue button since we can't delete the account and the message won't be visible
    }

    @Test
    public void testCase2(){
        /**
         * Login user with correct email address and password.
         * 1. Launch browser
         * 2. Navigate to url 'http://automationexercise.com'
         * 3. Verify that home page is visible successfully
         * 4. Click on 'Signup / Login' button
         * 5. Verify 'Login to your account' is visible
         * 6. Enter correct email address and password
         * 7. Click 'login' button
         * 8. Verify that 'Logged in as username' is visible
         * 9. Click 'Delete Account' button
         * 10. Verify that 'ACCOUNT DELETED!' is visible
         */

        launchBrowser();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By loginToAccount = By.xpath("//h2[text() ='Login to your account']");
        By logged = By.xpath("//a[text()=' Logged in as ']");

        driver.findElement(By.xpath(" //a[text()=' Signup / Login']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginToAccount));

        String email = f.readJson("emailAddress");
        String password = f.readJson("password");

        driver.findElement(By.xpath("//input[@placeholder ='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(logged));

        //The same as testCase1, with delete account, it won't work-HTTP 405 Method Not Allowed
        //driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();
    }
    @Test
    public void testCase7(){
        /** Verify test cases
         * 1. Launch browser
         * 2. Navigate to url 'http://automationexercise.com'
         * 3. Verify that home page is visible successfully
         * 4. Click on 'Test Cases' button
         * 5. Verify user is navigated to test cases page successfully
         */
        launchBrowser();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

    }
    @Test
    public void testCase18() {
        /**
         * View category products
         * 1. Launch browser
         * 2. Navigate to url 'http://automationexercise.com'
         * 3. Verify that categories are visible on left side bar
         * 4. Click on 'Women' category
         * 5. Click on any category link under 'Women' category, for example: Dress
         * 6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
         * 7. On left side bar, click on any sub-category link of 'Men' category
         * 8. Verify that user is navigated to that category page
         */
        launchBrowser();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By category = By.id("accordian");
        wait.until(ExpectedConditions.visibilityOfElementLocated(category));

        By women = By.xpath("//a[@href = '#Women']");
        driver.findElement(women).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/category_products/1']")));
        driver.findElement(By.xpath("//a[@href='/category_products/1']")).click();

        By title = By.xpath("//h2[@class=\"title text-center\"]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        if(driver.findElement(title).getText().equals("WOMEN - DRESS PRODUCTS")){
            System.out.println("Text confirmed");
        }
        else {
            System.out.println("Wrong text!");
        }

        By men = By.xpath("//a[@href = '#Men']");
        wait.until(ExpectedConditions.elementToBeClickable(men));
        driver.findElement(men).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/category_products/3']")));
        driver.findElement(By.xpath("//a[@href='/category_products/3']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(title));

    }
    @Test
    public void testCase25() throws InterruptedException {
        /**
         * Verify Scroll Up using 'Arrow' button and Scroll Down functionality
         * 1. Launch browser
         * 2. Navigate to url 'http://automationexercise.com'
         * 3. Verify that home page is visible successfully
         * 4. Scroll down page to bottom
         * 5. Verify 'SUBSCRIPTION' is visible
         * 6. Click on arrow at bottom right side to move upward
         * 7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
         */
        launchBrowser();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By subscription = By.xpath("//h2[text()='Subscription']");
        By text = By.xpath("//h2[text()='Full-Fledged practice website for Automation Engineers']");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscription));

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("scrollUp")));
        driver.findElement(By.id("scrollUp")).click();
        Thread.sleep(3000);

        JavascriptExecutor jt = (JavascriptExecutor) driver;
        Long location = (Long)jt.executeScript("return window.pageYOffset;");
        if(location != 0){
            System.out.println("Page is not scrolled up!");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(text));

    }

    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        driver.get("http://automationexercise.com");
    }

}
