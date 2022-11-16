package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.ACRepository;
import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ACService {

    ACRepository acRepository;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public ACService(ACRepository acRepository, LocalStoreRepository localStoreRepository) {
        this.acRepository = acRepository;
        this.localStoreRepository = localStoreRepository;
    }

    public AC addAc(ACDto acDto){
        if (localStoreRepository.findById(acDto.getLocalStoreId()).isPresent()) {
            AC ac = new AC();
            ac.setModel(acDto.getModel());
            ac.setBrand(acDto.getBrand());
            ac.setColour(acDto.getColour());
            ac.setWarranty(acDto.getWarranty());
            ac.setDigitalDisplay(acDto.isDigitalDisplay());
            ac.setWeightInKg(acDto.getWeightInKg());
            ac.setDiscountPercentage(acDto.getDiscountPercentage());
            ac.setPowerInStar(acDto.getPowerInStar());
            ac.setAvailability(acDto.getAvailability());
            ac.setBuiltInStabilizer(ac.isBuiltInStabilizer());
            ac.setCapacityInTon(acDto.getCapacityInTon());
            ac.setMode(acDto.getMode());
            ac.setTimer(acDto.isTimer());
            ac.setWiFi(acDto.isWiFi());
            ac.setAirConditionerType(acDto.getAirConditionerType());
            ac.setPrice(acDto.getPrice());
            ac.setLocalStore(localStoreRepository.findById(acDto.getLocalStoreId()).get());
            ac.setActive(true);
            acRepository.save(ac);
            return ac;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }
}
