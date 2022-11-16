package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.products.electronic.dao.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefrigeratorService {

    RefrigeratorRepository refrigeratorRepository;

    @Autowired
    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
    }
}
