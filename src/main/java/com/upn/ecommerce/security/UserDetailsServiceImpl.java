package com.upn.ecommerce.security;

import com.upn.ecommerce.entity.Usuario;
import com.upn.ecommerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                        .collect(Collectors.toList())
        );
    }
}
