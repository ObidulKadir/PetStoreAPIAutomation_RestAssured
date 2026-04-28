package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;
import java.util.concurrent.TimeUnit;

public class StoreTests {
    
    Faker faker;
    Store storePayload;
    long orderId;
    
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        storePayload = new Store();

        storePayload.setId(faker.number().numberBetween(1000, 9999)); 
        storePayload.setPetId(faker.number().numberBetween(1, 100));
        storePayload.setQuantity(faker.number().numberBetween(1, 5));
        storePayload.setShipDate(faker.date().future(5, TimeUnit.DAYS).toInstant().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(true);
    }
    
    @Test(priority = 1)
    public void testGetStoreInventory() {
        Response response = StoreEndPoints.readInventory();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 2)
    public void testPostOrder() {
        Response response = StoreEndPoints.createOrder(storePayload);
        response.then().log().all();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        orderId = response.jsonPath().getLong("id");
    }

    @Test(priority = 3, dependsOnMethods = {"testPostOrder"})
    public void testGetOrderById() {
        Response response = StoreEndPoints.readOrder(this.orderId);
        response.then().log().all();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), orderId);
    }
    
    @Test(priority = 4, dependsOnMethods = {"testPostOrder"})
    public void testDeleteOrder() {
        Response response = StoreEndPoints.deleteOrder(this.orderId);
        response.then().log().all();
        
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}