package net.houssy.inventoryservice.services;

import net.houssy.inventoryservice.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto findById(Long id);
    List<ProductDto> findAll();
    ProductDto save(ProductDto productDto);
    ProductDto update(Long id,ProductDto productDto);
    void delete(Long id);
}
