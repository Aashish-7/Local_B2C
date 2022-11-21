package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.TelevisionDto;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.products.electronic.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{uuid}/getAllByStoreId")
    public List<Television> getAllByStoreId(@PathVariable UUID uuid) {
        return televisionService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateTelevisionById")
    public Television updateTelevisionById(@RequestBody TelevisionDto televisionDto, @PathVariable Long id){
        return televisionService.updateTelevisionById(televisionDto, id);
    }

    @GetMapping("/{id}/getTelevisionById")
    public Television getTelevisionById(@PathVariable Long id){
        return televisionService.getTelevisionById(id);
    }

    @GetMapping("/{id}/deactivateById")
    public String deactivateById(@PathVariable Long id){
        return televisionService.deactivateById(id);
    }
}
