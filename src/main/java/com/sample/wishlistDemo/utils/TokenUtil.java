package com.sample.wishlistDemo.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;

import com.alibaba.fastjson.JSON;
import com.sample.wishlistDemo.bean.TokenResultBean;
import com.sample.wishlistDemo.config.SystemConfig;

public class TokenUtil {
	
	/**
	 * curl -i 
	 * -H "Content-Type: application/x-www-form-urlencoded" 
	 * -X POST 
	 * -d "grant_type=client_credentials&scope=SCOPE_NAME&client_id=CLIENT_ID&client_secret=CLIENT_SECRET"
	 *  https://api.beta.yaas.io/hybris/oauth2/v1/token
	 */
	public static TokenResultBean getAccessToken() {
		HttpClient httpClient = null;
		TokenResultBean bean = null;
		try {
			httpClient = new HTTPSTrustClient().init();

			Map<String, String> paramHeader = new HashMap<>();
			paramHeader.put("Content-Type", SystemConfig.tokenContentType);
			Map<String, String> paramBody = new HashMap<>();
			paramBody.put("grant_type", SystemConfig.tokenGrantType);
			paramBody.put("scope", SystemConfig.tokenScope);
			paramBody.put("client_id", SystemConfig.clientId);
			paramBody.put("client_secret", SystemConfig.clientSecret);
			String result = HTTPSClientUtil.doPost(httpClient, SystemConfig.tokenUrl, paramHeader, paramBody);

			bean = (TokenResultBean) JSON.parseObject(result, TokenResultBean.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
}
