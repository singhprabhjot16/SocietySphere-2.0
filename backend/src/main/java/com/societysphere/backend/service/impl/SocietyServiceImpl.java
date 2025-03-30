package com.societysphere.backend.service.impl;

import com.societysphere.backend.dao.CollegeAdminDao;
import com.societysphere.backend.dao.SocietyCreationRequestDao;
import com.societysphere.backend.dao.SocietyDetailDao;
import com.societysphere.backend.model.data.CollegeAdminDetail;
import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import com.societysphere.backend.model.data.SocietyDetail;
import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.response.CustomResponse;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppConstants;
import com.societysphere.backend.utilities.AppUtils;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private CollegeAdminDao collegeAdminDao;

    @Autowired
    private SocietyCreationRequestDao creationRequestDao;

    @Autowired
    private SocietyDetailDao societyDao;

    @Override
    @Transactional
    public CustomResponse createSocietyCreationRequest(SocietyCreationRequest request) {
        CustomResponse response = new CustomResponse();
        List<CollegeAdminDetail> collegeAdminDetail = collegeAdminDao.getCollegeAdmins(request.getCollegeAdminEmail());
        if (!collegeAdminDetail.isEmpty()) {
            List<SocietyCreationRequestDetail> requestDetails = creationRequestDao.findExistingRequests(
                    request.getSocietyAdminId(), collegeAdminDetail.get(0).getId(), request.getSocietyName(),
                    new ArrayList<>(Arrays.asList(AppConstants.FAILED, AppConstants.REJECTED)));
            if (!requestDetails.isEmpty()) {
                response.setStatus(Boolean.FALSE);
                response.setMessage("Request Already Exists!");
                return response;
            }
        }
        SocietyCreationRequestDetail creationRequest = new SocietyCreationRequestDetail();
        if (!collegeAdminDetail.isEmpty()) {
            creationRequest.setRequestedBy(request.getSocietyAdminId());
            creationRequest.setRequestedTo(collegeAdminDetail.get(0).getId());
            creationRequest.setSocietyName(request.getSocietyName());
            creationRequest.setRequestTimestamp(appUtils.getCurrentTime());
            creationRequest.setStatus(AppConstants.CREATED);
            creationRequestDao.save(creationRequest);

            Boolean isEmailSent = appUtils.sendVerificationEmail(request, collegeAdminDetail, creationRequest);

            if (Boolean.TRUE.equals(isEmailSent)) {
                response.setStatus(Boolean.TRUE);
                response.setMessage("Request Submitted Successfully!");
                return response;
            }
        }
        creationRequest.setStatus(AppConstants.FAILED);
        response.setStatus(Boolean.FALSE);
        response.setMessage("Error Creating Request! Try Again Later.");
        return response;
    }

    @Override
    public List<SocietyCreationRequestDetail> getAllRequests(Integer requestedBy) {
        return creationRequestDao.getAllRequests(requestedBy);
    }

    @Override
    public List<SocietyDetail> getAllSocieties() {
        return societyDao.getAllSocieties(AppConstants.ACTIVE);
    }

    @Override
    public SocietyDetail getSociety(Integer id) {
        return societyDao.getSociety(id, AppConstants.ACTIVE);
    }

    @Override
    @Transactional
    public Boolean rejectSocietyCreationRequest(Integer requestId) {
        Optional<SocietyCreationRequestDetail> societyCreationRequest = creationRequestDao.findById(requestId);
        if (societyCreationRequest.isPresent()) {
            SocietyCreationRequestDetail societyCreationRequestDetail = societyCreationRequest.get();
            societyCreationRequestDetail.setStatus(AppConstants.REJECTED);
            societyCreationRequestDetail.setRejectionTimestamp(appUtils.getCurrentTime());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
}
