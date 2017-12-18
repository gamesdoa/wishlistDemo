package com.sample.wishlistDemo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.bean.ResultBean;
import com.sample.wishlistDemo.bean.TokenResultBean;
import com.sample.wishlistDemo.config.SystemConfig;
import com.sample.wishlistDemo.utils.HTTPSClientUtil;
import com.sample.wishlistDemo.utils.HTTPSTrustClient;
import com.sample.wishlistDemo.utils.TokenUtil;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;

import com.sample.wishlistDemo.dao.WishlistDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishlistDaoImpl implements WishlistDao {
    private HttpClient httpClient = null;

    @Override
    public List<Wishlist> getWishlistsByUserId(String userId) {
        try {
            httpClient = new HTTPSTrustClient().init();
            TokenResultBean tokenBean = TokenUtil.getAccessToken();
            if(tokenBean != null) {
                //https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
                String url = SystemConfig.documentBaseUrl + SystemConfig.tenant + "/" + SystemConfig.client + "/data/" + SystemConfig.type;
                Map<String, String> paramHeader = new HashMap<>();
                paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
                paramHeader.put("Content-type", SystemConfig.jsonContentType);
                paramHeader.put("hybris-metaData", "wishlist:_owner:");

                // 'q' : "comicId:(C01H02, C01H03)"
                String query = "'q':\"_owner:" + userId + "\"";
                ResultBean result = JSON.parseObject(HTTPSClientUtil.doGet(httpClient, url, paramHeader ,query), ResultBean.class);

                return result.getDetails();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveWishlistByInfo(Wishlist wishlist) {
        try {
            httpClient = new HTTPSTrustClient().init();
            TokenResultBean tokenBean = TokenUtil.getAccessToken();
            if(tokenBean != null) {
                //https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}
                String url = SystemConfig.documentBaseUrl + SystemConfig.tenant + "/" + SystemConfig.client + "/data/" + SystemConfig.type +"/";
                Map<String, String> paramHeader = new HashMap<>();
                paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
                paramHeader.put("Content-type", SystemConfig.jsonContentType);
                Map<String, String> paramBody = new HashMap<>();

                paramBody.put("data", JSON.toJSONString(wishlist));
                ResultBean result = JSON.parseObject(HTTPSClientUtil.doPost(httpClient, url, paramHeader, paramBody), ResultBean.class);

                return result.getStatus().equals("200");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Wishlist getByWishlistId(String wishlistId) {
        try {
            httpClient = new HTTPSTrustClient().init();
            TokenResultBean tokenBean = TokenUtil.getAccessToken();
            if(tokenBean != null) {
                //https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}/{dataId}
                String url = SystemConfig.documentBaseUrl + SystemConfig.tenant + "/" + SystemConfig.client + "/data/" + SystemConfig.type +"/" + wishlistId;
                Map<String, String> paramHeader = new HashMap<>();
                paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
                paramHeader.put("Content-type", SystemConfig.jsonContentType);

                ResultBean result = JSON.parseObject(HTTPSClientUtil.doGet(httpClient, url, paramHeader,""), ResultBean.class);

                return result.getDetails().get(0);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateWishlistByInfo(String wishlistId, Wishlist wishlist) {
        try {
            httpClient = new HTTPSTrustClient().init();
            TokenResultBean tokenBean = TokenUtil.getAccessToken();
            if(tokenBean != null) {
                //https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}/{dataId}
                String url = SystemConfig.documentBaseUrl + SystemConfig.tenant + "/" + SystemConfig.client + "/data/" + SystemConfig.type +"/" + wishlistId;
                Map<String, String> paramHeader = new HashMap<>();
                paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());
                paramHeader.put("Content-type", SystemConfig.jsonContentType);

                ResultBean result = JSON.parseObject(HTTPSClientUtil.doPut(httpClient, url, paramHeader, JSON.toJSONString(wishlist)), ResultBean.class);

                return result.getStatus().equals("200");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteWishlistById(String wishlistId) {
        try {
            httpClient = new HTTPSTrustClient().init();
            TokenResultBean tokenBean = TokenUtil.getAccessToken();
            if(tokenBean != null) {
                //https://api.beta.yaas.io/hybris/document/v1/{tenant}/{client}/data/{type}/{dataId}
                String url = SystemConfig.documentBaseUrl + SystemConfig.tenant + "/" + SystemConfig.client + "/data/" + SystemConfig.type +"/" + wishlistId;
                Map<String, String> paramHeader = new HashMap<>();
                paramHeader.put("Authorization", tokenBean.getTokenType() + " " + tokenBean.getAccessToken());

                ResultBean result = JSON.parseObject(HTTPSClientUtil.doDelete(httpClient, url, paramHeader), ResultBean.class);

                return result.getStatus().equals("200");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
