package com.sample.wishlistDemo.utils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.bean.TokenResultBean;

public class HTTPSClientTest {

	@Test
	public void TestPostDocument() {
		HttpClient httpClient = null;

		try {
			httpClient = new HTTPSTrustClient().init();

			String baseUrl = "https://api.beta.yaas.io/hybris/document/v1/";

			/**
			 * Base URL: https://api.beta.yaas.io/hybris Client id:
			 * R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R Client Secret: EEmhYGZXGXPkjBpH
			 * Tenat:caashiring Scope:hybris.document_manage
			 * https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
			 */
			// {"token_type":"Bearer","access_token":"021-ac318fd2-f5d7-441c-a0ae-b48f1edfdbf3","expires_in":3600,
			//  "scope":"hybris.document_manage hybris.tenant=caashiring"}
			String tenant = "caashiring";
			String client = "R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R";
			String type = "comic";
			TokenResultBean tokenBean = TokenUtil.getAccessToken();
			if(tokenBean != null) {
				//https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
				String url = baseUrl + tenant + "/" + client + "/data/" + type +"/";
				Map<String, String> paramHeader = new HashMap<>();
				paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
				paramHeader.put("Content-type", "application/json");
				Map<String, String> paramBody = new HashMap<>();
				
				Wishlist wishlist = new Wishlist();
				wishlist.setDescription("I'm description");
				wishlist.setId("id");
				wishlist.setOwner("owner");
				wishlist.setTitle("title");
				wishlist.setUrl(new URI("uri"));
				
				paramBody.put("data", JSON.toJSONString(wishlist));
				String result = HTTPSClientUtil.doPost(httpClient, url, paramHeader, paramBody);
	
	
				System.out.println(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestGetDocument() {
		HttpClient httpClient = null;

		try {
			httpClient = new HTTPSTrustClient().init();

			String baseUrl = "https://api.beta.yaas.io/hybris/document/v1/";

			/**
			 * Base URL: https://api.beta.yaas.io/hybris Client id:
			 * R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R Client Secret: EEmhYGZXGXPkjBpH
			 * Tenat:caashiring Scope:hybris.document_manage
			 * https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
			 * curl -G -i 'https://api.beta.yaas.io/hybris/document/v1/mycomicsshop/myorg.stock/data/comic/?q=rarity:uncommon'
			 */
			String tenant = "caashiring";
			String client = "R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R";
			String type = "comic";
			TokenResultBean tokenBean = TokenUtil.getAccessToken();
			if(tokenBean != null) {
				//https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
				String url = baseUrl + tenant + "/" + client + "/data/" + type +"/";
				Map<String, String> paramHeader = new HashMap<>();
				paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
				paramHeader.put("Content-type", "application/json");
				Map<String, String> paramBody = new HashMap<>();
				paramBody.put("type", "insufficient_permissions");
				paramBody.put("scope", "hybris.document_manage");

				String result = HTTPSClientUtil.doGet(httpClient, url, paramHeader, paramBody);
	
	
				System.out.println(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * curl -i -H "Content-Type: application/x-www-form-urlencoded" -X POST -d
	 * "grant_type=client_credentials&scope=SCOPE_NAME&client_id=CLIENT_ID&client_secret=CLIENT_SECRET"
	 * https://api.beta.yaas.io/hybris/oauth2/v1/token
	 */
	//@Test
	public void TestToken() {
		HttpClient httpClient = null;

		try {
			httpClient = new HTTPSTrustClient().init();

			String baseUrl;

			baseUrl = "https://api.beta.yaas.io/hybris/oauth2/v1/token";

			// -d
			// "grant_type=client_credentials&scope=SCOPE_NAME&client_id=CLIENT_ID&client_secret=CLIENT_SECRET"
			Map<String, String> paramHeader = new HashMap<>();
			paramHeader.put("Content-Type", "application/x-www-form-urlencoded");
			Map<String, String> paramBody = new HashMap<>();
			paramBody.put("grant_type", "client_credentials");
			paramBody.put("scope", "hybris.document_manage");
			paramBody.put("client_id", "R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R");
			paramBody.put("client_secret", "EEmhYGZXGXPkjBpH");
			String result = HTTPSClientUtil.doPost(httpClient, baseUrl, paramHeader, paramBody);

			TokenResultBean bean = (TokenResultBean) JSON.parseObject(result, TokenResultBean.class);
			// {"token_type":"Bearer","access_token":"021-ac318fd2-f5d7-441c-a0ae-b48f1edfdbf3","expires_in":3600,"scope":"hybris.document_manage
			// hybris.tenant=caashiring"}

			System.out.println(result);
			System.out.println(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestAll() {
		HttpClient httpClient = null;

		try {
			httpClient = new HTTPSTrustClient().init();

			String baseUrl;

			baseUrl = "https://api.beta.yaas.io/hybris/document/v1/statistics/total";
			
			TokenResultBean tokenBean = TokenUtil.getAccessToken();
			Map<String, String> paramHeader = new HashMap<>();
			paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
			paramHeader.put("Content-type", "application/json");
			Map<String, String> paramBody = new HashMap<>();
			String result = HTTPSClientUtil.doPost(httpClient, baseUrl, paramHeader, paramBody);

			TokenResultBean bean = (TokenResultBean) JSON.parseObject(result, TokenResultBean.class);
			// {"token_type":"Bearer","access_token":"021-ac318fd2-f5d7-441c-a0ae-b48f1edfdbf3","expires_in":3600,"scope":"hybris.document_manage
			// hybris.tenant=caashiring"}

			System.out.println(result);
			System.out.println(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
