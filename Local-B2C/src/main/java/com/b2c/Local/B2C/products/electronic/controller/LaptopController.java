package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.LaptopDto;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.products.electronic.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/laptop")
public class LaptopController {

    LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/add")
    public Laptop addLaptop(@RequestBody LaptopDto laptopDto){
        return laptopService.addLaptop(laptopDto);
    }
}
