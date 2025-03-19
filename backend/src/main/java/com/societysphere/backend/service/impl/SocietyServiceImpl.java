package com.societysphere.backend.service.impl;

import com.societysphere.backend.dao.CollegeAdminDao;
import com.societysphere.backend.model.data.CollegeAdminDetail;
import com.societysphere.backend.model.dto.SocietyCreationRequest;
import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private CollegeAdminDao collegeAdminDao;

    @Override
    public Boolean createSocietyCreationRequest(SocietyCreationRequest request) {
        List<CollegeAdminDetail> collegeAdminDetail = collegeAdminDao.getCollegeAdmins(request.getCollegeAdminEmail());
        if (Objects.nonNull(collegeAdminDetail) && !collegeAdminDetail.isEmpty()) {
            return appUtils.sendEmail("${spring.mail.username}", collegeAdminDetail.get(0).getAdminEmail(),
                    "Sample", "Hello!");
        }
        return Boolean.FALSE;
    }

    @Override
    public SocietyDetailDTO getSocietyDetail(Integer societyId) {
        SocietyDetailDTO obj = new SocietyDetailDTO();
        obj.setId(10);
        obj.setName("Sample");
        return obj;
    }

}
