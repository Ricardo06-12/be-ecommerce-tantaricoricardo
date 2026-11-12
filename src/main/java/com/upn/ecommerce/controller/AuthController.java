package com.upn.ecommerce.controller;

import com.upn.ecommerce.dto.*;
import com.upn.ecommerce.entity.Rol;
import com.upn.ecommerce.entity.Usuario;
import com.upn.ecommerce.repository.RolRepository;
import com.upn.ecommerce.repository.UsuarioRepository;
import com.upn.ecommerce.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDTO(jwt, "Bearer", userDetails.getUsername(), roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequestDTO signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error: El usuario ya existe."));
        }
        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                .orElseGet(() -> rolRepository.save(new Rol(null, "ROLE_ADMIN")));

        Usuario user = new Usuario();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(Set.of(rolAdmin));
        usuarioRepository.save(user);

        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado exitosamente: " + user.getUsername()));
    }
}
