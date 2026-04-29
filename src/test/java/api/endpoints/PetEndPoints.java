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
	    Response response = given()
	            .contentType("application/json") 
	            .accept("application/json")
	            .pathParam("petId", petId) // Sets the variable for the URL
	            .body(payload)             // Sets the JSON data
	    .when()
	            .post(Routes.updatePet_url); // The action comes LAST

	    return response;
	}


}
