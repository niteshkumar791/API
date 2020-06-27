package Sprint1;

import java.io.IOException;

import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_2 {
	
	@Test
	public void TC02() throws JsonParseException, JsonMappingException, IOException {
		
		// Set the endpoint
		RestAssured.baseURI="https://reqres.in/api/users";
				
		// Set the Request JSON
		RequestSpecification httpRequest = RestAssured.given();
				
		// Invoke the service
		Response response = httpRequest.request(Method.GET,"?page=2");
				
		System.out.println(response.getBody().asString());
		
		//JSON parsing or deserialization 
		
		ObjectMapper mapper  = new ObjectMapper();
		Res c= mapper.readValue(response.asString(),Res.class );
		
		System.out.println("Page is " + c.page);
		System.out.println("Per Page is " + c.per_page);
		System.out.println("Total Page is " + c.total);
		System.out.println("Total Page is " + c.total_pages);
		
		System.out.println("Comany name is " + c.ad.company);
		
		List<v> lst = c.data;
		for(v val:lst) {
			
			System.out.println("First name is " + val.first_name);
			System.out.println("Last name is " + val.last_name);
			
		}
		
	}
     
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Res{
        
		public String page;
		public String per_page;
		public String total;
		public String total_pages;
		
		public List<v> data;
		public companydetails ad;
		}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class companydetails{

		public String company;
		public String url;
		public String text;
		}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class v{

		  public String id;
		  public String email;
		  public String first_name;
		  public String last_name;
		  public String avatar;
		}
	
	
}
