package com.b2c.Local.B2C.securities.controller;

import com.b2c.Local.B2C.securities.base.ManagementInterface;
import com.b2c.Local.B2C.securities.service.ManagementService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@PreAuthorize("hasAnyAuthority('admin')")
@RequestMapping("/managementFactory")
@Hidden
public class ManagementController implements ManagementInterface {

    ManagementService managementService;

    @Autowired
    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }


    @Override @GetMapping("/getRuntime")
    public Map<String, Object> getRuntime() {
        return managementService.getRuntime();
    }

    @Override @GetMapping("/getThreadsDetails")
    public Map<String, Object> getThreadsDetails() {
        return managementService.getThreadsDetails();
    }

    @Override @GetMapping("/getMemoryDetails")
    public Map<String, Object> getMemoryDetails() {
        return managementService.getMemoryDetails();
    }
}
