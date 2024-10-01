package com.autorizadorPlaca.autorizadorPlaca.repository;

import com.autorizadorPlaca.autorizadorPlaca.entity.PlacaConcessionPracaBloqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacaConcessionPracaBloqueRepository extends JpaRepository<PlacaConcessionPracaBloqueEntity, Long> {
    boolean existsByIdPlacaConcessionariaAndCodigoPraca(Long idPlacaConcessionaria, Long codigoPraca);
}