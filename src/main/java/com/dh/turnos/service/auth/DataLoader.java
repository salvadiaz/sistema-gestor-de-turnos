package com.dh.turnos.service.auth;


import com.dh.turnos.exceptions.BadRequestException;
import com.dh.turnos.model.Rol;
import com.dh.turnos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final UsuarioService usuarioService;

    @Autowired
    public DataLoader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void run(ApplicationArguments args) throws BadRequestException {
        usuarioService.crear(new Usuario(123456789, "admin", "admin@gmail.com", "admin", Set.of(new Rol("ADMIN"), new Rol("USER"))));
        usuarioService.crear(new Usuario(123456788, "user", "user@gmail.com", "user", Set.of(new Rol("USER"))));
    }
}