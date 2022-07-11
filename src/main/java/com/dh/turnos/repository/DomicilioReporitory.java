package com.dh.turnos.repository;

import com.dh.turnos.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioReporitory extends JpaRepository<Domicilio, Long> {
}
