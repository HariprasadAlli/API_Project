package automationStepBystep;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FirstTest {

	
	@Test
	public void test1() {
		Response response = get("http://localhost:3000/students?grade=10th");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200, "failed");
	}
	
	@Test
	public void test2() {
		baseURI = "http://localhost:3000/";
		given().get("students?grade=10th").then().statusCode(200)
		.body("[0].name", equalTo("David Johnson"));
	}
}
