package Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.Testbase;
import Utils_Package.TestUtils;
import client.RestClient;

public class GetAPITest extends Testbase {
	
	Testbase testbase ;
	RestClient restClient ;
	String serviceurl,apiurl,url1=""; 
	CloseableHttpResponse closeableHttpresponse;
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException
	{
		testbase=new Testbase();
		serviceurl=prop.getProperty("url");
		apiurl=prop.getProperty("serviceurl");		
		url1=serviceurl+apiurl;		
	}
	
	//@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException
	{
		restClient=new RestClient();
		closeableHttpresponse=restClient.get(url1);
		
		//a.Status Code
		int status_code=closeableHttpresponse.getStatusLine().getStatusCode();
        System.out.println(status_code);	
        Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_200,"Not in 200");
        
        //b.JSON String
        String responseString=EntityUtils.toString(closeableHttpresponse.getEntity(),"UTF-8");
        JSONObject responsejson = new JSONObject(responseString);
        System.out.println("responsejson---> "+responsejson);
        
        //per page value validation
        String per_page_value=TestUtils.getValueByJPath(responsejson, "/per_page");
        System.out.println("per_page_value-->"+per_page_value);
        Assert.assertEquals(per_page_value, "6","per page value not matched");
        
        //total_Page validation
        String total_Page=TestUtils.getValueByJPath(responsejson, "/total");
        System.out.println("total_Page-->"+total_Page);
        Assert.assertEquals(total_Page, "12","per page value not matched");
        
        //Data validation from JSON Array
        String lastname =TestUtils.getValueByJPath(responsejson, "/data[0]/last_name");
        System.out.println("lastname-->"+lastname);
        Assert.assertEquals(lastname, "Bluth","lastname not matched");
        
        String id =TestUtils.getValueByJPath(responsejson, "/data[0]/id");
        System.out.println("id-->"+id);
        Assert.assertEquals(id, "1","ID not matched");
        
        String avatar =TestUtils.getValueByJPath(responsejson, "/data[0]/avatar");
        System.out.println("avatar-->"+avatar);
        
        String company =TestUtils.getValueByJPath(responsejson, "/ad/company");
        System.out.println("company-->"+company);
        Assert.assertEquals(company, "StatusCode Weekly","ID not matched");
        
        Header[] headerArray = closeableHttpresponse.getAllHeaders();
        HashMap<String,String> allheaders=new HashMap<String, String>();

        for(Header header: headerArray)
        {
	      allheaders.put(header.getName(),header.getValue()); 	
        }

        System.out.println("all headers--> "+ allheaders);
	}
	
	@Test(priority=2)
	public void getAPITestwithHeader() throws ClientProtocolException, IOException
	{
		restClient=new RestClient();
		
		HashMap<String, String> HeaderMap= new HashMap<String, String>();
		HeaderMap.put("content-type","application/json");
		closeableHttpresponse=restClient.get(url1,HeaderMap);
		
		//a.Status Code
		int status_code=closeableHttpresponse.getStatusLine().getStatusCode();
        System.out.println(status_code);	
        Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_200,"Not in 200");
        
        //b.JSON String
        String responseString=EntityUtils.toString(closeableHttpresponse.getEntity(),"UTF-8");
        JSONObject responsejson = new JSONObject(responseString);
        System.out.println("responsejson---> "+responsejson);
        
        //per page value validation
        String per_page_value=TestUtils.getValueByJPath(responsejson, "/per_page");
        System.out.println("per_page_value-->"+per_page_value);
        Assert.assertEquals(per_page_value, "6","per page value not matched");
        
        //total_Page validation
        String total_Page=TestUtils.getValueByJPath(responsejson, "/total");
        System.out.println("total_Page-->"+total_Page);
        Assert.assertEquals(total_Page, "12","per page value not matched");
        
        //Data validation from JSON Array
        String lastname =TestUtils.getValueByJPath(responsejson, "/data[0]/last_name");
        System.out.println("lastname-->"+lastname);
        Assert.assertEquals(lastname, "Bluth","lastname not matched");
        
        String id =TestUtils.getValueByJPath(responsejson, "/data[0]/id");
        System.out.println("id-->"+id);
        Assert.assertEquals(id, "1","ID not matched");
        
        String avatar =TestUtils.getValueByJPath(responsejson, "/data[0]/avatar");
        System.out.println("avatar-->"+avatar);
        
        String company =TestUtils.getValueByJPath(responsejson, "/ad/company");
        System.out.println("company-->"+company);
        Assert.assertEquals(company, "StatusCode Weekly","ID not matched");
        
        Header[] headerArray = closeableHttpresponse.getAllHeaders();
        HashMap<String,String> allheaders=new HashMap<String, String>();

        for(Header header: headerArray)
        {
	      allheaders.put(header.getName(),header.getValue()); 	
        }

        System.out.println("all headers--> "+ allheaders);
	}

	

}
