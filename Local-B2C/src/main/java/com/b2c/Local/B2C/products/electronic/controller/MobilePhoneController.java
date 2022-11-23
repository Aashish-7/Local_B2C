package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.MobilePhoneDto;
import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import com.b2c.Local.B2C.products.electronic.service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products/mobilePhone")
public class MobilePhoneController {

    MobilePhoneService mobilePhoneService;

    @Autowired
    public MobilePhoneController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping("/add")
    public MobilePhone addMobilePhone(@RequestBody MobilePhoneDto mobilePhoneDto){
        return mobilePhoneService.addMobilePhone(mobilePhoneDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    public List<MobilePhone> getAllByStoreId(@PathVariable UUID uuid) {
        return mobilePhoneService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateMobilePhoneById")
    public MobilePhone updateMobilePhoneById(@RequestBody MobilePhoneDto mobilePhoneDto, @PathVariable Long id){
        return mobilePhoneService.updateMobilePhoneById(mobilePhoneDto, id);
    }

    @GetMapping("/{id}/getMobilePhoneById")
    public MobilePhone getMobilePhoneById(@PathVariable Long id){
        return mobilePhoneService.getMobilePhoneById(id);
    }

    @GetMapping("/{id}/deactivateById")
    public String deactivateById(@PathVariable Long id){
        return mobilePhoneService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<MobilePhone> getAllByModel(@RequestParam @NotNull String model){
        return mobilePhoneService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<MobilePhone> getAllByBrand(@RequestParam @NotNull String brand){
        return mobilePhoneService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<MobilePhone> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return mobilePhoneService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<MobilePhone> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return mobilePhoneService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredMobilePhone")
    public Map<String, Object> getFilteredMobilePhone(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return mobilePhoneService.getFilteredMobilePhone(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return mobilePhoneService.findAllDistinctData();
    }

}
