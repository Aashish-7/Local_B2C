package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.ACRepository;
import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<AC> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return acRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public AC updateAcById(ACDto acDto, Long id){
        if (acRepository.existsByAcIdAndActiveTrue(id)){
            AC ac = acRepository.findById(id).get();
            if (acDto.getModel() != null){
                ac.setModel(acDto.getModel());
            }
            if (acDto.getBrand() != null){
                ac.setBrand(acDto.getBrand());
            }
            if (acDto.getColour() != null){
                ac.setColour(acDto.getColour());
            }
            if (acDto.getWarranty() != null){
                ac.setWarranty(acDto.getWarranty());
            }
            if (acDto.isDigitalDisplay()){
               ac.setDigitalDisplay(acDto.isDigitalDisplay());
            }else {
                ac.setDigitalDisplay(acDto.isDigitalDisplay());
            }
            if (Objects.nonNull(acDto.getWeightInKg())) {
                ac.setWeightInKg(acDto.getWeightInKg());
            }
            if (Objects.nonNull(acDto.getDiscountPercentage())) {
                ac.setDiscountPercentage(acDto.getDiscountPercentage());
            }
            if (Objects.nonNull(acDto.getPowerInStar())){
                ac.setPowerInStar(acDto.getPowerInStar());
            }
            if (acDto.getAvailability() != null) {
                ac.setAvailability(acDto.getAvailability());
            }
            if (acDto.isBuiltInStabilizer()){
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            } else {
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            }
            if (Objects.nonNull(acDto.getCapacityInTon())){
                ac.setCapacityInTon(acDto.getCapacityInTon());
            }
            if (acDto.getMode() != null){
                ac.setMode(acDto.getMode());
            }
            if (acDto.isTimer()){
                ac.setTimer(acDto.isTimer());
            } else {
                ac.setTimer(acDto.isTimer());
            }
            if (acDto.isWiFi()) {
                ac.setWiFi(acDto.isWiFi());
            } else {
                ac.setWiFi(acDto.isWiFi());
            }
            if (acDto.getAirConditionerType() != null){
                ac.setAirConditionerType(acDto.getAirConditionerType());
            }
            if (Objects.nonNull(acDto.getPrice())) {
                ac.setPrice(acDto.getPrice());
            }
            acRepository.save(ac);
            return ac;
        }
        else {
            throw new NotFound404Exception("Ac not found");
        }
    }

    public AC updateAcByIdByPut(ACDto acDto, Long id){
        if (acRepository.existsByAcIdAndActiveTrue(id)){
            AC ac = acRepository.findById(id).get();
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

    public AC getAcById(Long id){
        return acRepository.findByAcIdAndActiveTrue(id);
    }
}
