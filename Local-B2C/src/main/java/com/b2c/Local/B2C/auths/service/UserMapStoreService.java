package com.b2c.Local.B2C.auths.service;

import com.b2c.Local.B2C.auths.dao.UserMapStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapStoreService {


    UserMapStoreRepository userMapStoreRepository;

    @Autowired
    public UserMapStoreService(UserMapStoreRepository userMapStoreRepository) {
        this.userMapStoreRepository = userMapStoreRepository;
    }
}
