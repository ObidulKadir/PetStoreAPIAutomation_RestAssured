package api.endpoints;

/*
 * Swagger URI ----> https://petstore.swagger.io
 * 
 create user (post): https://petstore.swagger.io/v2/user
 Get User (get) : https://petstore.swagger.io/v2/user/{username}
 update User (get) : https://petstore.swagger.io/v2/user/{username}
 delete User (get) : https://petstore.swagger.io/v2/user/{username}
 * 
 */

public class Routes {
	
	public static  String base_url ="https://petstore.swagger.io/v2";
	
	//user module
	public static  String post_url = base_url +"/user";
	public static  String get_url = base_url +"/user/{username}";
	public static  String update_url = base_url +"/user/{username}";
	public static  String delete_url = base_url +"/user/{username}";
	
	// store module
	public static String getInventory_URL = base_url + "/store/inventory";
	public static String postOrder_URL = base_url + "/store/order";
	public static String getOrder_URL = base_url + "/store/order/{orderId}";
	public static String DeleteOrder_URL = base_url + "/store/{orderId}";

	
	
}
