package com.autorizadorPlaca.autorizadorPlaca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rl_placaconcession_pracabloque")
public class PlacaConcessionPracaBloqueEntity {

    @Id
    @Column(name = "id_placa_concessionaria")
    private Long idPlacaConcessionaria;

    @Column(name = "codigo_praca")
    private Long codigoPraca;

}
