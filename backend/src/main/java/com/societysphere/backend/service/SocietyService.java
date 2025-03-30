package com.societysphere.backend.service;

import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import com.societysphere.backend.model.data.SocietyDetail;
import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.response.CustomResponse;

import java.util.List;

public interface SocietyService {

    public CustomResponse createSocietyCreationRequest(SocietyCreationRequest request);

    public List<SocietyCreationRequestDetail> getAllRequests(Integer requestedBy);

    public List<SocietyDetail> getAllSocieties();

    public SocietyDetail getSociety(Integer id);

    public Boolean rejectSocietyCreationRequest(Integer requestId);

}
