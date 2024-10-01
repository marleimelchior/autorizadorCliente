package com.autorizadorPlaca.autorizadorPlaca.repository;

import com.autorizadorPlaca.autorizadorPlaca.entity.PlacaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacaRepository extends JpaRepository<PlacaEntity, Long> {

    List<PlacaEntity> findByDsPlacaAndConcessionarias_CoConcessionaria(String dsPlaca, Long coConcessionaria);
}