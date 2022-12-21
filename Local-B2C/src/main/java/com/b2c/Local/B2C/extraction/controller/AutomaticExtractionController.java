package com.b2c.Local.B2C.extraction.controller;

import com.b2c.Local.B2C.extraction.service.AutomaticExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class AutomaticExtractionController {

    AutomaticExtractionService automaticExtractionService;

    @Autowired
    public AutomaticExtractionController(AutomaticExtractionService automaticExtractionService) {
        this.automaticExtractionService = automaticExtractionService;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getFile() throws IOException {
        String fileName = "ac.xlsx";
        InputStreamResource file = new InputStreamResource(automaticExtractionService.load());
        //InputStreamReader file = new InputStreamReader(automaticExtractionService.load());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +fileName);
        header.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        header.setContentLength(file.contentLength());
        return ResponseEntity.ok(file);
    }
}
