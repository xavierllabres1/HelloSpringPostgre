package com.world.hello.repository;

import com.world.hello.models.Nombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NombreRepository extends JpaRepository<Nombre, Long> {
}
