package com.dh.turnos.model;

import com.dh.turnos.dto.PacienteDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_seq")
    @SequenceGenerator(name = "paciente_seq", sequenceName = "paciente_seq", allocationSize = 1)
    private Long id;

    @Column
    @Setter
    private String nombre;

    @Column
    @Setter
    private String apellido;

    @Column
    @Setter
    private String dni;

    @Column
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    @Setter
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(PacienteDTO pacienteDTO) {
            this.nombre = pacienteDTO.getNombre();
            this.apellido = pacienteDTO.getApellido();
            this.dni = pacienteDTO.getDni();
            this.domicilio = pacienteDTO.getDomicilio();
            this.fechaIngreso = LocalDate.now();
    }

    public Paciente(String nombre, String apellido, String dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                '}';
    }
}
