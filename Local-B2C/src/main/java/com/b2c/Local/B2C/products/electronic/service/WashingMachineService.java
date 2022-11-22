package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.WashingMachineRepository;
import com.b2c.Local.B2C.products.electronic.dto.WashingMachineDto;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<WashingMachine> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return washingMachineRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public WashingMachine updateWashingMachineById(WashingMachineDto washingMachineDto, Long id){
        if (washingMachineRepository.existsByWashingMachineIdAndActiveTrue(id)){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
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
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public WashingMachine getWashingMachineById(Long id){
        return washingMachineRepository.findByWashingMachineIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (washingMachineRepository.findById(id).isPresent() && washingMachineRepository.findById(id).get().getActive()){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
            washingMachine.setActive(false);
            washingMachineRepository.save(washingMachine);
            return "Deactivate WashingMachine";
        }else {
            throw new NotFound404Exception("Washing Machine not found!");
        }
    }

    public List<WashingMachine> getAllByModel(String model){
        return washingMachineRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<WashingMachine> getAllByBrand(String brand){
        return washingMachineRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<WashingMachine> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!washingMachineRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return washingMachineRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<WashingMachine> getAllByModelAndPincode(String model, int pinCode) {
        if (!washingMachineRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return washingMachineRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }
}
