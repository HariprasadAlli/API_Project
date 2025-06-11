package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPSRequests_ChatGPT {
	
	String id; // to store dynamic ID from POST response

	@Test(priority = 1)
	void getUser() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}	

	@Test(priority = 2)
	void CreateUser() {
		HashMap<String, String> data = new HashMap();
		data.put("name", "Hari");
		data.put("job", "Tester");
				
		id = given()
				.contentType("application/json")
				.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all()
			.extract().jsonPath().getString("id");  // get ID from response
	}
	
	@Test(priority = 3, dependsOnMethods = {"CreateUser"})
	void updateUser() {
		HashMap<String, String> data = new HashMap();
		data.put("name", "Hari_Alli");
		data.put("job", "Tester_sdet");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(200)
			.log().all();
	}

	@Test(priority = 4, dependsOnMethods = {"CreateUser"})
	void deleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(204)
			.log().all();
	}
}
