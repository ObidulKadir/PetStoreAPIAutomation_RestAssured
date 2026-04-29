package api.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import api.payload.User;
import io.restassured.response.Response;

public class PetTests {
	Faker faker;
	Pet petPayload;
	public Logger logger;
	long id;

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		petPayload = new Pet();
		
		// pet class element
		petPayload.setId(faker.number().numberBetween(1000, 99999));
		petPayload.setName(faker.dog().name());
		petPayload.setStatus("available");
		
		// Set Category (Object)
		Category category = new Category();
		category.setId(faker.number().numberBetween(1000, 99999));
		category.setName(faker.animal().name());
		petPayload.setCategory(category);

		// Set Photo URLs (List of Strings)
		List<String > photoUrls = new ArrayList<>();
		photoUrls.add(faker.internet().url());
		petPayload.setPhotoUrls(photoUrls);
		
		// set tag (List of Object)
		Tag tag = new Tag();
		tag.setId(faker.number().randomDigit());
	    tag.setName("tag-name-" + faker.lorem().word());
	    
	    List<Tag> tags = new ArrayList<>();
	    tags.add(tag);
	    
	    petPayload.setTags(tags);
		

	}
	
	@Test(priority = 1)
    public void testPostPet() {
        // Calling the static method from PetEndPoints
        Response response = PetEndPoints.createPet(petPayload);
        
        // Log the response for debugging
        response.then().log().all();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
        
        id = response.jsonPath().getLong("id");
        System.out.println("After post the id is "+id);
    }

    @Test(priority = 2)
    public void testGetPetById() {
        // Use the ID from the payload we created in setup
        Response response = PetEndPoints.readPet(id);
        
        response.then().log().all();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), petPayload.getId());
    }
    @Test(priority = 3)
    public void testUpdatePet() {
        petPayload.setName(faker.animal().name());
        petPayload.setStatus("available");

        Response response = PetEndPoints.updatePet(id, petPayload);        
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        
        response.then().body("message", org.hamcrest.Matchers.equalTo(String.valueOf(id)));

}
}
