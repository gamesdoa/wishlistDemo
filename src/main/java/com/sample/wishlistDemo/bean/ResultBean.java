package com.sample.wishlistDemo.bean;

import com.sample.wishlistDemo.api.generated.Wishlist;

import java.util.List;

public class ResultBean {
    String status;
    String message;
    List<Wishlist> details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Wishlist> getDetails() {
        return details;
    }

    public void setDetails(List<Wishlist> details) {
        this.details = details;
    }
}
