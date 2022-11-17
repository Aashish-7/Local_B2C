package com.b2c.Local.B2C.securities.controller;

import com.b2c.Local.B2C.securities.model.FilterRequest;
import com.b2c.Local.B2C.securities.service.FilteringDataService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@Hidden
@RequestMapping("filter")
public class FilterRequestController {

    FilteringDataService filteringDataService;

    @Autowired
    public FilterRequestController(FilteringDataService filteringDataService) {
        this.filteringDataService = filteringDataService;
    }

    @GetMapping("getAllSessionId")
    public List<FilterRequest> getAllSessionId() {
        return filteringDataService.getAllSessionId();
    }

    @GetMapping("{id}/getBySessionId")
    public List<FilterRequest> getBySessionId(@PathVariable String id) {
        return filteringDataService.getBySessionId(id);
    }

    @GetMapping("user/getActiveSessionId")
    public Set<String> getActiveSessionId() {
        return filteringDataService.getActiveSessionId();
    }

    @PostMapping("user/destroyAllSession")
    public void destroyAllSession() {
        filteringDataService.destroyAllSession();
    }
}
