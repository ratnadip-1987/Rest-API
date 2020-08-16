package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClientTest {
	
   public void Get(String url) throws ClientProtocolException, IOException	
   {
	  CloseableHttpClient httpClient= HttpClients.createDefault();  
	  HttpGet httpget=new HttpGet(url);
	  CloseableHttpResponse httpsResponse=httpClient.execute(httpget);
	  int statuscode=httpsResponse.getStatusLine().getStatusCode();	  
	  System.out.println("statuscode--> "+statuscode);
	  
	  String Response_Msg= EntityUtils.toString(httpsResponse.getEntity(),"UTF-8");
	  JSONObject jsonObjmsg=new JSONObject(Response_Msg);
	  
	  System.out.println("Json Response "+jsonObjmsg);
	  
	  Header[] headerarray = httpsResponse.getAllHeaders();
	  HashMap<String, String> headerMap=new HashMap<String, String>();
	  for(Header header: headerarray)
	  {
		  headerMap.put(header.getName(), header.getValue());  
	  }
	  System.out.println(headerMap);	  	  
   }
   
   public CloseableHttpResponse post(String url, HashMap<String,String> map, String payload) throws ClientProtocolException, IOException
   {
	   CloseableHttpClient httpClient =HttpClients.createDefault();
	   HttpPost httppost=new HttpPost(url);	   
	   for(Map.Entry<String, String> entry : map.entrySet())
	   {
		   httppost.addHeader(entry.getValue(), entry.getValue());
	   }
	   CloseableHttpResponse httpresponse=httpClient.execute(httppost);	   
	   return httpresponse;
	   
   }

}
