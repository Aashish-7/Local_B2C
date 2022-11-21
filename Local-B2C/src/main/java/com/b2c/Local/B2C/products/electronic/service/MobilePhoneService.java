package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.MobilePhoneRepository;
import com.b2c.Local.B2C.products.electronic.dto.MobilePhoneDto;
import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MobilePhoneService {

    MobilePhoneRepository mobilePhoneRepository;

    LocalStoreRepository localStoreRepository;
    @Autowired
    public MobilePhoneService(MobilePhoneRepository mobilePhoneRepository, LocalStoreRepository localStoreRepository) {
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.localStoreRepository = localStoreRepository;
    }

    public MobilePhone addMobilePhone(MobilePhoneDto mobilePhoneDto){
        if (localStoreRepository.existsById(mobilePhoneDto.getLocalStoreId())){
            MobilePhone mobilePhone = new MobilePhone();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColor(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setLocalStore(localStoreRepository.findById(mobilePhoneDto.getLocalStoreId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<MobilePhone> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return mobilePhoneRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public MobilePhone updateMobilePhoneById(MobilePhoneDto mobilePhoneDto, Long id){
        if (mobilePhoneRepository.existsByMobilePhoneIdAndActiveTrue(id)){
            MobilePhone mobilePhone = mobilePhoneRepository.findById(id).get();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColor(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setLocalStore(localStoreRepository.findById(mobilePhoneDto.getLocalStoreId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public MobilePhone getMobilePhoneById(Long id){
        return mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(id);
    }


    public String deactivateById(Long id){
        if (mobilePhoneRepository.findById(id).isPresent() && mobilePhoneRepository.findById(id).get().getActive()){
            MobilePhone mobilePhone =mobilePhoneRepository.findById(id).get();
            mobilePhone.setActive(false);
            mobilePhoneRepository.save(mobilePhone);
            return "Deactivate MobilePhone";
        }else {
            throw new NotFound404Exception("Mobile not found");
        }
    }
}
