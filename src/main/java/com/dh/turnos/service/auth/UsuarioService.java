package com.dh.turnos.service.auth;

import com.dh.turnos.exceptions.BadRequestException;
import com.dh.turnos.model.Rol;
import com.dh.turnos.model.Usuario;
import com.dh.turnos.repository.RolRepository;
import com.dh.turnos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario crear(Usuario usuario) throws BadRequestException {
        if (usuario == null)
            throw new BadRequestException("El usuario no puede ser null");
        if (usuario.getDni() == null)
            throw new BadRequestException("El DNI del usuario no puede ser null");
        rolRepository.saveAll(usuario.getRoles());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> consultarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> u = usuarioRepository.findByUsername(username);
        if (u.isEmpty())
            throw new UsernameNotFoundException("No existe el usuario con username: " + username);

        Usuario usuario = u.get();
        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        for (Rol rol: usuario.getRoles()) {
            autorizaciones.add(new SimpleGrantedAuthority(rol.getName()));
        }

        return new User(usuario.getEmail(), usuario.getPassword(), true, true, true, true, autorizaciones);
    }
}
