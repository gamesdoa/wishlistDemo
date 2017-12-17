package com.sample.wishlistDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.api.generated.WishlistItem;
import com.sample.wishlistDemo.dao.WishlistDao;
import com.sample.wishlistDemo.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	private WishlistDao wishlistDao;
	@Override
	public List<Wishlist> getWishlistsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveWishlistByInfo(String userId, Wishlist wishlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Wishlist getByWishlistId(String userId, String wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateWishlistByInfo(String userId, String wishlistId, Wishlist wishlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteWishlistById(String userId, String wishlistId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<WishlistItem> getWishlistItemsByWishlistId(String userId, String wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveWishlistItemByInfo(String userId, String wishlistId, WishlistItem wishlistItem) {
		// TODO Auto-generated method stub
		return false;
	}

}
