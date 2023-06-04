package org.onurakca;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddUpdatePets extends BaseTest{

    @Test
    public void addNewPetToStore(){
        /*
   curl -X 'POST' \
  'https://petstore.swagger.io/v2/pet' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 3,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "köpek",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}'*/

       Response response = given(spec)
                .when()
                .accept("application/json")
                .contentType("application/json")
                .body(AddPetToStore)
                .post("/pet");

       response
               .then()
               .statusCode(200);

        Assertions.assertEquals(3, (Integer) response.jsonPath().getJsonObject("id"));
        Assertions.assertEquals("köpek",response.jsonPath().getJsonObject("name"));



    }
}
