package com.societysphere.backend.service.impl;

import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.service.SocietyService;
import org.springframework.stereotype.Service;

@Service
public class SocietyServiceImpl implements SocietyService {

    @Override
    public SocietyDetailDTO getSocietyDetail(Integer societyId) {
        return new SocietyDetailDTO();
    }
}
