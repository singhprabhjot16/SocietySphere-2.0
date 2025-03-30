package com.societysphere.backend.model.data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "STUDENT_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Integer id;

    @Column(name = "STUDENT_NAME", nullable = false)
    private String name;

    @Column(name = "STUDENT_EMAIL", nullable = false)
    private String email;

    @Column(name = "STUDENT_PROFILE_ICON")
    private String profileIcon;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "LINKED_IN_PROFILE_LINK")
    private String linkedInProfileLink;

    @Column

    @ManyToMany
    @JoinTable(
            name = "STUDENT_SOCIETY_MAPPING_DETAIL",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SOCIETY_ID")
    )
    private List<SocietyDetail> societyDetails;

}
