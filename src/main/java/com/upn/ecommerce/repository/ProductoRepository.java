package com.upn.ecommerce.repository;

import com.upn.ecommerce.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> buscarPorCategoriaNamed(@Param("categoria") String categoria);
    List<Producto> buscarConStockMinimoNamed(@Param("stockMinimo") Integer stockMinimo);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Producto> buscarPorNombreOMarcaJpql(@Param("filtro") String filtro);

    Optional<Producto> findByCodigoBarras(String codigoBarras);
}
