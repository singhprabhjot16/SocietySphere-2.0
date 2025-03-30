package com.societysphere.backend.controller;

import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import com.societysphere.backend.model.data.SocietyDetail;
import com.societysphere.backend.model.dto.requests.SocietyCreationRequest;
import com.societysphere.backend.model.dto.response.CustomResponse;
import com.societysphere.backend.model.enums.SocietyTypes;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppConstants;
import jakarta.annotation.Resource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@Resource
@RestController
@RequestMapping(value = "api" + AppConstants.API_SEPARATOR + "society" + AppConstants.API_SEPARATOR)
public class SocietyResource {

    @Autowired
    private SocietyService societyService;

    // GET Mappings

    @GetMapping(value = "get-all")
    public List<SocietyDetail> getAllSocieties() {
        log.info("Request to get all societies");
        return societyService.getAllSocieties();
    }

    @GetMapping(value = "get")
    public SocietyDetail getSociety(@RequestParam Integer id) {
        System.out.println("Request to get society detail with ID " + id);
        return societyService.getSociety(id);
    }

    @GetMapping(value = "get-all-requests")
    public List<SocietyCreationRequestDetail> getAllRequests(@RequestParam Integer requestedBy) {
        log.info("Requests to get all society creation requests by student ID: {}", requestedBy);
        return societyService.getAllRequests(requestedBy);
    }

    @GetMapping(value = "get-all-society-types")
    public SocietyTypes[] getAllSocietyTypes() {
        log.info("Request to get all society types");
        return SocietyTypes.values();
    }

    // POST Mappings

    @PostMapping(value = "create-request")
    public CustomResponse createSocietyCreationRequest(@RequestBody SocietyCreationRequest request) {
        log.info("Request to create society with society name: {}", request.getSocietyName());
        return societyService.createSocietyCreationRequest(request);
    }

    @PostMapping(value = "accept-request")
    public Boolean acceptSocietyCreationRequest(@RequestParam Integer requestId) {
        return Boolean.TRUE;
    }

    // Check why GET was working and POST is not
    @GetMapping(value = "reject-request")
    public Boolean rejectSocietyCreationRequest(@RequestParam Integer requestId) {
        log.info("Request to reject society creation request with request ID: {}", requestId);
        return societyService.rejectSocietyCreationRequest(requestId);
    }

}
