package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import api.payload.User;
import io.restassured.response.Response;

public class PetEndPoints {
	
	//	1. The "Create pet" Button (POST)
	public static Response createPet(Pet payload) {
		Response response = given().contentType("application/json") // we are sending json "forms"
				.accept("application/json") // We want the computer to talk back in JSON
				.body(payload) // here is user form we filled out.

				.when().post(Routes.createPet_url); // loop up the address in out address book (Routes)

		return response;
	}

//	2. The "Read pet" Button 
	public static Response readPet(long id) {
		Response response = given().pathParam("petId", id).when().get(Routes.getPet_url); // loop up the address in out address book (Routes)

		return response;
	}

//	3. The "update pet info" Button (POST)
	public static Response updatePet(long petId, Pet payload) {
	    // We take the data OUT of the payload and send it as Form Params
	    Response response = given()
	            .contentType("application/x-www-form-urlencoded") 
	            .accept("application/json")
	            .pathParam("petId", petId)
	            .formParam("name", payload.getName())    // Extract name from POJO
	            .formParam("status", payload.getStatus()) // Extract status from POJO
	    .when()
	            .post(Routes.updatePet_url);

	    return response;
	}


}
