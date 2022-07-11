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
@ToString
public class Paciente {
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
}
