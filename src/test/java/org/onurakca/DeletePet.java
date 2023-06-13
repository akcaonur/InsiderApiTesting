package org.onurakca;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePet extends BaseTest {

    Object petId = 123123123;

    @Test
    public void delete() {
        Response response = given(spec)
                .when()
                .contentType("application/json")
                .pathParams("petId", petId)
                .delete("/pet/{petId}");

        response.then().statusCode(200);
        Assertions.assertEquals(response.path("message"), String.valueOf(petId));
        response.prettyPrint();
    }

    @Test
    public void deleteNegative(){
        try {
            given(spec)
                    .when()
                    .contentType("application/json")
                    .pathParams("petId",23)
                    .delete("/pet/petId");
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

}
