package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.TelevisionDto;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.products.electronic.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/television")
public class TelevisionController {

    TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping("/add")
    public Television addTelevision(@RequestBody TelevisionDto televisionDto){
        return televisionService.addTelevision(televisionDto);
    }
}
