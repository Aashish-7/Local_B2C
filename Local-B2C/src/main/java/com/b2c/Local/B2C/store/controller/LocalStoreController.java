package com.b2c.Local.B2C.store.controller;

import com.b2c.Local.B2C.store.dto.LocalStoreDto;
import com.b2c.Local.B2C.store.model.LocalStore;
import com.b2c.Local.B2C.store.service.LocalStoreService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/localStore")
@Hidden
public class LocalStoreController {

    LocalStoreService localStoreService;

    @Autowired
    public LocalStoreController(LocalStoreService localStoreService) {
        this.localStoreService = localStoreService;
    }

    @PostMapping("/addStore")
    public LocalStore addStore(@RequestBody @Valid LocalStoreDto localStoreDto){
        return localStoreService.addStore(localStoreDto);
    }

    @GetMapping("/listAllStores")
    public List<LocalStore> listAllStores(){
        return localStoreService.listAllStores();
    }
}
