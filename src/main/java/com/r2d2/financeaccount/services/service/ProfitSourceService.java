package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceNewDTO;
import com.r2d2.financeaccount.data.model.ProfitSource;

import java.util.Set;

public interface ProfitSourceService {

    ProfitSourceDTO getById(Long id);

    Set<ProfitSourceDTO> getAll();

    ProfitSourceDTO create(ProfitSourceNewDTO profitSourceNewDTO);

    void update(Long personId, ProfitSourceNewDTO person);

    ProfitSource saveOrUpdate(ProfitSource profitSource);

    void delete(Long id);
}
