package com.saga.crm.repositories;

import com.saga.crm.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    List<Checklist> findByStatus(Integer status);
}
