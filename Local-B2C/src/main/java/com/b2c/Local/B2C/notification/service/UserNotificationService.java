package com.b2c.Local.B2C.notification.service;

import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProductProjection;
import com.b2c.Local.B2C.common.service.WishlistProductService;
import com.b2c.Local.B2C.notification.dao.UserNotificationRepository;
import com.b2c.Local.B2C.notification.dto.UserNotificationDto;
import com.b2c.Local.B2C.notification.model.UserNotification;
import com.b2c.Local.B2C.products.electronic.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNotificationService {

    UserNotificationRepository userNotificationRepository;

    WishlistProductService wishlistProductService;

    SSEmitterService ssEmitterService;

    ACRepository acRepository;

    LaptopRepository laptopRepository;

    MobilePhoneRepository mobilePhoneRepository;

    RefrigeratorRepository refrigeratorRepository;

    TelevisionRepository televisionRepository;

    WashingMachineRepository washingMachineRepository;

    @Autowired
    public UserNotificationService(UserNotificationRepository userNotificationRepository, WishlistProductService wishlistProductService, SSEmitterService ssEmitterService, ACRepository acRepository, LaptopRepository laptopRepository, MobilePhoneRepository mobilePhoneRepository, RefrigeratorRepository refrigeratorRepository, TelevisionRepository televisionRepository, WashingMachineRepository washingMachineRepository) {
        this.userNotificationRepository = userNotificationRepository;
        this.wishlistProductService = wishlistProductService;
        this.ssEmitterService = ssEmitterService;
        this.acRepository = acRepository;
        this.laptopRepository = laptopRepository;
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.refrigeratorRepository = refrigeratorRepository;
        this.televisionRepository = televisionRepository;
        this.washingMachineRepository = washingMachineRepository;
    }


    public void sendWishlistProductToUserNotification(){
        WishlistProductProjection ac =  wishlistProductService.getMaxProductIdCount(ProductEnum.AC.getValue());
        WishlistProductProjection laptop = wishlistProductService.getMaxProductIdCount(ProductEnum.LAPTOP.getValue());
        WishlistProductProjection mobilePhone = wishlistProductService.getMaxProductIdCount(ProductEnum.MOBILEPHONE.getValue());
        WishlistProductProjection refrigerator = wishlistProductService.getMaxProductIdCount(ProductEnum.REFRIGERATOR.getValue());
        WishlistProductProjection television = wishlistProductService.getMaxProductIdCount(ProductEnum.TELEVISION.getValue());
        WishlistProductProjection washingMachine = wishlistProductService.getMaxProductIdCount(ProductEnum.WASHINGMACHINE.getValue());
        List<UserNotificationDto> userNotificationDto = new ArrayList<>();
        userNotificationDto.add(new UserNotificationDto(acRepository.findByAcIdAndActiveTrue(ac.getProductId()), "Checkout this AC"));
        userNotificationDto.add(new UserNotificationDto(laptopRepository.findByLaptopIdAndActiveTrue(laptop.getProductId()), "checkout this laptop"));
        userNotificationDto.add(new UserNotificationDto(mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(mobilePhone.getProductId()),"Checkout this Mobile"));
        userNotificationDto.add(new UserNotificationDto(refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(refrigerator.getProductId()),"Checkout this Refrigerator"));
        userNotificationDto.add(new UserNotificationDto(televisionRepository.findByTelevisionIdAndActiveTrue(television.getProductId()), "checkout this Television"));
        userNotificationDto.add(new UserNotificationDto(washingMachineRepository.findByWashingMachineIdAndActiveTrue(washingMachine.getProductId()),"checkout this Washing Machine"));
        userNotificationDto.forEach(userNotificationDto1 -> {
            UserNotification userNotification = new UserNotification();
            userNotification.setNotifyMsg(userNotificationDto1);
            userNotification.setActive(true);
            userNotificationRepository.save(userNotification);
            ssEmitterService.sendUserNotification(userNotificationDto1);
            ssEmitterService.sendLocalStoreOwnerNotification(userNotificationDto1);
        });
    }
}
