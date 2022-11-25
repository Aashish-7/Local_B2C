package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.products.electronic.service.ACService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products/ac")
public class ACController {

    ACService acService;

    @Autowired
    public ACController(ACService acService) {
        this.acService = acService;
    }

    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PostMapping("/add")
    public AC addAc(@RequestBody ACDto acDto) {
        return acService.addAc(acDto);
    }

    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @GetMapping("/{uuid}/getAllByStoreId")
    public List<AC> getAllByStoreId(@PathVariable UUID uuid) {
        return acService.getAllByStoreId(uuid);
    }

    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PostMapping("/{id}/updateAcById")
    public AC updateAcById(@RequestBody ACDto acDto, @PathVariable Long id) {
        return acService.updateAcById(acDto, id);
    }

    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PutMapping("/{id}/updateAcByIdByPut")
    public AC updateAcByIdByPut(@RequestBody ACDto acDto, @PathVariable Long id) {
        return acService.updateAcByIdByPut(acDto, id);
    }

    @GetMapping("/{id}/getAcById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public AC getAcById(@PathVariable Long id) {
        return acService.getAcById(id);
    }

    @GetMapping("/{id}/deactivateById")
    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id) {
        return acService.deactivateById(id);
    }

    @GetMapping("/{id}/downloadACByLocalStoreId/pdf")
    public ResponseEntity<Resource> downloadACbyLocalStoreId(@PathVariable UUID id) throws DocumentException {
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(acService.downloadACbyLocalStoreId(id).readAllBytes()));
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + id + ".pdf");
        header.setContentType(MediaType.parseMediaType("application/pdf"));
        header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<Resource> response = new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getAllByModel")
    public List<AC> getAllByModel(@RequestParam @NotNull String model) {
        return acService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<AC> getAllByBrand(@RequestParam @NotNull String brand) {
        return acService.getAllByBrand(brand);
    }

    @GetMapping("/getAllByBrandAndPincode")
    public List<AC> getAllByBrandAndPincode(@RequestParam @NotNull String brand, @RequestParam @NotNull int pincode) {
        return acService.getAllByBrandAndPincode(brand, pincode);
    }

    @GetMapping("/getAllByModelAndPincode")
    public List<AC> getAllByModelAndPincode(@RequestParam @NotNull String model, @RequestParam @NotNull int pincode) {
        return acService.getAllByModelAndPincode(model, pincode);
    }

    @GetMapping("/getFilteredAc")
    public Map<String, Object> getFilteredAc(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return acService.getFilteredAc(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return acService.findAllDistinctData();
    }
}
