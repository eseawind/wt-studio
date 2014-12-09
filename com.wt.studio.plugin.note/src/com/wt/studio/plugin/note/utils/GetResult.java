package com.wt.studio.plugin.note.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetResult
{
	public static String httpGet(String url) throws IllegalStateException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {	   
		    HttpEntity entity1 = response1.getEntity();
		    InputStream instream = entity1.getContent();
		    BufferedReader bufferedReader = new BufferedReader(  
	                new InputStreamReader(instream));
		    StringBuffer temp = new StringBuffer();
	        String line = bufferedReader.readLine();  
	        while (line != null) {   
	            temp.append(line).append("\r\n");  
	            line = bufferedReader.readLine();  
	        }  
	        bufferedReader.close(); 
	        String res=new String(temp.toString().getBytes(), "UTF-8"); 
	        EntityUtils.consume(entity1);
	        return res;
		} finally {
		    response1.close();
		}
		
	}
}
