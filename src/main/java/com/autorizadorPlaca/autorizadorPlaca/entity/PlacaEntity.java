package com.autorizadorPlaca.autorizadorPlaca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"concessionarias", "pracaBloqueios"})
@Table(name = "tb_placa")
@Where(clause = "st_ativo = 'S'")
public class PlacaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaca;

    private String dsPlaca;
    private String stAtivo;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private ParceiroEntity parceiro;

    @OneToMany(mappedBy = "placa")
    private List<ConcessionariaEntity> concessionarias;

    @OneToMany
    private List<PlacaConcessionPracaBloqueEntity> pracaBloqueios;
}
