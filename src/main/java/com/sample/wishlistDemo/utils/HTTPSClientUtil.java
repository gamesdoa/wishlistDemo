package com.sample.wishlistDemo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;  
  
public class HTTPSClientUtil {  
    private static final String DEFAULT_CHARSET = "UTF-8";  
  
    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,  
            Map<String, String> paramBody) throws Exception {  
        return doPost(httpClient, url, paramHeader, paramBody, DEFAULT_CHARSET);  
    }  
  
    public static String doPost(HttpClient httpClient, String url, Map<String, String> paramHeader,  
            Map<String, String> paramBody, String charset) throws Exception {  
  
        String result = null;  
        HttpPost httpPost = new HttpPost(url);  
        setHeader(httpPost, paramHeader);
        setBody(httpPost, paramBody, charset);
       
  
        HttpResponse response = httpClient.execute(httpPost);  
        if (response != null) {  
            HttpEntity resEntity = response.getEntity();  
            if (resEntity != null) {  
                result = EntityUtils.toString(resEntity, charset);  
            }  
        }  
  
        return result;  
    }  
      
    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               String stringJson) throws Exception {
        return doGet(httpClient, url, paramHeader, stringJson, DEFAULT_CHARSET);
    }  
  
    public static String doGet(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               String stringJson,  String charset) throws Exception {
  
        String result = null;  
        HttpGet httpGet = new HttpGet(url + "?" + stringJson);
        setHeader(httpGet, paramHeader);  

        HttpResponse response = httpClient.execute(httpGet);  
        if (response != null) {  
            HttpEntity resEntity = response.getEntity();  
            if (resEntity != null) {  
                result = EntityUtils.toString(resEntity, charset);  
            }  
        }  
  
        return result;  
    }

    public static String doPut(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               String stringJson) throws Exception {
        return doPut(httpClient, url, paramHeader, stringJson, DEFAULT_CHARSET);
    }

    public static String doPut(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               String stringJson, String charset) throws Exception {

        String result = null;
        HttpPut httpPut = new HttpPut(url);
        setHeader(httpPut, paramHeader);
        setBody(httpPut, stringJson, charset);

        HttpResponse response = httpClient.execute(httpPut);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }

    public static String doDelete(HttpClient httpClient, String url, Map<String, String> paramHeader) throws Exception {
        return doDelete(httpClient, url, paramHeader, DEFAULT_CHARSET);
    }

    public static String doDelete(HttpClient httpClient, String url, Map<String, String> paramHeader,
                               String charset) throws Exception {

        String result = null;
        HttpDelete httpDelete = new HttpDelete(url);
        setHeader(httpDelete, paramHeader);

        HttpResponse response = httpClient.execute(httpDelete);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }


    private static void setHeader(HttpRequestBase request, Map<String, String> paramHeader) {  
        // 设置Header  
        if (paramHeader != null) {  
            Set<String> keySet = paramHeader.keySet();  
            for (String key : keySet) {  
                request.addHeader(key, paramHeader.get(key));  
            }
            request.setHeader("Accept", "application/json"); 
        }  
    }  
  
    private static void setBody(HttpPost httpPost, Map<String, String> paramBody, String charset) throws Exception {
        // 设置参数  
        if (paramBody != null) {  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Set<String> keySet = paramBody.keySet();
            for (String key : keySet) {
                list.add(new BasicNameValuePair(key, paramBody.get(key)));  
            }  
  
            if (list.size() > 0) {  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
    	    	entity.setContentType("application/json"); 
                httpPost.setEntity(entity);  
            } 
        }  
    }

    private static void setBody(HttpPut httpPut, String stringJson, String charset) throws Exception {
        // 设置参数
        if (StringUtils.isNotEmpty(stringJson)) {
            StringEntity stringEntity = new StringEntity(stringJson, charset);
            httpPut.setEntity(stringEntity);
        }
    }
}  