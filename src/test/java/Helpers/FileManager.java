package Helpers;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class FileManager {

    public void saveToFile(String content, String path) throws FileNotFoundException {
        PrintWriter w = new PrintWriter(path);
        w.write(content);

        w.close();
        w.flush();
    }

    public String readJson(String key) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("credentials.json"));
            JsonObject data = gson.fromJson(reader, JsonObject.class);
            return data.getAsJsonObject().get(key).getAsString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public JsonArray readJsonArray(String file) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file));
        JsonArray arr = gson.fromJson(reader, JsonArray.class);
        return arr.getAsJsonArray();
    }

    public String prettyJson(JsonElement jSon) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            return gson.toJson(jSon);
        } catch (Exception e) {
            return jSon.toString();
        }
    }

    public String prettyPrintArray(JsonArray arr){
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        return gson.toJson(arr);
    }


}
