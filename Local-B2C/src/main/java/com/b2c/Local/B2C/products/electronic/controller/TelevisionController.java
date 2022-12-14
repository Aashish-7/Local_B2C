package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.TelevisionDto;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.products.electronic.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
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
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television addTelevision(@RequestBody TelevisionDto televisionDto){
        return televisionService.addTelevision(televisionDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Television> getAllByStoreId(@PathVariable UUID uuid) {
        return televisionService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateTelevisionById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television updateTelevisionById(@RequestBody TelevisionDto televisionDto, @PathVariable Long id){
        return televisionService.updateTelevisionById(televisionDto, id);
    }

    @GetMapping("/{id}/getTelevisionById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television getTelevisionById(@PathVariable Long id){
        return televisionService.getTelevisionById(id);
    }

    @GetMapping("/{id}/deactivateById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return televisionService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Television> getAllByModel(@RequestParam @NotNull String model){
        return televisionService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Television> getAllByBrand(@RequestParam @NotNull String brand){
        return televisionService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<Television> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return televisionService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<Television> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return televisionService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredTelevision")
    public Map<String, Object> getFilteredTelevision(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return televisionService.getFilteredTelevision(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return televisionService.findAllDistinctData();
    }
}
