package com.societysphere.backend.dao;

import com.societysphere.backend.model.data.CollegeAdminDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeAdminDao extends JpaRepository<CollegeAdminDetail, Integer> {

    @Query("SELECT a FROM CollegeAdminDetail a WHERE a.adminEmail = :adminEmail")
    public List<CollegeAdminDetail> getCollegeAdmins(@Param("adminEmail") String adminEmail);

}
