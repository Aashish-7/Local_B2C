package com.b2c.Local.B2C.common.service;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.dao.WishlistProductRepository;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProduct;
import com.b2c.Local.B2C.common.model.WishlistProductProjection;
import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.Conflict409Exception;
import com.b2c.Local.B2C.exception.Forbidden403Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.*;
import com.b2c.Local.B2C.products.electronic.model.*;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.b2c.Local.B2C.store.service.LocalStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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

    LocalStoreRepository localStoreRepository;

    LocalStoreService localStoreService;

    @Autowired
    public WishlistProductService(WishlistProductRepository wishlistProductRepository, ObjectMapper objectMapper, ACRepository acRepository, LaptopRepository laptopRepository, MobilePhoneRepository mobilePhoneRepository, RefrigeratorRepository refrigeratorRepository, TelevisionRepository televisionRepository, WashingMachineRepository washingMachineRepository,LocalStoreRepository localStoreRepository,LocalStoreService localStoreService) {
        this.wishlistProductRepository = wishlistProductRepository;
        this.objectMapper = objectMapper;
        this.acRepository = acRepository;
        this.laptopRepository = laptopRepository;
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.refrigeratorRepository = refrigeratorRepository;
        this.televisionRepository = televisionRepository;
        this.washingMachineRepository = washingMachineRepository;
        this.localStoreRepository = localStoreRepository;
        this.localStoreService = localStoreService;

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

    public Map<String, Object> getAllProductByUserId(UUID uuid) {
        if (!uuid.equals(getLoggedInUser().getId())) {
            throw new Forbidden403Exception("You Not Allowed");
        }
        Map<String, Object> objectMap = new HashMap<>();
        if (wishlistProductRepository.existsAllByDeletedFalseAndUser_Id(uuid)) {
            List<WishlistProduct> wishlistProductsList = new ArrayList<>();
            List<Double> priceCount = new ArrayList<>();
            wishlistProductRepository.findAllByDeletedFalseAndUser_Id(uuid).forEach(wishlistProduct -> {
                switch (wishlistProduct.getProduct().getValue()) {
                    case "AC":
                        wishlistProduct.setProducts(acRepository.findByAcIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(acRepository.findByAcIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                    case "Laptop":
                        wishlistProduct.setProducts(laptopRepository.findByLaptopIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(laptopRepository.findByLaptopIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                    case "MobilePhone":
                        wishlistProduct.setProducts(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                    case "Refrigerator":
                        wishlistProduct.setProducts(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                    case "Television":
                        wishlistProduct.setProducts(televisionRepository.findByTelevisionIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(televisionRepository.findByTelevisionIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                    case "WashingMachine":
                        wishlistProduct.setProducts(washingMachineRepository.findByWashingMachineIdAndActiveTrue(wishlistProduct.getProductId()));
                        priceCount.add(washingMachineRepository.findByWashingMachineIdAndActiveTrue(wishlistProduct.getProductId()).getPrice());
                        wishlistProductsList.add(wishlistProduct);
                        break;
                }
            });
            objectMap.put("WishlistProduct", wishlistProductsList);
            objectMap.put("TotalPrice", priceCount.stream().mapToDouble(Double::doubleValue).sum());
        } else {
            throw new NotFound404Exception("Wishlist Is Empty");
        }
        return objectMap;
    }

    public String deleteProductById(UUID userId, UUID wishlistId) {
        if (!userId.equals(getLoggedInUser().getId())) {
            throw new Forbidden403Exception("You Not Allowed");
        }
        if (wishlistProductRepository.existsByIdAndDeletedFalse(wishlistId)) {
            WishlistProduct wishlistProduct = wishlistProductRepository.findById(wishlistId).get();
            wishlistProduct.setDeleted(true);
            wishlistProductRepository.save(wishlistProduct);
            return wishlistProduct.getProduct().getValue() + " Deleted Successfully";
        } else {
            throw new NotFound404Exception("Product Not Found In WishList");
        }
    }

    public String deleteAllProductByUserId(UUID userId) {
        if (!userId.equals(getLoggedInUser().getId())) {
            throw new Forbidden403Exception("You Not Allowed");
        }
        if (wishlistProductRepository.existsAllByDeletedFalseAndUser_Id(userId)) {
            List<WishlistProduct> wishlistProducts = new ArrayList<>();
            wishlistProductRepository.findAllByDeletedFalseAndUser_Id(userId).forEach(wishlistProduct -> {
                wishlistProduct.setDeleted(true);
                wishlistProducts.add(wishlistProduct);
            });
            wishlistProductRepository.saveAll(wishlistProducts);
            return "All Deleted Successfully";
        } else {
            throw new NotFound404Exception("Product Not Found In WishList");
        }
    }

    public WishlistProduct getProductById(UUID userId, UUID wishlistId) {
        if (!userId.equals(getLoggedInUser().getId())) {
            throw new Forbidden403Exception("You Not Allowed");
        }
        if (wishlistProductRepository.existsByIdAndDeletedFalse(wishlistId)) {
            WishlistProduct wishlistProduct = wishlistProductRepository.findById(wishlistId).get();
            switch (wishlistProduct.getProduct().getValue()) {
                case "AC":
                    wishlistProduct.setProducts(acRepository.findByAcIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
                case "Laptop":
                    wishlistProduct.setProducts(laptopRepository.findByLaptopIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
                case "MobilePhone":
                    wishlistProduct.setProducts(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
                case "Refrigerator":
                    wishlistProduct.setProducts(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
                case "Television":
                    wishlistProduct.setProducts(televisionRepository.findByTelevisionIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
                case "WashingMachine":
                    wishlistProduct.setProducts(washingMachineRepository.findByWashingMachineIdAndActiveTrue(wishlistProduct.getProductId()));
                    break;
            }
            return wishlistProduct;
        } else {
            throw new NotFound404Exception("Product Not Found");
        }
    }

    public Map<String, Object> getProductCount(UUID userId){
        if (!userId.equals(getLoggedInUser().getId())) {
            throw new Forbidden403Exception("You Not Allowed");
        }
        Map<String, Object> objectMap = new HashMap<>();
        if (!localStoreRepository.findByUserIdAndActiveTrue(userId).isEmpty()){
            Map<String,Long> longMap = new HashMap<>();
            localStoreRepository.findByUserIdAndActiveTrue(userId).forEach(localStore -> {
                longMap.put(ProductEnum.AC.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.AC,localStoreService.getAllAcByLocalStoreId(localStore.getId()).stream().map(AC::getAcId).collect(Collectors.toList())));
                longMap.put(ProductEnum.LAPTOP.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.LAPTOP,localStoreService.getAllLaptopByLocalStoreId(localStore.getId()).stream().map(Laptop::getLaptopId).collect(Collectors.toList())));
                longMap.put(ProductEnum.MOBILEPHONE.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.MOBILEPHONE,localStoreService.getAllMobilePhoneByLocalStoreId(localStore.getId()).stream().map(MobilePhone::getMobilePhoneId).collect(Collectors.toList())));
                longMap.put(ProductEnum.REFRIGERATOR.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.REFRIGERATOR,localStoreService.getAllRefrigeratorByLocalStoreId(localStore.getId()).stream().map(Refrigerator::getRefrigeratorId).collect(Collectors.toList())));
                longMap.put(ProductEnum.WASHINGMACHINE.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.WASHINGMACHINE,localStoreService.getAllWashingMachineByLocalStoreId(localStore.getId()).stream().map(WashingMachine::getWashingMachineId).collect(Collectors.toList())));
                longMap.put(ProductEnum.TELEVISION.getValue(),wishlistProductRepository.countAllByDeletedFalseAndProductAndProductIdIn(ProductEnum.TELEVISION,localStoreService.getAllTelevisionByLocalStoreId(localStore.getId()).stream().map(Television::getTelevisionId).collect(Collectors.toList())));
                objectMap.put(localStore.getStoreName(),longMap);
            });
            return objectMap;
        }else {
            throw new NotFound404Exception("LocalStore Not Found");
        }
    }

    private User getLocalStoreByProduct(ProductEnum products, long productId){
        switch (products.getValue()) {
            case "AC":
                return acRepository.findByAcId(productId).getLocalStore().getUser();
            case "Laptop":
                return laptopRepository.findById(productId).get().getLocalStore().getUser();
            case "MobilePhone":
                return mobilePhoneRepository.findById(productId).get().getLocalStore().getUser();
            case "Refrigerator":
                return refrigeratorRepository.findById(productId).get().getLocalStore().getUser();
            case "Television":
                return televisionRepository.findById(productId).get().getLocalStore().getUser();
            case "WashingMachine":
                return washingMachineRepository.findById(productId).get().getLocalStore().getUser();
            default:
                return null;
        }
    }

    public WishlistProductProjection getMaxProductIdCount(String productEnum){
        return wishlistProductRepository.getProductIdCount(productEnum);
    }
}
