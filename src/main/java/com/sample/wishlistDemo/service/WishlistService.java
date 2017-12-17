package com.sample.wishlistDemo.service;

import java.util.List;

import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.api.generated.WishlistItem;

public interface WishlistService {

	/**
	 * find wishlist use userId
	 * 
	 * @param userId
	 * @return
	 */
	List<Wishlist> getWishlistsByUserId(String userId);

	/**
	 * save wishlist by info
	 * 
	 * @param userId
	 * @param wishlist
	 * @return
	 */
	boolean saveWishlistByInfo(String userId, Wishlist wishlist);

	/**
	 * find wishlist use wishlistId
	 * 
	 * @param userId
	 * @param wishlistId
	 * @return
	 */
	Wishlist getByWishlistId(String userId, String wishlistId);

	/**
	 * update wishlist info use info
	 * 
	 * @param userId
	 * @param wishlistId
	 * @param wishlist
	 * @return
	 */
	boolean updateWishlistByInfo(String userId, String wishlistId, Wishlist wishlist);

	/**
	 * delete wishlist info use id
	 * 
	 * @param userId
	 * @param wishlistId
	 * @return
	 */
	boolean deleteWishlistById(String userId, String wishlistId);

	/**
	 * find wishlist items use wishlist id
	 * 
	 * @param userId
	 * @param wishlistId
	 * @return
	 */
	List<WishlistItem> getWishlistItemsByWishlistId(String userId, String wishlistId);

	/**
	 * save wishlist item use info
	 * 
	 * @param userId
	 * @param wishlistId
	 * @param wishlistItem
	 * @return
	 */
	boolean saveWishlistItemByInfo(String userId, String wishlistId, WishlistItem wishlistItem);
}
