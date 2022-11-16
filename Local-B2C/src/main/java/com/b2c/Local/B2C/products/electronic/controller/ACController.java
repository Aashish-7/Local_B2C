package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.products.electronic.service.ACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/ac")
public class ACController {

    ACService acService;

    @Autowired
    public ACController(ACService acService) {
        this.acService = acService;
    }

    @PostMapping("/add")
    public AC addAc(@RequestBody ACDto acDto){
        return acService.addAc(acDto);
    }
}
