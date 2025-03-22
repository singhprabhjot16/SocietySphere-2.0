package com.societysphere.backend.service.impl;

import com.societysphere.backend.dao.CollegeAdminDao;
import com.societysphere.backend.dao.SocietyCreationRequestDao;
import com.societysphere.backend.model.data.CollegeAdminDetail;
import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.model.dto.response.CustomResponse;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppConstants;
import com.societysphere.backend.utilities.AppUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private CollegeAdminDao collegeAdminDao;

    @Autowired
    private SocietyCreationRequestDao creationRequestDao;

    @Override
    public CustomResponse createSocietyCreationRequest(SocietyCreationRequest request) {
        CustomResponse response = new CustomResponse();
        List<CollegeAdminDetail> collegeAdminDetail = collegeAdminDao.getCollegeAdmins(request.getCollegeAdminEmail());
        List<SocietyCreationRequestDetail> requestDetails = new ArrayList<>();
        if (!collegeAdminDetail.isEmpty()) {
            requestDetails = creationRequestDao.findExistingRequests(request.getSocietyAdminId(),
                    collegeAdminDetail.get(0).getId(), request.getSocietyName());
        }
        if (!requestDetails.isEmpty()) {
            response.setStatus(Boolean.FALSE);
            response.setMessage("Request Already Exists!");
            return response;
        }
        if (!collegeAdminDetail.isEmpty()) {
            Boolean isEmailSent = appUtils.sendEmail("${spring.mail.username}", collegeAdminDetail.get(0).getAdminEmail(),
                    "Sample", "Hello!");
            if (Boolean.TRUE.equals(isEmailSent)) {
                SocietyCreationRequestDetail requestDetail = new SocietyCreationRequestDetail();
                requestDetail.setRequestedBy(request.getSocietyAdminId());
                requestDetail.setRequestedTo(collegeAdminDetail.get(0).getId());
                requestDetail.setRequestTimestamp(appUtils.getCurrentTime());
                requestDetail.setStatus(AppConstants.CREATED);
                requestDetail.setSocietyName(request.getSocietyName());
                creationRequestDao.save(requestDetail);

                response.setStatus(Boolean.TRUE);
                response.setMessage("Request Created Successfully!");
                return response;
            }
        }
        response.setStatus(Boolean.FALSE);
        response.setMessage("Error Creating Request! Try Again Later.");
        return response;
    }

    @Override
    public SocietyDetailDTO getSocietyDetail(Integer societyId) {
        SocietyDetailDTO obj = new SocietyDetailDTO();
        obj.setId(10);
        obj.setName("Sample");
        return obj;
    }

}
