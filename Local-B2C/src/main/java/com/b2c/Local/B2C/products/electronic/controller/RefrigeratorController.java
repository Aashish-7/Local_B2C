package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.RefrigeratorDto;
import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import com.b2c.Local.B2C.products.electronic.service.RefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
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
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Refrigerator addRefrigerator(@RequestBody RefrigeratorDto refrigeratorDto){
        return refrigeratorService.addRefrigerator(refrigeratorDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Refrigerator> getAllByStoreId(@PathVariable UUID uuid) {
        return refrigeratorService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateRefrigeratorById")
    public Refrigerator updateRefrigeratorById(@RequestBody RefrigeratorDto refrigeratorDto, @PathVariable Long id){
        return refrigeratorService.updateRefrigeratorById(refrigeratorDto, id);
    }

    @GetMapping("/{id}/getRefrigeratorById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Refrigerator getRefrigeratorById(@PathVariable Long id){
        return refrigeratorService.getRefrigeratorById(id);
    }

    @GetMapping("/{id}/deactivateById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return refrigeratorService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Refrigerator> getAllByModel(@RequestParam @NotNull String model){
        return refrigeratorService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Refrigerator> getAllByBrand(@RequestParam @NotNull String brand){
        return refrigeratorService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<Refrigerator> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return refrigeratorService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<Refrigerator> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return refrigeratorService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredRefrigerator")
    public Map<String, Object> getFilteredRefrigerator(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return refrigeratorService.getFilteredRefrigerator(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return refrigeratorService.findAllDistinctData();
    }
}
