package com.b2c.Local.B2C.securities.controller;

import com.b2c.Local.B2C.securities.dto.FilterRequestInfo;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import com.b2c.Local.B2C.securities.model.RequestResponseBody;
import com.b2c.Local.B2C.securities.service.FilteringDataService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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


    @GetMapping("user/getActiveSessionId")
    public Set<String> getActiveSessionId() {
        return filteringDataService.getActiveSessionId();
    }

    @PostMapping("user/destroyAllSession")
    public void destroyAllSession() {
        filteringDataService.destroyAllSession();
    }

    @GetMapping("{requestId}/getRequestsByRequestId")
    public List<FilterRequest> getRequestsByRequestId(@PathVariable String requestId, @RequestParam String url) {
        return filteringDataService.getRequestsByRequestId(requestId, url);
    }

    @GetMapping("user/getAllRequestIdByUser")
    public List<FilterRequestInfo> getAllRequestIdByUser() {
        return filteringDataService.getAllRequestIdByUser();
    }

    @GetMapping("user/getAllActiveSessionDetails")
    public Map<String, List<FilterRequest>> getAllActiveSessionDetails() {
        return filteringDataService.getAllActiveSessionDetails();
    }

    @GetMapping("user/getFilterRequestBySessionId")
    public Set<FilterRequest> getFilterRequestBySessionId(@RequestParam String sessionId) {
        return filteringDataService.getFilterRequestBySessionId(sessionId);
    }

    @GetMapping("admin/getAllNewSessionRequest")
    public List<FilterRequest> getAllNewSessionRequest(){
        return filteringDataService.getAllNewSessionRequest();
    }

    @GetMapping("admin/{filterRequestId}/getRequestResponseBodyByFilterRequestId")
    public RequestResponseBody getRequestResponseBodyByFilterRequestId(@PathVariable String filterRequestId){
        return filteringDataService.getRequestResponseBodyByFilterRequestId(filterRequestId);
    }

    @GetMapping("admin/getAllByRemoteIp")
    public List<FilterRequest> getAllByRemoteIp(@RequestParam String remoteIp){
        return filteringDataService.getAllByRemoteIp(remoteIp);
    }

    @GetMapping("admin/getAllByMacAddress")
    public List<FilterRequest> getAllByMacAddress(@RequestParam String macAddress){
        return filteringDataService.getAllByMacAddress(macAddress);
    }

}
