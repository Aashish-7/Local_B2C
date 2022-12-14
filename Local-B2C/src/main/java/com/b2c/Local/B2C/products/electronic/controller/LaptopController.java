package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.LaptopDto;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.products.electronic.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products/laptop")
public class LaptopController {

    LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop addLaptop(@RequestBody LaptopDto laptopDto){
        return laptopService.addLaptop(laptopDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Laptop> getAllByStoreId(@PathVariable UUID uuid) {
        return laptopService.getAllByStoreId(uuid);
    }

    @PutMapping("{id}/updateLaptopById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop updateLaptopById(@RequestBody LaptopDto laptopDto, @PathVariable Long id){
        return laptopService.updateLaptopById(laptopDto, id);
    }

    @GetMapping("/{id}/getLaptopById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop getLaptopById(@PathVariable Long id){
        return laptopService.getLaptopById(id);
    }

    @GetMapping("/{id}/deactivateById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return laptopService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Laptop> getAllByModel(@RequestParam @NotNull String model){
        return laptopService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Laptop> getAllByBrand(@RequestParam @NotNull String brand){
        return laptopService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<Laptop> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return laptopService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<Laptop> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return laptopService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredLaptop")
    public Map<String, Object> getFilteredLaptop(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return laptopService.getFilteredLaptop(page, size, electronicFilterDto);
    }
    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return laptopService.findAllDistinctData();
    }
}
