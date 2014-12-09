package com.wt.studio.plugin.tools.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class HttpClientUtil {
	protected static HttpClient httpclient = null;
	protected static int maxTotal = 200;
	protected static int maxPerRoute = 20;
	protected static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7";

	static {
		if (httpclient == null) {
			SchemeRegistry reg = new SchemeRegistry();
			reg.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			reg.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
					reg);
			cm.setMaxTotal(maxTotal);
			cm.setDefaultMaxPerRoute(maxPerRoute);
			httpclient = new DefaultHttpClient(cm);
		}
	}

	/**
	 * <pre>
	 * 下載後回傳Inputstream
	 * </pre>
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static InputStream downloadAsStream(String url) throws Exception {
		InputStream is = (InputStream) download(url, null, null, false);
		return is;
	}

	/**
	 * <pre>
	 * 下載後儲存到File
	 * </pre>
	 * 
	 * @param url
	 * @param saveFile
	 * @throws Exception
	 */
	public static void download(String url, File saveFile) throws Exception {
		download(url, saveFile, null, false);
	}

	/**
	 * <pre>
	 * 下載
	 * </pre>
	 * @param url
	 * @param saveFile
	 * @param params
	 * @param isPost
	 * @return 如果saveFile==null則回傳inputstream, 否則回傳saveFile
	 * @throws Exception
	 */
	public static Object download(final String url, final File saveFile,
			final Map<String, String> params, final boolean isPost)
			throws Exception {

		boolean saveToFile = saveFile != null;

		if (saveToFile && saveFile.getParentFile().exists() == false) {
			saveFile.getParentFile().mkdirs();
		}

		Exception err = null;
		HttpRequestBase request = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		FileOutputStream fos = null;
		Object result = null;

		try {
			if (isPost) {
				request = new HttpPost(url);
			} else {
				request = new HttpGet(url);
			}
			addHeaderAndParams(request, params);
			response = httpclient.execute(request);
			entity = response.getEntity();
			entity = new BufferedHttpEntity(entity);

			if (saveToFile) {
				fos = new FileOutputStream(saveFile);
				IOUtils.copy(entity.getContent(), fos);
				result = saveFile;
			} else {
				result = new BufferedInputStream(entity.getContent());
			}

		} catch (Exception e) {
			err = e;
		} finally {
			IOUtils.closeQuietly(fos);
			request = null;
			response = null;
			entity = null;
			if (err != null) {
				throw err;
			}
			return result;
		}
	}

	protected static void addHeaderAndParams(final HttpRequestBase request,
			final Map<String, String> params) {
		request.addHeader("User-Agent", userAgent);
		if (params != null) {
			BasicHttpParams myParams = new BasicHttpParams();
			for (String key : params.keySet()) {
				myParams.setParameter(key, params.get(key));
			}
			request.setParams(myParams);
		}
	}
	/**
	 * 
	 * 方法说明：get方法函数
	 *
	 * @param url
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
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
	
	/**
	 * 
	 * 方法说明：post表单方法函数
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String httpPostForm(String url,Map params) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	    Iterator iter = params.entrySet().iterator();
	       while(iter.hasNext()){
	    	    Map.Entry entry=(Map.Entry)iter.next();
	            Object key=entry.getKey();
	    	    Object val=entry.getValue();
	    	    if(!(key.equals("")||val.equals("")))
	    	             nvps.add(new BasicNameValuePair(key.toString(), val.toString()));
	    	   }
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    InputStream instream = entity2.getContent();
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
		    try {
				EntityUtils.consume(entity2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return res;
		} finally {
		    response2.close();
		}
	}
	/**
	 * 
	 * 方法说明：Post多媒体方法函数
	 *
	 * @param url
	 * @param file
	 * @param text
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPostMulti(String url,Map file,Map text) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder.create();
            if(file!=null)
            {
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	    Iterator iter = file.entrySet().iterator();
    	       while(iter.hasNext()){
    	    	    Map.Entry entry=(Map.Entry)iter.next();
    	            Object key=entry.getKey();
    	    	    Object val=entry.getValue();
    	    	    	FileBody bin = new FileBody(new File(val.toString()));
    	    	        reqEntityBuilder.addPart(key.toString(), bin);
    	    	   }
            }
            if(text!=null)
            {
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	    Iterator iter = text.entrySet().iterator();
    	       while(iter.hasNext()){
    	    	    Map.Entry entry=(Map.Entry)iter.next();
    	            Object key=entry.getKey();
    	    	    Object val=entry.getValue();
    	    	    StringBody comment = new StringBody(val.toString(), ContentType.TEXT_PLAIN);
    	    	    reqEntityBuilder.addPart(key.toString(), comment);
    	    	   }
            }
           
            HttpEntity reqEntity = reqEntityBuilder.build();


            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                InputStream instream = resEntity.getContent();
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
                EntityUtils.consume(resEntity);
                return res;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
	}
	/**
	 * 
	 * 方法说明：Post Raw方式
	 *
	 * @param url
	 * @param Text
	 * @param flag
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPostRaw(String url,String Text,int flag) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try{
			
		  HttpPost httpPost = new HttpPost(url);

	      StringEntity entity=new StringEntity(Text);
	      httpPost.setEntity(entity);
	      if(flag==0)
	           httpPost.setHeader( "Content-type" , "text/Text; charset=UTF-8" );
	      else if(flag==1)
	           httpPost.setHeader( "Content-type" , "text/JSON; charset=UTF-8" );
	      else if(flag==2)
	           httpPost.setHeader( "Content-type" , "text/XML; charset=UTF-8" );
	      else if(flag==3)
	           httpPost.setHeader( "Content-type" , "text/HTML; charset=UTF-8" );
	      CloseableHttpResponse response = httpclient.execute(httpPost);
	      try{
	      
	      HttpEntity resEntity = response.getEntity();
          InputStream instream = resEntity.getContent();
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
          EntityUtils.consume(resEntity);
          return res;
	      }
	      finally
	      {
	    	 response.close(); 
	      }
		}finally{
			 httpclient.close();
		}
	}
	/**
	 * 
	 * 方法说明：url分解函数，将url中包含的params分解出来。
	 *
	 * @param url
	 * @return
	 */
	public static Map urlSplit(String url){
	    @SuppressWarnings("unchecked")
		Map<String, String> map=new LinkedHashMap();
	    if(url != null)
	    {
	    String url1[]=url.split("\\?");
	    if(!url1[0].equals(url)&&url1.length==2)
	    {
	    String url2[]=url1[1].split("\\&");
	    for(int i=0;i<url2.length;i++)
	    {
	 	   String url3[]=url2[i].split("\\=");
	 	   if(!url3[0].equals(url2[i])&&url3.length!=1)
	 	   {
	 		  map.put(url3[0].toString(), url3[1].toString());
	 	   }
	 	   if(url3.length==1)
	 	   {
	 		   map.put(url3[0].toString(), "");
	 	   }
	 	        
	    }
	    }
	    }
	    return map;
	}
	/**
	 * 
	 * 方法说明：将表格中的参数反解析到url中
	 *
	 * @param table
	 * @return
	 */
	public static String Splice(Table table)
	{   
		StringBuffer buffer=new StringBuffer();
		TableItem [] items=table.getItems();
		int flag=0;
		for(int i=0;i<items.length;i++)
		{
			if(!items[i].getText(0).equals(""))
			{        if(i!=0&flag==1)
				          buffer.append("&");
			         buffer.append(items[i].getText(0));
			         flag=1;
			}
			if(!items[i].getText(1).equals(""))
			{
			buffer.append("=");
			buffer.append(items[i].getText(1));
			}			
		}
		return buffer.toString();
	}
	
	public static HttpClient getHttpclient() {
		return httpclient;
	}

	public static void setHttpclient(HttpClient httpclient) {
		HttpClientUtil.httpclient = httpclient;
	}

	public static int getMaxTotal() {
		return maxTotal;
	}

	public static void setMaxTotal(int maxTotal) {
		HttpClientUtil.maxTotal = maxTotal;
	}

	public static int getMaxPerRoute() {
		return maxPerRoute;
	}

	public static void setMaxPerRoute(int maxPerRoute) {
		HttpClientUtil.maxPerRoute = maxPerRoute;
	}

	public static String getUserAgent() {
		return userAgent;
	}

	public static void setUserAgent(String userAgent) {
		HttpClientUtil.userAgent = userAgent;
	}

}
