package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

// user end points for create, read,update and delete.

public class UserEndPoints2 {

	// method created for getting URL's from properties file.
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // load properties file // name of the properties
																	// file (routes. properties)
		return routes;
	}

//	1. The "Create User" Button (POST)
	public static Response createUser(User payload) {
		String post_url = getUrl().getString("post_url");
		Response response = given().contentType("application/json") // we are sending json "forms"
				.accept("application/json") // We want the computer to talk back in JSON
				.body(payload) // here is user form we filled out.

				.when().post(post_url); // loop up the address in out address book (Routes)

		return response;
	}

//	2. The "Read User" Button (POST)
	public static Response readUser(String userName) {
		String get_url = getUrl().getString("get_url");

		Response response = given().pathParam("username", userName).when().get(get_url); // loop up the address in out
																							// address book (Routes)

		return response;
	}

//	3. The "update User" Button (POST)
	public static Response updateUser(String userName, User payload) {

		String update_url = getUrl().getString("update_url");

		Response response = given().contentType("application/json") // we are sending json "forms"
				.accept("application/json") // We want the computer to talk back in JSON
				.body(payload) // here is user form we filled out.
				.pathParam("username", userName)

				.when().put(update_url); // loop up the address in out address book (Routes)

		return response;
	}

	// 4. The "Delete User" Button (DELETE)
	public static Response deleteUser(String username) {
		String delete_url = getUrl().getString("delete_url");

		Response response = given().pathParam("username", username) // Tell the computer who to remove.
				.when().delete(delete_url); // Go to the "Delete" address.

		return response;
	}
}
