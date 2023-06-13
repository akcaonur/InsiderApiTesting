package org.onurakca;


import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class UpdatePet extends BaseTest{


    @Test
    public void petUpdate(){

        updateRequestJson(123,0,"String","köpek","string",0,"name","abc");

        Response response = given(spec)
                .when()
                .accept("application/json")
                .contentType("application/json")
                .body(jsonFileBody)
                .put("/pet");
        Assertions.assertEquals(response.path("name"), requestBody.get("name"));
        Assertions.assertEquals(response.path("id"), requestBody.get("id"));


    }

    @Test
    public void petUpdateNegative() {
        updateRequestJson(123,0,"String","köpek","string",0,"name","abc");

        try {
            Response response = given(spec)
                    .when()
                    .accept("application/json")
                    .contentType("application/json")
                    .put("/pet");

        }catch (Exception e){
            Assertions.assertEquals("status code: 405, reason phrase: Method Not Allowed",e.getMessage());
        }








    }


}
