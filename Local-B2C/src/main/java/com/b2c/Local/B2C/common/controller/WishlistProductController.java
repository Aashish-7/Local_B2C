package com.b2c.Local.B2C.common.controller;

import com.b2c.Local.B2C.common.service.WishlistProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wishlistProduct")
public class WishlistProductController {

    WishlistProductService wishlistProductService;

    @Autowired
    public WishlistProductController( WishlistProductService wishlistProductService) {
        this.wishlistProductService = wishlistProductService;
    }

    @PostMapping("{uuid}/addByObject")
    public Object addByObject(@RequestBody Object wishlist, @PathVariable UUID uuid){
       return wishlistProductService.addByObject(wishlist, uuid);
    }

    @PostMapping("{uuid}/addByUrl")
    public Object addByUrl(@PathVariable UUID uuid,@RequestParam String productUrl) throws JsonProcessingException {
        return wishlistProductService.addByUrl(uuid, productUrl);
    }
}
