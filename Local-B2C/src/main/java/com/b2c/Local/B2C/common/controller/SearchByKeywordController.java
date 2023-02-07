package com.b2c.Local.B2C.common.controller;

import com.b2c.Local.B2C.common.service.SearchByKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController @Validated
@RequestMapping("/search")
public class SearchByKeywordController {

    SearchByKeywordService searchByKeywordService;

    @Autowired
    public SearchByKeywordController(SearchByKeywordService searchByKeywordService) {
        this.searchByKeywordService = searchByKeywordService;
    }

    @GetMapping("/searchKeyword")
    public Map<String, Object> searchKeyword(@RequestParam String keyword, @RequestParam int page, @RequestParam int size){
        return searchByKeywordService.searchKeyword(keyword, page, size);
    }

    @GetMapping("/searchKeywordByIndexing")
    public Map<String, Object> searchKeywordByIndexing(@RequestParam @Size(min = 2) String keyword, @RequestParam @Min(0) int page, @RequestParam @Min(1) int size){
        return searchByKeywordService.searchKeywordByIndexing(keyword, page, size);
    }
}
