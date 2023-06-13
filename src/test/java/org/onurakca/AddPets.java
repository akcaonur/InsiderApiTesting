package org.onurakca;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddPets extends BaseTest{

    @Test
    public void addNewPetToStore(){

        updateRequestJson(123123123,0,"String","köpek","string",123,"name","available");


       Response response = given(spec)
                .when()
                .accept("application/json")
                .contentType("application/json")
                .body(jsonFileBody)
                .post("/pet");

       response
               .then()
               .statusCode(200);

        Assertions.assertEquals(123123123,(Integer) response.jsonPath().getJsonObject("id"));
        Assertions.assertEquals("köpek",response.jsonPath().getJsonObject("name"));

    }

    @Test
    public void addNewPetToStoreNoData(){
        updateRequestJson(123,0,"String","köpek","string",0,"name","abc");

        Response response = given(spec)
                .when()
                .accept("application/json")
                .contentType("application/json")
                .post("/pet");

        response
                .then()
                .statusCode(405);// no data

    }



}
