package com.b2c.Local.B2C.common.controller;

import com.b2c.Local.B2C.common.service.WishlistProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistProductController {

    ObjectMapper objectMapper;

    WishlistProductService wishlistProductService;

    @Autowired
    public WishlistProductController(ObjectMapper objectMapper, WishlistProductService wishlistProductService) {
        this.objectMapper = objectMapper;
        this.wishlistProductService = wishlistProductService;
    }

    @PostMapping("/wishlist")
    public Object addWishlist(@RequestBody Object wishlist){
       return wishlistProductService.addWishlist(wishlist);
    }
}
