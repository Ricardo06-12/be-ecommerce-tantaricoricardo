package com.upn.ecommerce.service;

import com.upn.ecommerce.dto.ProductoRequestDTO;
import com.upn.ecommerce.dto.ProductoResponseDTO;
import com.upn.ecommerce.entity.Producto;
import com.upn.ecommerce.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToResponse(producto);
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> buscarPorCategoria(String categoria) {
        return productoRepository.buscarPorCategoriaNamed(categoria).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> buscarPorFiltro(String filtro) {
        return productoRepository.buscarPorNombreOMarcaJpql(filtro).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .categoria(dto.getCategoria())
                .precioCompra(dto.getPrecioCompra())
                .precioVenta(dto.getPrecioVenta())
                .stock(dto.getStock())
                .marca(dto.getMarca())
                .codigoBarras(dto.getCodigoBarras())
                .fechaIngreso(dto.getFechaIngreso())
                .build();
        return mapToResponse(productoRepository.save(producto));
    }

    @Transactional
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setMarca(dto.getMarca());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setFechaIngreso(dto.getFechaIngreso());
        return mapToResponse(productoRepository.save(producto));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO mapToResponse(Producto p) {
        return ProductoResponseDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precioCompra(p.getPrecioCompra())
                .precioVenta(p.getPrecioVenta())
                .stock(p.getStock())
                .marca(p.getMarca())
                .codigoBarras(p.getCodigoBarras())
                .fechaIngreso(p.getFechaIngreso())
                .build();
    }
}
