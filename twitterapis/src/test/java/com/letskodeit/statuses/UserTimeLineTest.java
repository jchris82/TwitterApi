package com.letskodeit.statuses;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.google.common.hash.Hashing;
import com.letskodeit.common.RestUtilities;
import com.letskodeit.constants.EndPoints;
import com.letskodeit.constants.Path;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UserTimeLineTest {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.queryParam("user_id", "apiautomation");
		reqSpec.basePath(Path.STATUSES);
		
		resSpec = RestUtilities.getResponseSpecification();
	}
	
	@Test
	public void readTweets1() {
	    given()
   		    .spec(RestUtilities.createQueryParam(reqSpec,"count","1"))
       .when()
  	        .get(EndPoints.STATUSES_USER_TIMELINE)
       .then()
            //.log().all()
  	        .spec(resSpec)
	        .body("user.screen_name", hasItem("jchristian821"));
	}
	
	@Test
	public void readTweets2() {
		RestUtilities.setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
		Response res = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec,"count","1"), "get");
		ArrayList<String> ScreenNameList = res.path("user.screen_name");
		System.out.println("Read Tweets 2 Method");
		System.out.println(ScreenNameList);
		Assert.assertTrue(ScreenNameList.contains("jchristian821"));
		
	}
}
