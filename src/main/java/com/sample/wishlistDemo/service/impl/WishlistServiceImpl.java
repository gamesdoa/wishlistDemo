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
		return wishlistDao.getWishlistsByUserId(userId);
	}

	@Override
	public boolean saveWishlistByInfo(String userId, Wishlist wishlist) {
		List<Wishlist> list = getWishlistsByUserId(userId);
		for (Wishlist item : list) {
			if(item.getTitle().equals(wishlist.getTitle())){
				return wishlistDao.updateWishlistByInfo(item.getId() , wishlist);
			}
		}

		return wishlistDao.saveWishlistByInfo(wishlist);
	}

	@Override
	public Wishlist getByWishlistId(String userId, String wishlistId) {
		Wishlist wishlist = wishlistDao.getByWishlistId(wishlistId);
		if(wishlist.getOwner().equals(userId)){
			return wishlist;
		} else {
			return null;
		}
	}

	@Override
	public boolean updateWishlistByInfo(String userId, String wishlistId, Wishlist wishlist) {
		Wishlist wishlistDb = getByWishlistId(userId, wishlistId);
		if(wishlistDb == null || !wishlistDb.getOwner().equals(userId))
			return false;

		return wishlistDao.updateWishlistByInfo(wishlistId , wishlist);
	}

	@Override
	public boolean deleteWishlistById(String userId, String wishlistId) {
		Wishlist wishlist = getByWishlistId(userId, wishlistId);
		if(wishlist == null || !wishlist.getOwner().equals(userId))
			return false;

		return wishlistDao.deleteWishlistById(wishlistId);
	}

	@Override
	public List<WishlistItem> getWishlistItemsByWishlistId(String userId, String wishlistId) {
		Wishlist wishlist = getByWishlistId(userId, wishlistId);
		if(wishlist == null || !wishlist.getOwner().equals(userId))
			return null;

		return wishlist.getItems();
	}

	@Override
	public boolean saveWishlistItemByInfo(String userId, String wishlistId, WishlistItem wishlistItem) {
		Wishlist wishlist = getByWishlistId(userId, wishlistId);
		if(wishlist == null || !wishlist.getOwner().equals(userId))
			return false;
		List<WishlistItem> items = wishlist.getItems();

		boolean hes = false;
		for (WishlistItem wli: items) {
			if(wli.getProduct().equals(wishlistItem.getProduct())){
				wli.setAmount(wishlistItem.getAmount());
				wli.setNote(wishlistItem.getNote());
				hes = true;
			}
		}

		if(!hes){
			wishlist.getItems().add(wishlistItem);
		}

		return updateWishlistByInfo(userId , wishlistId , wishlist);
	}

}
