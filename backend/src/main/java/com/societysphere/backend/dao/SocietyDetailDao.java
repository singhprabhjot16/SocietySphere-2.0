package com.societysphere.backend.dao;

import com.societysphere.backend.model.data.SocietyDetail;
import com.societysphere.backend.utilities.AppUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyDetailDao extends JpaRepository<SocietyDetail, Integer> {

    @Query("SELECT s FROM SocietyDetail s WHERE s.status = :status")
    public List<SocietyDetail> getAllSocieties(@Param("status") String status);

    @Query("SELECT s FROM SocietyDetail s WHERE s.id = :id AND s.status = :status")
    public SocietyDetail getSociety(@Param("id") Integer id, @Param("status") String status);

}
