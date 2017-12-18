package com.sample.wishlistDemo.dao;

import com.sample.wishlistDemo.api.generated.Wishlist;

import java.util.List;

public interface WishlistDao {
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
     * @param wishlist
     * @return
     */
    boolean saveWishlistByInfo(Wishlist wishlist);

    /**
     * find wishlist use wishlistId
     *
     * @param wishlistId
     * @return
     */
    Wishlist getByWishlistId(String wishlistId);

    /**
     * update wishlist info use info
     *
     * @param wishlistId
     * @param wishlist
     * @return
     */
    boolean updateWishlistByInfo(String wishlistId, Wishlist wishlist);

    /**
     * delete wishlist info use id
     *
     * @param wishlistId
     * @return
     */
    boolean deleteWishlistById(String wishlistId);
}
