package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Store;
import io.restassured.response.Response;

// Store endpoints for crude operations.

public class StoreEndPoints {
	
	
//	1. Get the inventory details 
	public static Response readInventory() {
		
		Response  response = given().accept("application/json")
				.when()
					.get(Routes.getInventory_URL);
					
				return response;
	}
	
//	2. create an order
	public static Response createOrder(Store storePayload) {
		
		Response response = given().contentType("aplication/json")
				.accept("application/json")
				.body(storePayload)
				
				.when()
					.post(Routes.postOrder_URL);
		
		return response;
	}
//	3. read the order
	public static Response readOrder(long orderId) {
		
		Response  response = given().accept("application/json")
				.pathParam("orderId", orderId)
				.when()
					.get(Routes.getOrder_URL);
					
				return response;
	}
	
//	4. delete an order 

	public static Response deleteOrder(long orderId) {
		
		Response response = given().contentType("aplication/json")
				.accept("application/json")
				.pathParam("orderId", orderId)
				
				.when()
					.delete(Routes.DeleteOrder_URL);
		
		return response;
	}
}
