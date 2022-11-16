package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.RefrigeratorDto;
import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import com.b2c.Local.B2C.products.electronic.service.RefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/refrigerator")
public class RefrigeratorController {

    RefrigeratorService refrigeratorService;

    @Autowired
    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    @PostMapping("/add")
    public Refrigerator addRefrigerator(@RequestBody RefrigeratorDto refrigeratorDto){
        return refrigeratorService.addRefrigerator(refrigeratorDto);
    }
}
