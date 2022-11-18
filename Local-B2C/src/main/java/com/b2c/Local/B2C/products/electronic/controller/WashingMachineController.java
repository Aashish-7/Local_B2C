package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.WashingMachineDto;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.b2c.Local.B2C.products.electronic.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products/washingMachine")
public class WashingMachineController {

    WashingMachineService washingMachineService;

    @Autowired
    public WashingMachineController(WashingMachineService washingMachineService) {
        this.washingMachineService = washingMachineService;
    }

    @PostMapping("/add")
    public WashingMachine addWashingMachine(@RequestBody WashingMachineDto washingMachineDto){
        return washingMachineService.addWashingMachine(washingMachineDto);
    }

    @GetMapping("/{uuid}/getAllByStoreId")
    public List<WashingMachine> getAllByStoreId(@PathVariable UUID uuid) {
        return washingMachineService.getAllByStoreId(uuid);
    }

    @PutMapping("/{id}/updateWashingMachineById")
    public WashingMachine updateWashingMachineById(@RequestBody WashingMachineDto washingMachineDto, @PathVariable Long id){
        return washingMachineService.updateWashingMachineById(washingMachineDto, id);
    }

    @GetMapping("/{id}/getWashingMachineById")
    public WashingMachine getWashingMachineById(@PathVariable Long id){
        return washingMachineService.getWashingMachineById(id);
    }

    @GetMapping("/deactivateById")
    public String deactivateById(@RequestParam Long id){
        return washingMachineService.deactivateById(id);
    }
}
