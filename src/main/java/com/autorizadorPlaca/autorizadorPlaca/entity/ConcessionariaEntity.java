package com.autorizadorPlaca.autorizadorPlaca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_placa_concessionaria")
public class ConcessionariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concessionaria")
    private Long idConcessionaria;

    @Column(name = "co_concessionaria")
    private Long coConcessionaria;

    @ManyToOne
    @JoinColumn(name = "id_placa")
    private PlacaEntity placa;

    @Column(name = "id_placa_concessionaria")
    private Long idPlacaConcessionaria;

    @OneToMany
    private List<PlacaConcessionPracaBloqueEntity> pracaBloqueios;

    public Long getIdPlacaConcessionaria() {
        return idPlacaConcessionaria;
    }
}
