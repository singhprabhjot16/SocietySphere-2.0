package com.societysphere.backend.controller;

import com.societysphere.backend.model.dto.SocietyDetailDTO;
import com.societysphere.backend.service.SocietyService;
import com.societysphere.backend.utilities.AppConstants;
import jakarta.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;

@Resource
@RestController
@RequestMapping(value = "api" + AppConstants.API_SEPARATOR)
public class SocietyResource {

    @Autowired
    private SocietyService societyService;

    @RequestMapping(method = RequestMethod.GET, value = "get")
    public SocietyDetailDTO getSocietyDetail(@RequestParam Integer societyId) {
        System.out.println("Request to Get Society Detail with ID " + societyId);
        return societyService.getSocietyDetail(societyId);
    }

}
