
package com.sample.wishlistDemo.api.generated;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.wishlistDemo.service.WishlistService;

/**
 * Resource class containing the custom logic. Please put your logic here!
 */
@Component("apiWishlistsResource")
@Singleton
public class DefaultWishlistsResource implements com.sample.wishlistDemo.api.generated.WishlistsResource {
	@javax.ws.rs.core.Context
	private javax.ws.rs.core.UriInfo uriInfo;

	@Autowired
	private WishlistService wishlistService;

	/* GET / */
	@Override
	public Response get(final YaasAwareParameters yaasAware) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			List<Wishlist> result = wishlistService.getWishlistsByUserId(yaasAware.getHybrisUserId());
			return Response.ok().entity(result).build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	/* POST / */
	@Override
	public Response post(final YaasAwareParameters yaasAware, final Wishlist wishlist) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			boolean result = wishlistService.saveWishlistByInfo(yaasAware.getHybrisUserId(), wishlist);
			if (result) {
				return Response.created(uriInfo.getAbsolutePath()).build();
			} else {
				return Response.serverError().build();
			}
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	/* GET /{wishlistId} */
	@Override
	public Response getByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			Wishlist result = wishlistService.getByWishlistId(yaasAware.getHybrisUserId(), wishlistId);
			return Response.ok().entity(result).build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	/* PUT /{wishlistId} */
	@Override
	public Response putByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId,
			final Wishlist wishlist) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			boolean result = wishlistService.updateWishlistByInfo(yaasAware.getHybrisUserId(), wishlistId, wishlist);
			if (result) {
				return Response.ok().build();
			} else {
				return Response.serverError().build();
			}
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	/* DELETE /{wishlistId} */
	@Override
	public Response deleteByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			boolean result = wishlistService.deleteWishlistById(yaasAware.getHybrisUserId(), wishlistId);
			if (result) {
				return Response.noContent().build();
			} else {
				return Response.serverError().build();
			}
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	@Override
	public Response getByWishlistIdWishlistItems(final YaasAwareParameters yaasAware,
			final java.lang.String wishlistId) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			List<WishlistItem> result = wishlistService.getWishlistItemsByWishlistId(yaasAware.getHybrisUserId(),
					wishlistId);
			return Response.ok().entity(result).build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	@Override
	public Response postByWishlistIdWishlistItems(final YaasAwareParameters yaasAware,
			final java.lang.String wishlistId, final WishlistItem wishlistItem) {
		// place some logic here
		if (verifyInfo(yaasAware)) {
			boolean result = wishlistService.saveWishlistItemByInfo(yaasAware.getHybrisUserId(), wishlistId,
					wishlistItem);
			if (result) {
				return Response.noContent().build();
			} else {
				return Response.serverError().build();
			}
		} else {
			return Response.notAcceptable(null).build();
		}
	}
	
	/**
	 * Verify the necessary information
	 * 
	 * @param yaasAware
	 * @return
	 */
	private boolean verifyInfo(final YaasAwareParameters yaasAware) {
		if(StringUtils.isEmpty(yaasAware.getHybrisUserId())) {
			return true; // The actual situation should return false
		} else if(StringUtils.isEmpty(yaasAware.getHybrisUser())) {
			return true; // The actual situation should return false
		}
		return true;
	}

}
