package com.dh.turnos.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Setter
    private String calle;

    @Column
    @Setter
    private Integer numero;

    @Column
    @Setter
    private String localidad;

    @Column
    @Setter
    private String provincia;
}
