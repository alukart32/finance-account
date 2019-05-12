package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceNewDTO;
import com.r2d2.financeaccount.data.model.ProfitSource;
import com.r2d2.financeaccount.data.repository.ProfitSourceRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.services.service.ProfitSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfitSourceServiceImpl implements ProfitSourceService {

    @Autowired
    ProfitSourceRepository profitSourceRepository;

    @Autowired
    OrikaMapper mapper;

    @Override
    public ProfitSource getByName(String name) {
        return profitSourceRepository.findByName(name).
                orElseThrow(NotFoundException::new);
    }

    @Override
    public ProfitSourceDTO getById(Long id) {
        return mapper.map(profitSourceRepository.findById(id), ProfitSourceDTO.class);
    }

    @Override
    public Set<ProfitSourceDTO> getAll() {
        return mapper.mapAsSet(profitSourceRepository.findAll(), ProfitSourceDTO.class);
    }

    @Override
    public ProfitSourceDTO create(ProfitSourceNewDTO profitSourceNewDTO) {
        return null;
    }

    @Override
    public void update(Long personId, ProfitSourceNewDTO person) {

    }

    @Override
    public ProfitSource saveOrUpdate(ProfitSource profitSource) {
        return profitSourceRepository.save(profitSource);
    }

    @Override
    public void delete(Long id) {}
}
