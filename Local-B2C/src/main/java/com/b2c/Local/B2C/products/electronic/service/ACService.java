package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.products.electronic.dao.ACRepository;
import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ACService {

    ACRepository acRepository;

    @Autowired
    public ACService(ACRepository acRepository) {
        this.acRepository = acRepository;
    }

    public AC addAc(ACDto acDto){
        AC ac = new AC();

        return ac;
    }
}
