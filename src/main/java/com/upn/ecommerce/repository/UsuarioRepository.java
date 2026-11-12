package com.upn.ecommerce.repository;

import com.upn.ecommerce.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
}
