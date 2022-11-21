package com.b2c.Local.B2C.products.electronic.controller;

import com.b2c.Local.B2C.products.electronic.dto.ACDto;
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
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
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

    @GetMapping("/{id}/deactivateById")
    public String deactivateById(@PathVariable Long id){
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
}
