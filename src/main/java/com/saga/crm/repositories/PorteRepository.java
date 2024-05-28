package com.saga.crm.repositories;

import com.saga.crm.model.Porte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteRepository extends JpaRepository<Porte, Long> {
    Porte findPorteByTitulo(String titulo);
}
