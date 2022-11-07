package com.b2c.Local.B2C.store.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.b2c.Local.B2C.store.dto.LocalStoreDto;
import com.b2c.Local.B2C.store.model.LocalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalStoreService {

    LocalStoreRepository localStoreRepository;

    UserRepository userRepository;

    @Autowired
    public LocalStoreService(LocalStoreRepository localStoreRepository, UserRepository userRepository) {
        this.localStoreRepository = localStoreRepository;
        this.userRepository = userRepository;
    }

    public LocalStore addStore(LocalStoreDto localStoreDto){
        LocalStore localStore = new LocalStore();
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
        localStoreRepository.save(localStore);
        return localStore;
    }
}
