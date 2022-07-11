package com.dh.turnos.model;

import com.dh.turnos.dto.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Setter
    private String nombre;

    @Column
    @Setter
    private String apellido;

    @Column
    @Setter
    private Integer matricula;

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(OdontologoDTO odontologoDTO) {
        this.nombre = odontologoDTO.getNombre();
        this.apellido = odontologoDTO.getApellido();
        this.matricula = odontologoDTO.getMatricula();
    }
}
