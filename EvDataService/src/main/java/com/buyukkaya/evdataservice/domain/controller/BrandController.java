package com.buyukkaya.evdataservice.domain.controller;

import com.buyukkaya.evdataservice.domain.model.response.GetBrandResponse;
import com.buyukkaya.evdataservice.domain.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<GetBrandResponse> getAllBrands() {
        return ResponseEntity.ok(GetBrandResponse.builder()
                .brandList(brandService.getAllBrands())
                .timestamp(Instant.now())
                .build());
    }

}
