package com.b2c.Local.B2C.common.service;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.dao.WishlistProductRepository;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProduct;
import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.Conflict409Exception;
import com.b2c.Local.B2C.exception.Forbidden403Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.*;
import com.b2c.Local.B2C.products.electronic.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class WishlistProductService {

    WishlistProductRepository wishlistProductRepository;

    ObjectMapper objectMapper;

    ACRepository acRepository;

    LaptopRepository laptopRepository;

    MobilePhoneRepository mobilePhoneRepository;

    RefrigeratorRepository refrigeratorRepository;

    TelevisionRepository televisionRepository;

    WashingMachineRepository washingMachineRepository;

    @Autowired
    public WishlistProductService(WishlistProductRepository wishlistProductRepository, ObjectMapper objectMapper, ACRepository acRepository, LaptopRepository laptopRepository, MobilePhoneRepository mobilePhoneRepository, RefrigeratorRepository refrigeratorRepository, TelevisionRepository televisionRepository, WashingMachineRepository washingMachineRepository) {
        this.wishlistProductRepository = wishlistProductRepository;
        this.objectMapper = objectMapper;
        this.acRepository = acRepository;
        this.laptopRepository = laptopRepository;
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.refrigeratorRepository = refrigeratorRepository;
        this.televisionRepository = televisionRepository;
        this.washingMachineRepository = washingMachineRepository;
    }

    private User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal);
        }
        return null;
    }

    public Object addByObject(Object wishlist, UUID uuid) {
        if (!uuid.equals(Objects.requireNonNull(getLoggedInUser()).getId())) {
            throw new Forbidden403Exception("Not Allowed");
        }
        WishlistProduct wishlistProduct = new WishlistProduct();
        if (objectMapper.convertValue(wishlist, Laptop.class).getLaptopId() != null) {
            if (!laptopRepository.existsByLaptopIdAndActiveTrue(objectMapper.convertValue(wishlist, Laptop.class).getLaptopId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, Laptop.class).getLaptopId(), ProductEnum.LAPTOP, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.LAPTOP);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Laptop.class).getLaptopId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(laptopRepository.findByLaptopIdAndActiveTrue(objectMapper.convertValue(wishlist, Laptop.class).getLaptopId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        } else if (objectMapper.convertValue(wishlist, AC.class).getAcId() != null) {
            if (!acRepository.existsByAcIdAndActiveTrue(objectMapper.convertValue(wishlist, AC.class).getAcId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, AC.class).getAcId(), ProductEnum.AC, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.AC);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, AC.class).getAcId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(acRepository.findByAcIdAndActiveTrue(objectMapper.convertValue(wishlist, AC.class).getAcId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        } else if (objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId() != null) {
            if (!washingMachineRepository.existsByWashingMachineIdAndActiveTrue(objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId(), ProductEnum.WASHINGMACHINE, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.WASHINGMACHINE);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(washingMachineRepository.findByWashingMachineIdAndActiveTrue(objectMapper.convertValue(wishlist, WashingMachine.class).getWashingMachineId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        } else if (objectMapper.convertValue(wishlist, Television.class).getTelevisionId() != null) {
            if (!televisionRepository.existsByTelevisionIdAndActiveTrue(objectMapper.convertValue(wishlist, Television.class).getTelevisionId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, Television.class).getTelevisionId(), ProductEnum.TELEVISION, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.TELEVISION);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Television.class).getTelevisionId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(televisionRepository.findByTelevisionIdAndActiveTrue(objectMapper.convertValue(wishlist, Television.class).getTelevisionId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        } else if (objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId() != null) {
            if (!mobilePhoneRepository.existsByMobilePhoneIdAndActiveTrue(objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId(), ProductEnum.MOBILEPHONE, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.MOBILEPHONE);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(objectMapper.convertValue(wishlist, MobilePhone.class).getMobilePhoneId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        } else if (objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId() != null) {
            if (!refrigeratorRepository.existsByRefrigeratorIdAndActiveTrue(objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId())) {
                throw new NotFound404Exception("Product Not Found");
            }
            if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId(), ProductEnum.REFRIGERATOR, getLoggedInUser())) {
                wishlistProduct.setProduct(ProductEnum.REFRIGERATOR);
                wishlistProduct.setProductId(objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId());
                wishlistProduct.setUser(getLoggedInUser());
                wishlistProduct.setProducts(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(objectMapper.convertValue(wishlist, Refrigerator.class).getRefrigeratorId()));
                wishlistProductRepository.save(wishlistProduct);
                return wishlistProduct;
            } else {
                throw new Conflict409Exception("This Product Already Exists");
            }
        }
        return new BadRequest400Exception("Something went wrong, Please try again!" + wishlist);
    }

    public Object addByUrl(UUID uuid, String url) throws JsonProcessingException {
        if (!uuid.equals(Objects.requireNonNull(getLoggedInUser()).getId())) {
            throw new Forbidden403Exception("Not Allowed");
        }
        WishlistProduct wishlistProduct = new WishlistProduct();
        String[] urlSplit = url.split("/");
        Long id = objectMapper.readValue(urlSplit[urlSplit.length - 2], Long.class);
        switch (urlSplit[urlSplit.length - 1]) {
            case "getAcById":
                if (!acRepository.existsByAcIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.AC, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.AC);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(acRepository.findByAcIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            case "getLaptopById":
                if (!laptopRepository.existsByLaptopIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.LAPTOP, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.LAPTOP);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(laptopRepository.findByLaptopIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            case "getMobilePhoneById":
                if (!mobilePhoneRepository.existsByMobilePhoneIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.MOBILEPHONE, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.MOBILEPHONE);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            case "getRefrigeratorById":
                if (!refrigeratorRepository.existsByRefrigeratorIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.REFRIGERATOR, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.REFRIGERATOR);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            case "getTelevisionById":
                if (!televisionRepository.existsByTelevisionIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.TELEVISION, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.TELEVISION);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(televisionRepository.findByTelevisionIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            case "getWashingMachineById":
                if (!washingMachineRepository.existsByWashingMachineIdAndActiveTrue(id)) {
                    throw new NotFound404Exception("Product Not Found");
                }
                if (!wishlistProductRepository.existsByProductIdAndProductAndUserAndDeletedFalse(id, ProductEnum.WASHINGMACHINE, getLoggedInUser())) {
                    wishlistProduct.setProduct(ProductEnum.WASHINGMACHINE);
                    wishlistProduct.setUser(getLoggedInUser());
                    wishlistProduct.setProductId(id);
                    wishlistProduct.setProducts(washingMachineRepository.findByWashingMachineIdAndActiveTrue(id));
                    wishlistProductRepository.save(wishlistProduct);
                    return wishlistProduct;
                } else {
                    throw new Conflict409Exception("This Product Already Exists");
                }
            default:
                return "Not Added in Wishlist " + url;
        }
    }
}
