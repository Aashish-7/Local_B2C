package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.TelevisionRepository;
import com.b2c.Local.B2C.products.electronic.dto.TelevisionDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TelevisionService {

    TelevisionRepository televisionRepository;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, LocalStoreRepository localStoreRepository) {
        this.televisionRepository = televisionRepository;
        this.localStoreRepository = localStoreRepository;
    }

    public Television addTelevision(TelevisionDto televisionDto){
        if (localStoreRepository.findById(televisionDto.getLocalStoreId()).isPresent()){
            Television television = new Television();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColor(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setLocalStore(localStoreRepository.findById(televisionDto.getLocalStoreId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Television> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return televisionRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Television updateTelevisionById(TelevisionDto televisionDto, Long id){
        if (televisionRepository.existsByTelevisionIdAndActiveTrue(id)){
            Television television = televisionRepository.findById(id).get();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColor(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setLocalStore(localStoreRepository.findById(televisionDto.getLocalStoreId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Television getTelevisionById(Long id){
        return televisionRepository.findByTelevisionIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (televisionRepository.findById(id).isPresent() && televisionRepository.findById(id).get().getActive()){
            Television television = televisionRepository.findById(id).get();
            television.setActive(false);
            televisionRepository.save(television);
            return "Deactivate television";
        }else {
            throw new NotFound404Exception("Television not found");
        }
    }

    public List<Television> getAllByModel(String model){
        return televisionRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Television> getAllByBrand(String brand){
        return televisionRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<Television> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!televisionRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return televisionRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<Television> getAllByModelAndPincode(String model, int pinCode) {
        if (!televisionRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return televisionRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }
}
