package com.b2c.Local.B2C.securities.controller;

import com.b2c.Local.B2C.securities.dto.FilterRequestInfo;
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

    @GetMapping("getAllSessionRequests")
    public List<FilterRequest> getAllSessionRequests() {
        return filteringDataService.getAllSessionRequests();
    }

    @GetMapping("{id}/getFilterRequestBySessionId")
    public List<FilterRequest> getFilterRequestBySessionId(@PathVariable String id) {
        return filteringDataService.getFilterRequestBySessionId(id);
    }

    @GetMapping("user/getActiveSessionId")
    public Set<String> getActiveSessionId() {
        return filteringDataService.getActiveSessionId();
    }

    @PostMapping("user/destroyAllSession")
    public void destroyAllSession() {
        filteringDataService.destroyAllSession();
    }

    @GetMapping("{sessionId}/getRequestsBySessionId")
    public List<FilterRequest> getRequestsBySessionId(@PathVariable String sessionId, @RequestParam String url) {
        return filteringDataService.getRequestsBySessionId(sessionId, url);
    }

    @GetMapping("user/getAllSessionsByUser")
    public List<FilterRequestInfo> getAllSessionsByUser() {
        return filteringDataService.getAllSessionsByUser();
    }
}
