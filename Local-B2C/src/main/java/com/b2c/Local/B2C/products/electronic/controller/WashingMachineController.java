package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.WashingMachineDto;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.b2c.Local.B2C.products.electronic.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
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

    @GetMapping("{id}/deactivateById")
    public String deactivateById(@PathVariable Long id){
        return washingMachineService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<WashingMachine> getAllByModel(@RequestParam @NotNull String model){
        return washingMachineService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<WashingMachine> getAllByBrand(@RequestParam @NotNull String brand){
        return washingMachineService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<WashingMachine> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return washingMachineService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<WashingMachine> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return washingMachineService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredWashingMachine")
    public Map<String, Object> getFilteredWashingMachine(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return washingMachineService.getFilteredWashingMachine(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return washingMachineService.findAllDistinctData();
    }
}
