package com.b2c.Local.B2C.store.controller;

import com.b2c.Local.B2C.products.electronic.model.*;
import com.b2c.Local.B2C.store.dto.LocalStoreDto;
import com.b2c.Local.B2C.store.model.LocalStore;
import com.b2c.Local.B2C.store.service.LocalStoreService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/localStore/0")
@Hidden
@CacheConfig(cacheNames = {"LocalStoreController"})
public class LocalStoreController {

    LocalStoreService localStoreService;

    @Autowired
    public LocalStoreController(LocalStoreService localStoreService) {
        this.localStoreService = localStoreService;
    }

    @PostMapping("/addStore")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public LocalStore addStore(@RequestBody @Valid LocalStoreDto localStoreDto){
        return localStoreService.addStore(localStoreDto);
    }

    @GetMapping("/listAllStores")
    public List<LocalStore> listAllStores(){
        return localStoreService.listAllStores();
    }

    @GetMapping("/getById")
    public LocalStore getById(@RequestParam UUID uuid){
        return localStoreService.getById(uuid);
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam UUID uuid){
        return localStoreService.deleteById(uuid);
    }

    @GetMapping("/deactivateById")
    public String deactivateById(@RequestParam UUID uuid){
        return localStoreService.deactivateById(uuid);
    }

    @DeleteMapping("/deleteAllStoreByUserId")
    public String deleteAllStoreByUserId(){
        return localStoreService.deleteAllStoreByUserId();
    }

    @PostMapping("/addProductByStoreId")
    public LocalStore addProductByStoreId(@RequestParam UUID uuid, @RequestParam String product){
        return localStoreService.addProductByStoreId(uuid, product);
    }

    @Cacheable(value = "findStoreByPinCode", key = "#pinCode")
    @GetMapping("/findByPinCode")
    public List<LocalStore> findStoreByPinCode(@RequestParam int pinCode){
        return localStoreService.findStoreByPinCode(pinCode);
    }

    @Cacheable(value = "findStoreByCity", key = "#city")
    @GetMapping("/findByCity")
    public List<LocalStore> findStoreByCity(@RequestParam String city){
        return localStoreService.findStoreByCity(city);
    }

    @GetMapping("/{uuid}/getAllProductInLocalStoreById")     @Cacheable(value = "getAllProductInLocalStoreById", key = "#uuid")
    public Map<String, Object> getAllProductInLocalStoreById(@PathVariable UUID uuid){
        return localStoreService.getAllProductInLocalStoreById(uuid);
    }

    @GetMapping("/{uuid}/getAllAcByLocalStoreId")
    public List<AC> getAllAcByLocalStoreId(@PathVariable UUID uuid){
       return localStoreService.getAllAcByLocalStoreId(uuid);
    }

    @GetMapping("/{uuid}/getAllLaptopByLocalStoreId")
    public List<Laptop> getAllLaptopByLocalStoreId(@PathVariable UUID uuid){
        return localStoreService.getAllLaptopByLocalStoreId(uuid);
    }


    @GetMapping("/{uuid}/getAllMobilePhoneByLocalStoreId")
    public List<MobilePhone> getAllMobilePhoneByLocalStoreId(@PathVariable UUID uuid){
        return localStoreService.getAllMobilePhoneByLocalStoreId(uuid);
    }

    @GetMapping("/{uuid}/getAllRefrigeratorByLocalStoreId")
    public List<Refrigerator> getAllRefrigeratorByLocalStoreId(@PathVariable UUID uuid){
        return localStoreService.getAllRefrigeratorByLocalStoreId(uuid);
    }

    @GetMapping("/{uuid}/getAllTelevisionByLocalStoreId")
    public List<Television> getAllTelevisionByLocalStoreId(@PathVariable UUID uuid){
        return localStoreService.getAllTelevisionByLocalStoreId(uuid);
    }

    @GetMapping("/{uuid}/getAllWashingMachineByLocalStoreId")
    public List<WashingMachine> getAllWashingMachineByLocalStoreId(@PathVariable UUID uuid){
        return localStoreService.getAllWashingMachineByLocalStoreId(uuid);
    }


}
