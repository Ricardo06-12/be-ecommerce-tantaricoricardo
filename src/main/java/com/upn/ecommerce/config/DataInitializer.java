package com.upn.ecommerce.config;

import com.upn.ecommerce.entity.Producto;
import com.upn.ecommerce.entity.Rol;
import com.upn.ecommerce.entity.Usuario;
import com.upn.ecommerce.repository.ProductoRepository;
import com.upn.ecommerce.repository.RolRepository;
import com.upn.ecommerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                .orElseGet(() -> rolRepository.save(new Rol(null, "ROLE_ADMIN")));

        if (!usuarioRepository.existsByUsername("ricardo")) {
            Usuario ricardo = new Usuario();
            ricardo.setUsername("ricardo");
            ricardo.setPassword(passwordEncoder.encode("123456"));
            ricardo.setRoles(Set.of(rolAdmin));
            usuarioRepository.save(ricardo);
        }

        if (productoRepository.count() == 0) {
            productoRepository.save(Producto.builder()
                    .nombre("Laptop Gamer Nitro 5")
                    .categoria("Computo")
                    .precioCompra(new BigDecimal("2800.00"))
                    .precioVenta(new BigDecimal("3499.00"))
                    .stock(10)
                    .marca("Acer")
                    .codigoBarras("775000100001")
                    .fechaIngreso(LocalDate.now())
                    .build());
            productoRepository.save(Producto.builder()
                    .nombre("Mouse Inalambrico G305")
                    .categoria("Perifericos")
                    .precioCompra(new BigDecimal("110.00"))
                    .precioVenta(new BigDecimal("169.90"))
                    .stock(25)
                    .marca("Logitech")
                    .codigoBarras("775000100002")
                    .fechaIngreso(LocalDate.now())
                    .build());
        }
    }
}
