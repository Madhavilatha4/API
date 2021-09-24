package testcases;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import static org.hamcrest.Matchers.equalTo;
public class VideoGameAPI {

	@Test(priority=1)
	public void test_getAllVideoGame() {
		given().when().
		get("http://localhost:8080/app/videogames").then()
		.statusCode(200);
	}

	@Test(priority=2)
	public void test_addnewVideoGame() {
		JSONObject data=new JSONObject();
		data.put("id","35");
		data.put("name","Spider-Man");
		data.put("releaseDate","2021-09-24T02:02:32.948Z");
		data.put("reviewScore","0");
		data.put("category","string");
		data.put("rating","string");
		Response res =		
				given().
				contentType("application/json")
				.body(data).when().
		post("http://localhost:8080/app/videogames").then().
		statusCode(200).log().body()
		.extract().response();
		String jsonSring = res.asString();
		Assert.assertEquals(jsonSring.contains("Record Added Successfully"),true);
	}

	@Test(priority=3)
	public void test_getVideoGame() {
		given().when().
		get("http://localhost:8080/app/videogames/35").then()
		.statusCode(200).body("videoGame.id",equalTo("35"))
		.body("videoGame.name", equalTo("Spider-Man"));
	}
	@Test(priority=4)
		public void test_updatenewVideoGame() {
			JSONObject data=new JSONObject();
			data.put("id","35");
			data.put("name","Super-Man");
			data.put("releaseDate","2021-09-24T02:02:32.948Z");
			data.put("reviewScore","0");
			data.put("category","game");
			data.put("rating","string");
				
					given().headers("content-type","application/json").
					contentType(ContentType.JSON)
					.body(data).when().
			put("http://localhost:8080/app/videogames/35").then().
			statusCode(200).log().body()
			.body("videoGame.id",equalTo("35"))
			.body("videoGame.name", equalTo("Super-Man"));
		}
	@Test(priority=5)
	public void test_deletenewVideoGame() throws InterruptedException {
	Response res =		
			given().
			when().
	delete("http://localhost:8080/app/videogames/35").then().
	statusCode(200).log().body()
	.extract().response(); 
	Thread.sleep(3000);
	String jsonSring = res.asString();
	Assert.assertEquals(jsonSring.contains("Record Deleted Successfully"),true);}
}
