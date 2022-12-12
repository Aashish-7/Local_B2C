package com.b2c.Local.B2C.common.controller;

import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistProductController {

    ObjectMapper objectMapper;

    @Autowired
    public WishlistProductController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/wishlist")
    public String setWishlist(@RequestBody Object wishlist){
        if (objectMapper.convertValue(wishlist, Laptop.class).getLaptopId() !=null)
            return "laptop";
        if (objectMapper.convertValue(wishlist, AC.class).getAcId() !=null)
            return "ac";
        if (objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId() !=null)
            return "washingMachine";
        if (objectMapper.convertValue(wishlist, Television.class).getTelevisionId() !=null)
            return "television";
        else
            return "Nothing !!";
    }
}
