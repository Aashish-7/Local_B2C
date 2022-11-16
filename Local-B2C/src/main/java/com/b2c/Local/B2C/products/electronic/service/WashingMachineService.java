package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.WashingMachineRepository;
import com.b2c.Local.B2C.products.electronic.dto.WashingMachineDto;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WashingMachineService {

    WashingMachineRepository washingMachineRepository;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository, LocalStoreRepository localStoreRepository) {
        this.washingMachineRepository = washingMachineRepository;
        this.localStoreRepository = localStoreRepository;
    }

    public WashingMachine addWashingMachine(WashingMachineDto washingMachineDto){
        if (localStoreRepository.findById(washingMachineDto.getLocalStoreId()).isPresent()){
            WashingMachine washingMachine = new WashingMachine();
            washingMachine.setModel(washingMachineDto.getModel());
            washingMachine.setBrand(washingMachineDto.getBrand());
            washingMachine.setDryer(washingMachineDto.isDryer());
            washingMachine.setFunctionType(washingMachineDto.getFunctionType());
            washingMachine.setCapacityInKg(washingMachineDto.getCapacityInKg());
            washingMachine.setPowerInStar(washingMachineDto.getPowerInStar());
            washingMachine.setTimer(washingMachineDto.isTimer());
            washingMachine.setColour(washingMachineDto.getColour());
            washingMachine.setWarranty(washingMachineDto.getWarranty());
            washingMachine.setDigitalDisplay(washingMachineDto.isDigitalDisplay());
            washingMachine.setChildLock(washingMachineDto.isChildLock());
            washingMachine.setShockProof(washingMachineDto.isShockProof());
            washingMachine.setWeight(washingMachineDto.getWeight());
            washingMachine.setDiscountPercentage(washingMachineDto.getDiscountPercentage());
            washingMachine.setAvailability(washingMachineDto.getAvailability());
            washingMachine.setPrice(washingMachineDto.getPrice());
            washingMachine.setLocalStore(localStoreRepository.findById(washingMachineDto.getLocalStoreId()).get());
            washingMachine.setActive(true);
            washingMachineRepository.save(washingMachine);
            return washingMachine;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }
}
