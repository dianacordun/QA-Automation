package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

public class SortByReviews implements Comparator<WebElement> {
    @Override
    public int compare(WebElement o1, WebElement o2) {

        WebElement rootWebElement1 = o1.findElement(By.xpath(".//div[@class = 'card-v2-rating mrg-btm-xxs']"));
        List<WebElement> children1 = rootWebElement1.findElements(By.xpath(".//*"));
        WebElement rootWebElement2 = o2.findElement(By.xpath(".//div[@class = 'card-v2-rating mrg-btm-xxs']"));
        List<WebElement> children2 = rootWebElement2.findElements(By.xpath(".//*"));
        //in case one or both of them has no reviews
        if (children1.size() == 0){
            if(children2.size() == 0) return 0;
            else return -1;
        }else if(children2.size() == 0) return 1;



        Integer nr1 = Integer.parseInt(o1.findElement(By.xpath(".//span[@class='visible-xs-inline-block ']")).getText().replaceAll("[^0-9]", ""));
        Integer nr2 = Integer.parseInt(o2.findElement(By.xpath(".//span[@class='visible-xs-inline-block ']")).getText().replaceAll("[^0-9]", ""));

        if (nr1 > nr2) return 1;
        else if(nr1 < nr2) return -1;
        return 0;
    }

}
