package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.products.electronic.dao.MobilePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobilePhoneService {

    MobilePhoneRepository mobilePhoneRepository;

    @Autowired
    public MobilePhoneService(MobilePhoneRepository mobilePhoneRepository) {
        this.mobilePhoneRepository = mobilePhoneRepository;
    }
}
