package automationStepBystep;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;


import org.testng.annotations.Test;

import com.google.gson.JsonObject;

public class Tutorial6_GETPOST {
	
	//@Test
	public void testGET() {
	
	baseURI = "https://reqres.in/";
	
	given().
		get("/api/users?page=2").
	then().
		statusCode(200).
		body("data[1].first_name", equalTo("Lindsay")).
		body("data.first_name", hasItems("Lindsay", "Tobias"));
		
	}
	
	
	@Test
	public void testPOST() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "Hari");
//		map.put("job", "tester");
//		System.out.println(map);
		
		JSONObject request = new JSONObject();
		request.put("name", "Hari");
		request.put("job", "tester");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			header("x-api-key", "reqres-free-v1").
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).log().all();
		//ending here
		
		
		
		
	}
	
}
