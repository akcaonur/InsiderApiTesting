package org.onurakca;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class BaseTest {

    RequestSpecification spec;
    public String baseUrl = "https://petstore.swagger.io/v2";

    public File jsonFileBody = new File("src/test/resources/body.json");
    JSONObject requestBody = readJsonFromFile(jsonFileBody);


    @BeforeEach
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();

    }
    public JSONObject readJsonFromFile(File file) {
        JSONObject jsonObject = null;
        try {
            FileReader reader = new FileReader(file);
            JSONTokener tokener = new JSONTokener(reader);
            jsonObject = new JSONObject(tokener);
            reader.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void writeJsonToFile(JSONObject jsonObject, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonObject.toString(4));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRequestJson(int id,int categoryId,String categoryName,String name,
                                  String photoUrl, int tagId,String tagName,String status){
        requestBody.put("id",id);

        JSONObject category = requestBody.getJSONObject("category");
        category.put("id",categoryId);
        category.put("name",categoryName);

        requestBody.put("name",name);

        JSONArray photoUrls = requestBody.getJSONArray("photoUrls");
        photoUrls.put(photoUrl);

        JSONArray tagsArray = requestBody.getJSONArray("tags");
        JSONObject newTag = new JSONObject();
        newTag.put("id",tagId);
        newTag.put("name",tagName);

        requestBody.put("status",status);

        writeJsonToFile(requestBody, jsonFileBody);
    }
}
