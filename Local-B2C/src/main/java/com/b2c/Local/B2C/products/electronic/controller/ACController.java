package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.products.electronic.service.ACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products/ac")
public class ACController {

    ACService acService;

    @Autowired
    public ACController(ACService acService) {
        this.acService = acService;
    }

    @PostMapping("/add")
    public AC addAc(@RequestBody ACDto acDto) {
        return acService.addAc(acDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    public List<AC> getAllByStoreId(@PathVariable UUID uuid) {
        return acService.getAllByStoreId(uuid);
    }

    @PostMapping("/{id}/updateAcById")
    public AC updateAcById(@RequestBody ACDto acDto, @PathVariable Long id){
        return acService.updateAcById(acDto, id);
    }

    @PutMapping("/{id}/updateAcByIdByPut")
    public AC updateAcByIdByPut(@RequestBody ACDto acDto, @PathVariable Long id){
        return acService.updateAcByIdByPut(acDto, id);
    }

    @GetMapping("/{id}/getAcById")
    public AC getAcById(@PathVariable Long id){
        return acService.getAcById(id);
    }

    @GetMapping("/deactivateById")
    public String deactivateById(@RequestParam Long id){
        return acService.deactivateById(id);
    }
}
