package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.LaptopRepository;
import com.b2c.Local.B2C.products.electronic.dto.LaptopDto;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaptopService {

    LaptopRepository laptopRepository;

    LocalStoreRepository localStoreRepository;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository, LocalStoreRepository localStoreRepository) {
        this.laptopRepository = laptopRepository;
        this.localStoreRepository = localStoreRepository;
    }


    public Laptop addLaptop(LaptopDto laptopDto){
        if (localStoreRepository.existsById(laptopDto.getLocalStoreId())) {
            Laptop laptop = new Laptop();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setLocalStore(localStoreRepository.findById(laptopDto.getLocalStoreId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Laptop> getAllByStoreId(UUID uuid) {
        if (localStoreRepository.existsById(uuid)) {
            return laptopRepository.findByLocalStore_IdAndActiveTrue(uuid);
        } else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Laptop updateLaptopById(LaptopDto laptopDto, Long id){
        if (laptopRepository.existsByLaptopIdAndActiveTrue(id)){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setLocalStore(localStoreRepository.findById(laptopDto.getLocalStoreId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Laptop getLaptopById(Long id){
        return laptopRepository.findByLaptopIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (laptopRepository.findById(id).isPresent() && laptopRepository.findById(id).get().getActive()){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setActive(false);
            laptopRepository.save(laptop);
            return "Deactivate laptop";
        }else {
            throw new NotFound404Exception("Laptop not found.");
        }
    }

    public List<Laptop> getAllByModel(String model){
        return laptopRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Laptop> getAllByBrand(String brand){
        return laptopRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<Laptop> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!laptopRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return laptopRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<Laptop> getAllByModelAndPincode(String model, int pinCode) {
        if (!laptopRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return laptopRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }
}
