package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.RefrigeratorRepository;
import com.b2c.Local.B2C.products.electronic.dto.RefrigeratorDto;
import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RefrigeratorService {

    RefrigeratorRepository refrigeratorRepository;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository, LocalStoreRepository localStoreRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.localStoreRepository = localStoreRepository;
    }

    public Refrigerator addRefrigerator(RefrigeratorDto refrigeratorDto){
        if (localStoreRepository.findById(refrigeratorDto.getLocalStoreId()).isPresent()){
            Refrigerator refrigerator = new Refrigerator();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setLocalStore(localStoreRepository.findById(refrigeratorDto.getLocalStoreId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Refrigerator> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return refrigeratorRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Refrigerator updateRefrigeratorById(RefrigeratorDto refrigeratorDto, Long id){
        if (refrigeratorRepository.existsByRefrigeratorIdAndActiveTrue(id)){
            Refrigerator refrigerator = refrigeratorRepository.findById(id).get();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setLocalStore(localStoreRepository.findById(refrigeratorDto.getLocalStoreId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Refrigerator getRefrigeratorById(Long id){
        return refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(id);
    }
}
