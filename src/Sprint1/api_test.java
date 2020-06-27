package Sprint1;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class api_test {
	
	
	public void tc01_GET() throws JsonParseException, JsonMappingException, IOException {
		
		// Set the endpoint
		RestAssured.baseURI="https://reqres.in/api/users";
		
		// Set the Request JSON
		RequestSpecification httpRequest = RestAssured.given();
		
		// Invoke the service
		Response response = httpRequest.request(Method.GET,"/2");
		
		//priting response inn console
		System.out.println(response.getBody().asString());
		
		//validating statuscode
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//JSON parsing or deserialization 
		
		ObjectMapper mapper  = new ObjectMapper();
		Res c= mapper.readValue(response.asString(),Res.class );
		
		System.out.println("Email is " + c.data.email);
		System.out.println("First name is " +c.data.first_name);
		System.out.println("Last Name is " + c.data.last_name);
		System.out.println("Comany name is " + c.ad.company);
		System.out.println("url is " + c.ad.url);
		
		Assert.assertEquals(c.data.email, "janet.weaver@reqres.in");
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Res{

		public v data;
		public companydetails ad;
		}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class v{

		  public String id;
		  public String email;
		  public String first_name;
		  public String last_name;
		  public String avatar;
		}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class companydetails{

		public String company;
		public String url;
		public String text;
		}




@Test
public void tc02_POST() throws JsonParseException, JsonMappingException, IOException {
	
	// Set the endpoint
	RestAssured.baseURI="https://reqres.in/api/users";
	
	// Set the Request JSON
	RequestSpecification httpRequest = RestAssured.given();
	
	//request payload sending along with post request
	httpRequest.header("Content-Type","application/json");	
	JSONObject requestparams = new JSONObject();
	requestparams.put("name", "nitesh123455");
	requestparams.put("job", "QA");
	
	//Add json to body of request
	httpRequest.body(requestparams.toJSONString());
	
	//post the request
	Response response= httpRequest.request(Method.POST, "");
	
	//check the response
	System.out.println("Complete response is "+ response.asString());
	
	ObjectMapper mapper = new ObjectMapper();
	putresponse res1 = mapper.readValue(response.asString(),putresponse.class );
	
	System.out.println("Name is "+ res1.name);
	
	Assert.assertEquals(res1.name, "nitesh123455");

}


@JsonIgnoreProperties(ignoreUnknown=true)
public static class putresponse{
	  public String name;
	  public String job;
	  public String id;
	  public String createdAt;

	}

}
