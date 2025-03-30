package com.societysphere.backend.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SOCIETY_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocietyDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOCIETY_ID")
    private Integer id;

    @Column(name = "SOCIETY_NAME", nullable = false)
    private String name;

    @Column(name = "SOCIETY_TAG")
    private String tag;

    @Column(name = "SOCIETY_PROFILE_ICON")
    private String profileIcon;

    @Column(name = "SOCIETY_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CONTACT_EMAIL", nullable = false)
    private String contactEmail;

    @Column(name = "CREATION_TIMESTAMP", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    @Column(name = "UPDATION_TIMESTAMP", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updationTimestamp;

    @Column(name = "JOINING_LINK")
    private String joiningLink;

//    private List<StudentDetail> studentDetails;

    //    @Column(name = "SOCIETY_ADMIN_ID", nullable = false)
//    @OneToMany()
    // for current students;

    //    @Column(name = "SOCIETY_ADMIN_ID", nullable = false)
//    @OneToMany()
    // for alumni

//    @OneToMany
//    private List<AchievementDetail> achievementDetails;
//
//    @OneToMany
//    private List<AnnouncementDetail> announcementDetails;
//
//    @OneToMany
//    private List<PhotoDetail> photoDetails;
//
//    @OneToMany
//    private List<FAQDetail> faqDetails;

}
