package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests2 {
	

	/*
	 * Purpose of this class it will read the url from UserEndPoints 2 class that is taking the urls from the Routes.properties file. 
	 * 
	 * 
	 */
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String username, String fname, String lname, String useremail, String pwd, String ph) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
//		Response response = UserEndPoints.createUser(userPayload);
		Response response = UserEndPoints2.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}

	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUserbyName(String username) {
		
		Response response = UserEndPoints2.readUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testUpdateUserbyName(String username) {
		
		User userPayload = new User();
		Faker faker = new Faker();
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(username,userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserbyName(String username) {
		
		Response response = UserEndPoints2.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
