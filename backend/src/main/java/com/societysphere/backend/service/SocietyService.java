package com.societysphere.backend.service;

import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.model.dto.response.CustomResponse;

public interface SocietyService {

    public CustomResponse createSocietyCreationRequest(SocietyCreationRequest request);

    public SocietyDetailDTO getSocietyDetail(Integer societyId);

}
