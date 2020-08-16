package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import BaseClass.Testbase;

public class RestClient{
	
	//Get method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);// https get request 		
		CloseableHttpResponse closeableHttpresponse =httpClient.execute(httpget);// Hit the get URL
		return closeableHttpresponse;		
	}
	
	//Get with Header.
	public CloseableHttpResponse get(String url,HashMap<String,String> headermap) throws ClientProtocolException, IOException
	{
	   	CloseableHttpClient httpClient= HttpClients.createDefault();
	   	HttpGet httpget=new HttpGet(url);// https get request.
	   	
	   	for(Map.Entry<String, String> entry: headermap.entrySet())
	   	{
	   		httpget.addHeader(entry.getKey(), entry.getValue());
	   	}	   		
	   	CloseableHttpResponse closeableHttpresponse=httpClient.execute(httpget);
	   	return closeableHttpresponse;
	   	
	}
	
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString));
		for(Map.Entry<String, String> entry :headermap.entrySet())
		{
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closablehttpresponse=httpclient.execute(httpPost);
		
		return closablehttpresponse;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 
}
