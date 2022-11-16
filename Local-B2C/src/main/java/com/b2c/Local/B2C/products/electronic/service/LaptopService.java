package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.LaptopRepository;
import com.b2c.Local.B2C.products.electronic.dto.LaptopDto;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            laptop.setColor(laptopDto.getColor());
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
}
