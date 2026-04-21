package api.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import org.apache.logging.log4j.LogManager;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker ;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug(".....Debugging........");

	}
	
	@Test(priority =1)
	public void testPostUser() {
		
		logger.info("************* Creating User ***************");
		Response response =  UserEndPoints.createUser(userPayload);
		response.then().log().all();
		logger.info("************* User is created ***************");
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority =2)
	public void testGetUser() {
		
		logger.info("************* Reading User info ***************");
		
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("************* Reading User info displayed ***************");
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		
		logger.info("************* Updating  User info ***************");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		logger.info("************* User is updated ***************");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//response after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority =4)
	public void testDeleteUser() {
		logger.info("************* Deleting  User info ***************");
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User is deleted. ***************");
	}

}
