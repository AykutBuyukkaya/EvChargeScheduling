package com.buyukkaya.evdataservice.domain.service.impl;

import com.buyukkaya.evdataservice.domain.mapper.BrandMapper;
import com.buyukkaya.evdataservice.domain.model.dto.BrandDto;
import com.buyukkaya.evdataservice.domain.repository.BrandRepository;
import com.buyukkaya.evdataservice.domain.service.BrandService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }
}
