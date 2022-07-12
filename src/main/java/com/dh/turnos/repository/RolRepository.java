package com.dh.turnos.repository;

import com.dh.turnos.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RolRepository extends JpaRepository<Rol, Integer> {
}

