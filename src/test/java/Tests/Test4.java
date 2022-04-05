package Tests;

import Base.Base;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Test4 extends Base {
    @Test
    public void postLogin(){
        /**
         * 1. Make a POST request to  https://demoqa.com/Account/v1/Login
         * 2. Body: {"userName":"theTeam","password":"MyPassword1!"}
         */
        //Another solution would be to read the credentials from a json file, but since it's a small request I used this method
        JsonObject object = new JsonObject();
        object.addProperty("userName","theTeam");
        object.addProperty("password","MyPassword1!");

        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(object.toString())
                        .post(" https://demoqa.com/Account/v1/Login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();
        /**
         * 3. Print in console isActive value
         */
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        System.out.println(data.getAsJsonObject().get("isActive").getAsBoolean());
    }

}
