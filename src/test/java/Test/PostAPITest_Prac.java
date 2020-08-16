package Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseClass.Testbase;
import Resource.User;
import client.RestClientTest;
import junit.framework.Assert;

public class PostAPITest_Prac extends Testbase {
	
	public static Testbase base;
	public static RestClientTest restclient;
	String url="";
	
	@BeforeTest
	public void setup()
	{
		base=new Testbase();
		url=prop.getProperty("url")+prop.getProperty("serviceurl");
		System.out.println(url);
	}
	
	@Test
	public void post_validation() throws JsonGenerationException, JsonMappingException, IOException
	{
	   	HashMap<String,String> headermap=new HashMap<String,String>();
	   	headermap.put("content-type","application/json");
	   	ObjectMapper mapper= new ObjectMapper();
	   	User user=new User("Shyam","Manager");
	   	
	    //Object to JSON Conversion
	    mapper.writeValue(new File("C:\\Users\\Ratnadip Ghosh\\Documents\\TJXAutomation\\restAPI\\src\\main\\java\\Resource\\User.json"), user);
	   	
	    String json_Payload=mapper.writeValueAsString(user);
	   	System.out.println("JsonPayload-->"+json_Payload);
	   	restclient=new RestClientTest(); 
	   	CloseableHttpResponse httpresponse =restclient.post(url, headermap, json_Payload);
	   	
	   	int Status_Code=httpresponse.getStatusLine().getStatusCode();
	   	String responsestr=EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
	   	JSONObject jsonobj=new JSONObject(responsestr);
	   	System.out.println(jsonobj);
	   	User userresponseObj=mapper.readValue(responsestr, User.class);
	   	System.out.println(userresponseObj);
	   	
	   	System.out.println(user.getName()+"     "+userresponseObj.getName());
	   	
	  /*Assert.assertTrue(user.getName().equalsIgnoreCase(userresponseObj.getName()));
	    Assert.assertTrue(user.getJob().equalsIgnoreCase(userresponseObj.getJob()));*/
	    
	    System.out.println("User Id-->"+userresponseObj.getId());
	    System.out.println("Created date-->"+userresponseObj.getCreatedAt());
	}

}
