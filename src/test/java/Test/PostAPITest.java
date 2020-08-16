package Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseClass.Testbase;
import Resource.User;
import Utils_Package.TestUtils;
import client.RestClient;

public class PostAPITest extends Testbase  {
	
	Testbase testbase;
	RestClient restClient;
	String url1="";
	String url_base="";
	String service_url="";
	
	CloseableHttpResponse closeableHttpresponse;
	
	@BeforeTest
	public void setup()
	{
		testbase=new Testbase();
		url_base=prop.getProperty("url");
	    service_url=prop.getProperty("serviceurl");
		url1=url_base+service_url;		
	}
	
	@Test
	public void PostAPICall() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient=new RestClient();
		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("content-type","application/json");
		
		//Jackson API
		ObjectMapper mapper=new ObjectMapper();
		User user=new User("morpheus","leader");
		
		//Object to JSON Conversion
		mapper.writeValue(new File("C:\\Users\\Ratnadip Ghosh\\Documents\\TJXAutomation\\restAPI\\src\\main\\java\\Resource\\User.json"), user);
		
		//Object to Json String
		String jsonmsg=mapper.writeValueAsString(user);
		System.out.println(jsonmsg);
	
		CloseableHttpResponse httpresponse=restClient.post(url1, jsonmsg, headerMap);
		int status_code=httpresponse.getStatusLine().getStatusCode();
		System.out.println("status_code->> "+status_code);
		Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_201,"Status code is not matched");
		
		//Json String
		String entityresponse =EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		JSONObject jsonObj=new JSONObject(entityresponse);		
		System.out.println("Json Object "+jsonObj);
		
		Header[] header=httpresponse.getAllHeaders();
		HashMap<String,String> AllHeader=new HashMap<String,String>();
		for(Header haeds:header)
		{
			AllHeader.put(haeds.getName(), haeds.getValue());
		}
		
		System.out.println(AllHeader);
		
		User userResponseobj=mapper.readValue(entityresponse,User.class);
		System.out.println(userResponseobj);
		
		Assert.assertTrue(user.getName().equalsIgnoreCase(userResponseobj.getName()));
		Assert.assertTrue(user.getJob().equalsIgnoreCase(userResponseobj.getJob()));
		
		System.out.println(userResponseobj.getName());
		System.out.println(userResponseobj.getJob());
		
		
		
		

		
		
		
		
		
		
		
	}

}