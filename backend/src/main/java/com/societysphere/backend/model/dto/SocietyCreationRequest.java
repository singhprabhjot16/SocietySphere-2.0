package com.societysphere.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SocietyCreationRequest implements Serializable {

    private String societyName;
    private String societyAdminName;
    private String collegeAdminName;
    private String collegeAdminEmail;
    private String societyType;
    private String societyStatus;

}
