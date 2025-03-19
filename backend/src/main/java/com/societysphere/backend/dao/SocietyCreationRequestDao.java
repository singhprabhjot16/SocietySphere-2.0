package com.societysphere.backend.dao;

import com.societysphere.backend.model.data.SocietyCreationRequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocietyCreationRequestDao extends JpaRepository<SocietyCreationRequestDetail, Integer> {
}
