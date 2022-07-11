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
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_seq")
    @SequenceGenerator(name = "odontologo_seq", sequenceName = "odontologo_seq", allocationSize = 1)
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

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(OdontologoDTO odontologoDTO) {
        this.nombre = odontologoDTO.getNombre();
        this.apellido = odontologoDTO.getApellido();
        this.matricula = odontologoDTO.getMatricula();
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +
                '}';
    }
}
