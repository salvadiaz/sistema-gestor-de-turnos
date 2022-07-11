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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_seq")
    @SequenceGenerator(name = "domicilio_seq", sequenceName = "domicilio_seq", allocationSize = 1)
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
