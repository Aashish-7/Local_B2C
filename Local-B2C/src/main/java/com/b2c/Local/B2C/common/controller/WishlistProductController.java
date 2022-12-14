package com.b2c.Local.B2C.common.controller;

import com.b2c.Local.B2C.common.service.WishlistProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/wishlistProduct")
public class WishlistProductController {

    WishlistProductService wishlistProductService;

    @Autowired
    public WishlistProductController( WishlistProductService wishlistProductService) {
        this.wishlistProductService = wishlistProductService;
    }

    @PostMapping("{userId}/addByObject")
    public Object addByObject(@RequestBody Object wishlist, @PathVariable UUID userId){
       return wishlistProductService.addByObject(wishlist, userId);
    }

    @PostMapping("{userId}/addByUrl")
    public Object addByUrl(@PathVariable UUID userId,@RequestParam String productUrl) throws JsonProcessingException {
        return wishlistProductService.addByUrl(userId, productUrl);
    }

    @GetMapping("{userId}/getAllProductByUserId")
    public Map<String, Object> getAllProductByUserId(@PathVariable UUID userId){
        return wishlistProductService.getAllProductByUserId(userId);
    }
}
