package org.onurakca;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class FindPet extends BaseTest{

    @Test
     public void findPetById(){
         given(spec)
                 .when()
                 .accept("application/json")
                 .get("3")
                 .then()
                 .statusCode(200);
     }

     @Test
     public void findPetByStatus(){

        //available pending sold
         spec.queryParam("status","available");
         Response response = given(spec)
                 .when()
                 .accept("application/json")
                 .get("/pet/findByStatus");
         response
                 .then()
                 .statusCode(200);

         List<String> list = response.jsonPath().getList("name");
         System.out.println(list);
         Assertions.assertTrue(list.contains("köpek"));


     }
}
