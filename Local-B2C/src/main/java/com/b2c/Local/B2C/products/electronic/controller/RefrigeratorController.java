package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.RefrigeratorDto;
import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import com.b2c.Local.B2C.products.electronic.service.RefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{uuid}/getAllByStoreId")
    public List<Refrigerator> getAllByStoreId(@PathVariable UUID uuid) {
        return refrigeratorService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateRefrigeratorById")
    public Refrigerator updateRefrigeratorById(@RequestBody RefrigeratorDto refrigeratorDto, @PathVariable Long id){
        return refrigeratorService.updateRefrigeratorById(refrigeratorDto, id);
    }

    @GetMapping("/{id}/getRefrigeratorById")
    public Refrigerator getRefrigeratorById(@PathVariable Long id){
        return refrigeratorService.getRefrigeratorById(id);
    }

    @GetMapping("/deactivateById")
    public String deactivateById(@RequestParam Long id){
        return refrigeratorService.deactivateById(id);
    }
}
