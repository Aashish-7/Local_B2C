package com.b2c.Local.B2C.common.service;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.dao.WishlistProductRepository;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProduct;
import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.products.electronic.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class WishlistProductService {

    WishlistProductRepository wishlistProductRepository;

    ObjectMapper objectMapper;

    @Autowired
    public WishlistProductService(WishlistProductRepository wishlistProductRepository, ObjectMapper objectMapper) {
        this.wishlistProductRepository = wishlistProductRepository;
        this.objectMapper = objectMapper;
    }

    private User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal);
        }
        return null;
    }

    public Object addWishlist(Object wishlist){
        WishlistProduct wishlistProduct =  new WishlistProduct();
        if (objectMapper.convertValue(wishlist, Laptop.class).getLaptopId() !=null) {
            wishlistProduct.setProduct(ProductEnum.LAPTOP);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Laptop.class).getLaptopId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        else if (objectMapper.convertValue(wishlist, AC.class).getAcId() !=null) {
            wishlistProduct.setProduct(ProductEnum.AC);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, AC.class).getAcId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        else if (objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId() !=null) {
            wishlistProduct.setProduct(ProductEnum.WASHINGMACHINE);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        else if (objectMapper.convertValue(wishlist, Television.class).getTelevisionId() !=null) {
            wishlistProduct.setProduct(ProductEnum.TELEVISION);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Television.class).getTelevisionId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        else if (objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId() != null) {
            wishlistProduct.setProduct(ProductEnum.MOBILEPHONE);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        else if (objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId() != null) {
            wishlistProduct.setProduct(ProductEnum.REFRIGERATOR);
            wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId());
            wishlistProduct.setUser(getLoggedInUser());
            return wishlistProduct;
        }
        return new BadRequest400Exception("Something went wrong, Please try again!" + wishlist);
    }
}
