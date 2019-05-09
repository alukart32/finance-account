package com.r2d2.financeaccount.controller.model;

import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceNewDTO;
import com.r2d2.financeaccount.services.service.ProfitSourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/profit/source/")
public class ProfitSourceController {

    ProfitSourceService profitSourceService;

    public ProfitSourceController(ProfitSourceService profitSourceService) {
        this.profitSourceService = profitSourceService;
    }

    @RequestMapping("default")
    public ResponseEntity<Set<ProfitSourceDTO>> getDefault(){
        Set<ProfitSourceDTO> category = profitSourceService.getAll();
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<ProfitSourceDTO> get(@PathVariable("id") Long id){
        ProfitSourceDTO profitSource = profitSourceService.getById(id);
        return new ResponseEntity(profitSource, HttpStatus.OK);
    }
}