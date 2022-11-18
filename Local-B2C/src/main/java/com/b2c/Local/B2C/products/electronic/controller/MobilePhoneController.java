package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.MobilePhoneDto;
import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import com.b2c.Local.B2C.products.electronic.service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/deactivateById")
    public String deactivateById(@RequestParam Long id){
        return mobilePhoneService.deactivateById(id);
    }
}
