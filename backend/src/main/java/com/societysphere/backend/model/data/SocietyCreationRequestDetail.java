package com.societysphere.backend.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SOCIETY_CREATION_REQUEST_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SocietyCreationRequestDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID")
    private Integer requestId;

    @Column(name = "REQUESTED_BY", nullable = false)
    private Integer requestedBy;

    @Column(name = "REQUESTED_TO", nullable = false)
    private Integer requestedTo;

    @Column(name = "SOCIETY_NAME", nullable = false)
    private String societyName;

    @Column(name = "REQUEST_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTimestamp;

    @Column(name = "APPROVAL_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalTimestamp;

    @Column(name = "STATUS", nullable = false)
    private String status;

}
