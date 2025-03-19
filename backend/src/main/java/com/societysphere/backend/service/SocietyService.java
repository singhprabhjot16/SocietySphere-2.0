package com.societysphere.backend.service;

import com.societysphere.backend.model.dto.SocietyCreationRequest;
import com.societysphere.backend.model.dto.SocietyDetailDTO;

public interface SocietyService {

    public Boolean createSocietyCreationRequest(SocietyCreationRequest request);

    public SocietyDetailDTO getSocietyDetail(Integer societyId);

}
