package Tests;

import Base.Base;
import Helpers.FileManager;
import Helpers.SortByReviews;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 extends Base {

    @Test
    public void emag() throws FileNotFoundException {
        /**
         * 1. Open browser
         */
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);

        /**
         * 2. Go to https://www.emag.ro/
         */
        driver.get("https://www.emag.ro/");

        /**
         * 3. Verify that search field has placeholder “Ai libertatea să alegi ce vrei”. (Added ă so it would work)
         */
        WebElement searchField = driver.findElement(By.id("searchboxTrigger"));
        if (searchField.getAttribute("placeholder").equals("Ai libertatea să alegi ce vrei")) {
            System.out.println("The search field has the expected placeholder.");

        } else {
            System.out.println("The search field doesn't have the expected placeholder!");
        }
        System.out.println("-----------------------------------------------------------");


        /**
         * 4. Input in search field “TV” and click search
         */
        searchField.sendKeys("TV");
        driver.findElement(By.xpath("//button[@class = 'btn btn-default searchbox-submit-button']")).click();

        /**
         * 5. Print in console number of results extracting it from webelement marked
         */
        String nrOfResults = driver.findElement(By.xpath("//span[@class='title-phrasing title-phrasing-sm']")).getAttribute("innerText");
        System.out.println("Number of results: " + nrOfResults.replaceAll("[^0-9]", ""));
        System.out.println("-----------------------------------------------------------");


        /**
         * 6. Print in console number of results from page 1 that have price < 1500.
         */
        int nr = 0;
        Double smallest = 1000000.0;
        String productTitle = "";
        String smallestProductTitle = "";

        JsonArray array = new JsonArray();



        List<WebElement> allCards = new ArrayList<>();
        allCards = driver.findElements(By.xpath("//div[@class= 'card-item card-standard js-product-data']"));

        for (WebElement tv : allCards) {
            String strPrice = tv.findElement(By.xpath(".//p[@class= 'product-new-price']")).getText();
            String intPart = strPrice.substring(0, strPrice.length() - 7).replaceAll("[^0-9]", "");
            String decimals = strPrice.substring(strPrice.length() - 6, strPrice.length() - 4);
            Double price = Double.parseDouble(intPart + "." + decimals);

            JsonObject auxiliary = new JsonObject();

            if (price < 1500) {
                nr++;
            }

            productTitle = tv.findElement(By.xpath(".//a[@data-zone= 'title']")).getText();
            if (price < smallest) {
                smallest = price;
                smallestProductTitle = productTitle;
            }

            auxiliary.addProperty("price", price);
            auxiliary.addProperty("title", productTitle);
            array.add(auxiliary);

        }

        System.out.println("Number of results from page one with price < 1500 : " + nr);
        System.out.println("-----------------------------------------------------------");

        /**
         * 7. Print in console the title of product with smallest price on page 1.
         * If two products have the smallest price, the first one's title will be printed.
         */
        System.out.println("Product with smallest price on page 1: " + smallestProductTitle);
        System.out.println("-----------------------------------------------------------");
        /**
         * 8. Print in console number of results that have the “genius” option on page 1.
         */
        System.out.println("Number of products with genius option on page 1: " + driver.findElements(By.xpath("//div[@class= 'card-v2-badge badge-genius']")).size());
        System.out.println("-----------------------------------------------------------");

        /**
         * 9. Order items by numbers of reviews
         * Implemented a helper Class, SortByReviews that serves as a Comparator
         */
        Collections.sort(allCards, new SortByReviews());
        //checkSorting(allCards);


        /**
         * 10. Create a JSONArray with all products displayed on page 1 and save it to file.
         */

        FileManager f = new FileManager();
        String text = f.prettyPrintArray(array);
        f.saveToFile(text,"T1.json");

        /**
         * 11. From the file saved at point 10 print in console title for products with price smaller than 1200 RON.
         */
        JsonArray array1 = f.readJsonArray("T1.json");
        System.out.println("Products with price smaller than 1200 RON: ");

        for (int i = 0; i < array1.size(); i++){
            if (array1.get(i).getAsJsonObject().get("price").getAsDouble() < 1200.0){
                System.out.println(array1.get(i).getAsJsonObject().get("title").getAsString());
            }
        }

    }

    public void checkSorting(List<WebElement> allCards){
        for(WebElement tv : allCards){


            WebElement rootWebElement = tv.findElement(By.xpath(".//div[@class = 'card-v2-rating mrg-btm-xxs']"));
            List<WebElement> childs = rootWebElement.findElements(By.xpath(".//*"));
            if (childs.size() != 0){
                System.out.println(tv.findElement(By.xpath(".//span[@class='visible-xs-inline-block ']")).getText());
            }
            else
                System.out.println("0");
        }
    }



}
