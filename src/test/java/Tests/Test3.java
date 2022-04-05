package Tests;

import Base.Base;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Test3 extends Base {
    @Test
    public void getCurrentPrice(){
        /**
         * 1. Make a GET request on https://api.coindesk.com/v1/bpi/currentprice.json
         */
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("https://api.coindesk.com/v1/bpi/currentprice.json")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        /**
         * 2. Print in console chartName, GBP/rate, EUR/rate_float
         */
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        System.out.println("Chart Name: " + data.getAsJsonObject().get("chartName").getAsString());
        System.out.println("GBP/rate: " + data.getAsJsonObject().get("bpi").getAsJsonObject().get("GBP").getAsJsonObject().get("rate").getAsString());
        System.out.println("EUR/rate_float: " + data.getAsJsonObject().get("bpi").getAsJsonObject().get("EUR").getAsJsonObject().get("rate_float").getAsDouble());
    }
}
