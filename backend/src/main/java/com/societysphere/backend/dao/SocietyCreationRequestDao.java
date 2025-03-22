package com.societysphere.backend.dao;

import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyCreationRequestDao extends JpaRepository<SocietyCreationRequestDetail, Integer> {

    @Query("SELECT r FROM SocietyCreationRequestDetail r " +
            "WHERE r.requestedBy = :requestedBy " +
            "AND r.requestedTo = :requestedTo " +
            "AND r.societyName = :societyName")
    List<SocietyCreationRequestDetail> findExistingRequests(@Param("requestedBy") Integer requestedBy,
                                                            @Param("requestedTo") Integer requestedTo,
                                                            @Param("societyName") String societyName);

}
