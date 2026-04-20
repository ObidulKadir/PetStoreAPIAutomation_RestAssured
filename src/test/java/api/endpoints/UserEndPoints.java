package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

// user end points for create, read,update and delete.

public class UserEndPoints {

//	1. The "Create User" Button (POST)
	public static Response createUser(User payload) {
		Response response = given().contentType("application/json") // we are sending json "forms"
				.accept("application/json") // We want the computer to talk back in JSON
				.body(payload) // here is user form we filled out.

				.when().post(Routes.post_url); // loop up the address in out address book (Routes)

		return response;
	}

//	2. The "Read User" Button (POST)
	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName).when().get(Routes.get_url); // loop up the address in out address book (Routes)

		return response;
	}

//	3. The "update User" Button (POST)
	public static Response updateUser(String userName, User payload) {
		Response response = given().contentType("application/json") // we are sending json "forms"
				.accept("application/json") // We want the computer to talk back in JSON
				.body(payload) // here is user form we filled out.
				.pathParam("username", userName)

				.when().put(Routes.update_url); // loop up the address in out address book (Routes)

		return response;
	}

	// 4. The "Delete User" Button (DELETE) [20]
	public static Response deleteUser(String username) {
		Response response = given().pathParam("username", username) // Tell the computer who to remove [20]
				.when().delete(Routes.delete_url); // Go to the "Delete" address [20, 21]

		return response;
	}
}
