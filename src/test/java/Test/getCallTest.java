package Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BaseClass.Testbase;
import client.RestClient;

public class getCallTest extends Testbase{
	
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
	public void getAPIwithHeader() throws ClientProtocolException, IOException
	{
		restClient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("content-type","application/json");
		closeableHttpresponse=restClient.get(url1,headerMap);
		
		int StatusCode=closeableHttpresponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode--> "+StatusCode);
		
		String httsresponse=EntityUtils.toString(closeableHttpresponse.getEntity(),"UTF-8");
		JSONObject jsonobj=new JSONObject(httsresponse);
		System.out.println("JSON Response---> "+jsonobj);
		
		Header[] allheader =closeableHttpresponse.getAllHeaders();
		HashMap<String, String> headermap1=new HashMap<String, String>();
		for(Header header:allheader)
		{
			headermap1.put(header.getName(), header.getValue());
		}
		System.out.println("all haeder info--->  " +headermap1);
		
		

		
		
		
		
		
	}

}
