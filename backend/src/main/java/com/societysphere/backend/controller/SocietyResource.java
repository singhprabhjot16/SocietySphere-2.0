package com.societysphere.backend.controller;

import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.model.dto.response.CustomResponse;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppConstants;
import jakarta.annotation.Resource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Resource
@RestController
@RequestMapping(value = "api" + AppConstants.API_SEPARATOR + "society" + AppConstants.API_SEPARATOR)
public class SocietyResource {

    @Autowired
    private SocietyService societyService;

    // GET Mappings

    @RequestMapping(method = RequestMethod.GET, value = "get")
    public SocietyDetailDTO getSocietyDetail(@RequestParam Integer societyId) {
        System.out.println("Request to Get Society Detail with ID " + societyId);
        return societyService.getSocietyDetail(societyId);
    }

    // POST Mappings

    @RequestMapping(method = RequestMethod.POST, value = "create-request")
    @PostMapping(value = "create-request")
    public CustomResponse createSocietyCreationRequest(@RequestBody SocietyCreationRequest request) {
        log.info("Request to create society with society name: {}", request.getSocietyName());
        return societyService.createSocietyCreationRequest(request);
    }

}
