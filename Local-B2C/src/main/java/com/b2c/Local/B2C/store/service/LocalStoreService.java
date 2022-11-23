package com.b2c.Local.B2C.store.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.exception.Forbidden403Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.*;
import com.b2c.Local.B2C.products.electronic.service.ACService;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.b2c.Local.B2C.store.dto.LocalStoreDto;
import com.b2c.Local.B2C.store.model.LocalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalStoreService {

    LocalStoreRepository localStoreRepository;

    UserRepository userRepository;

    ACRepository acRepository;

    LaptopRepository laptopRepository;

    MobilePhoneRepository mobilePhoneRepository;

    RefrigeratorRepository refrigeratorRepository;

    TelevisionRepository televisionRepository;

    WashingMachineRepository washingMachineRepository;

    ACService acService;

    @Autowired
    public LocalStoreService(LocalStoreRepository localStoreRepository, UserRepository userRepository,
                             ACRepository acRepository, LaptopRepository laptopRepository,
                             MobilePhoneRepository mobilePhoneRepository, RefrigeratorRepository refrigeratorRepository,
                             TelevisionRepository televisionRepository, WashingMachineRepository washingMachineRepository,
                             ACService acService) {
        this.localStoreRepository = localStoreRepository;
        this.userRepository = userRepository;
        this.acRepository = acRepository;
        this.laptopRepository = laptopRepository;
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.refrigeratorRepository = refrigeratorRepository;
        this.televisionRepository = televisionRepository;
        this.washingMachineRepository = washingMachineRepository;
        this.acService = acService;
    }

    public LocalStore addStore(LocalStoreDto localStoreDto){
        LocalStore localStore = new LocalStore();
        if (Objects.equals(getLoggedInUserId(), localStoreDto.getUserId())){
            localStore.setStoreName(localStoreDto.getStoreName());
            localStore.setStoreAddress(localStoreDto.getStoreAddress());
            localStore.setPinCode(localStoreDto.getPinCode());
            localStore.setCity(localStoreDto.getCity());
            localStore.setEmail(localStoreDto.getEmail());
            localStore.setContactNo(localStoreDto.getContactNo());
            localStore.setAlternateContactNo(localStoreDto.getAlternateContactNo());
            localStore.setAddressLink(localStoreDto.getAddressLink());
            localStore.setOwnerName(localStoreDto.getOwnerName());
            localStore.setDescription(localStoreDto.getDescription());
            localStore.setListOfProduct(localStoreDto.getListOfProduct());
            localStore.setUser(userRepository.findById(localStoreDto.getUserId()).get());
            localStore.setActive(true);
            localStoreRepository.save(localStore);
        }else {
            throw new Forbidden403Exception("Enter Valid UserId");
        }
        return localStore;
    }

    public LocalStore UpdateByStoreId(UUID uuid, LocalStoreDto localStoreDto){
        if (localStoreRepository.findById(uuid).isEmpty()){
           throw new NotFound404Exception("Store Not Found");
        }
        LocalStore localStore = localStoreRepository.findById(uuid).get();
        if (localStoreDto.getEmail() != null)
            localStore.setEmail(localStoreDto.getEmail());
        if (localStoreDto.getStoreName() != null)
            localStore.setStoreName(localStoreDto.getStoreName());
        if (localStoreDto.getStoreAddress() != null)
            localStore.setStoreName(localStoreDto.getStoreAddress());
        if (localStoreDto.getCity() != null)
            localStore.setCity(localStoreDto.getCity());
        localStoreRepository.save(localStore);
        return localStore;
    }

    private UUID getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User)principal).getId();
        }
        return null;
    }

    public List<LocalStore> listAllStores(){
        if (getLoggedInUserId() != null)
            return localStoreRepository.findByUserId(getLoggedInUserId());
        return null;
    }

    public LocalStore getById(UUID uuid){
        if (localStoreRepository.findById(uuid).isPresent())
            return localStoreRepository.findById(uuid).get();
        else
            return null;
    }

    public String deleteById(UUID uuid){
        if (localStoreRepository.findById(uuid).isPresent()){
             localStoreRepository.deleteById(uuid);
             return "Deleted";
        }else {
            return "Store Not Found";
        }
    }

    public String deactivateById(UUID uuid){
        if (localStoreRepository.findById(uuid).isPresent()){
            LocalStore localStore =localStoreRepository.findById(uuid).get();
            localStore.setActive(false);
            localStoreRepository.save(localStore);
            return "Deactivate Store";
        }else {
            return "Store Not Found";
        }
    }

    public String deleteAllStoreByUserId(){
        localStoreRepository.deleteAll(localStoreRepository.findByUserId(getLoggedInUserId()));
        return "Delete All Store";
    }

    public LocalStore addProductByStoreId(UUID uuid, String product){
        if (localStoreRepository.findById(uuid).isPresent()){
            LocalStore localStore = localStoreRepository.findById(uuid).get();
            localStore.setListOfProduct(Collections.singletonList(product));
            localStoreRepository.save(localStore);
            return localStore;
        }else {
            return null;
        }
    }

    public List<LocalStore> findStoreByPinCode(int pinCode){
        return localStoreRepository.findByPinCodeAndActiveTrue(pinCode);
    }

    public List<LocalStore> findStoreByCity(String city){
        return localStoreRepository.findByCityAndActiveTrue(city);
    }

    public Map<String, Object> getAllProductInLocalStoreById(UUID uuid){
        if (localStoreRepository.existsByIdAndActiveTrue(uuid)){
            Map<String, Object> map = new HashMap<>();
            map.put("Ac", acRepository.findByLocalStore_IdAndActiveTrue(uuid));
            map.put("Laptop", laptopRepository.findByLocalStore_IdAndActiveTrue(uuid));
            map.put("MobilePhone", mobilePhoneRepository.findByLocalStore_IdAndActiveTrue(uuid));
            map.put("Refrigerator", refrigeratorRepository.findByLocalStore_IdAndActiveTrue(uuid));
            map.put("Television", televisionRepository.findByLocalStore_IdAndActiveTrue(uuid));
            map.put("WashingMachine", washingMachineRepository.findByLocalStore_IdAndActiveTrue(uuid));
            return map;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }
}
