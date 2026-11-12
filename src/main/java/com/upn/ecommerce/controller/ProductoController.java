package com.upn.ecommerce.controller;

import com.upn.ecommerce.dto.ProductoRequestDTO;
import com.upn.ecommerce.dto.ProductoResponseDTO;
import com.upn.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorFiltro(@RequestParam String filtro) {
        return ResponseEntity.ok(productoService.buscarPorFiltro(filtro));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        return new ResponseEntity<>(productoService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado correctamente"));
    }
}
