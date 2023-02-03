package com.b2c.Local.B2C.common.service;

import com.b2c.Local.B2C.products.electronic.service.*;
import com.b2c.Local.B2C.store.service.LocalStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchByKeywordService {

    LocalStoreService localStoreService;

    ACService acService;

    LaptopService laptopService;

    MobilePhoneService mobilePhoneService;

    RefrigeratorService refrigeratorService;

    TelevisionService televisionService;

    WashingMachineService washingMachineService;

    @Autowired
    public SearchByKeywordService(LocalStoreService localStoreService, ACService acService, LaptopService laptopService, MobilePhoneService mobilePhoneService, RefrigeratorService refrigeratorService, TelevisionService televisionService, WashingMachineService washingMachineService) {
        this.localStoreService = localStoreService;
        this.acService = acService;
        this.laptopService = laptopService;
        this.mobilePhoneService = mobilePhoneService;
        this.refrigeratorService = refrigeratorService;
        this.televisionService = televisionService;
        this.washingMachineService = washingMachineService;
    }

    public Map<String, Object> searchKeyword(String keyword, int page, int size){
        Map<String, Object> map = new HashMap<>();
        map.put("localStore", localStoreService.localStoreSearchKeyword(keyword, page, size));
        map.put("ac", acService.acSearchKeyword(keyword,page, size));
        map.put("laptop", laptopService.laptopSearchKeyword(keyword, page, size));
        map.put("mobilePhone", mobilePhoneService.mobilePhoneSearchKeyword(keyword, page, size));
        map.put("refrigerator", refrigeratorService.refrigeratorSearchKeyword(keyword, page, size));
        map.put("television",  televisionService.televisionSearchKeyword(keyword, page, size));
        map.put("washingMachine", washingMachineService.washingMachineSearchKeyword(keyword, page, size));
        return map;
    }

    public Map<String,Object> searchKeywordByIndexing(String keyword, int page,int size){
        Map<String,Object> map = new HashMap<>();
        map.put("ac", acService.searchKeywordInAc(keyword,page, size));
       // map.put("laptop", laptopService.laptopSearchKeyword(keyword, page, size));
       // map.put("mobilePhone", mobilePhoneService.mobilePhoneSearchKeyword(keyword, page, size));
       // map.put("refrigerator", refrigeratorService.refrigeratorSearchKeyword(keyword, page, size));
        //map.put("television",  televisionService.televisionSearchKeyword(keyword, page, size));
        //map.put("washingMachine", washingMachineService.washingMachineSearchKeyword(keyword, page, size));
        return map;
    }
}
