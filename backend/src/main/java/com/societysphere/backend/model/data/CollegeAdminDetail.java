package com.societysphere.backend.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "COLLEGE_ADMIN_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollegeAdminDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADMIN_NAME")
    private String adminName;

    @Column(name = "ADMIN_EMAIL")
    private String adminEmail;

}
