package net.houssy.inventoryservice.services.ServiceImpl;

import net.houssy.inventoryservice.dto.ProductDto;
import net.houssy.inventoryservice.entities.Product;
import net.houssy.inventoryservice.mappers.ProductMapper;
import net.houssy.inventoryservice.repository.ProductRepo;
import net.houssy.inventoryservice.services.ProductService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepo productRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto findById(Long id) {
        Product fetchedProduct = this.findProductById(id);
        return this.productMapper.toDto(fetchedProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> fetchedProducts = this.productRepo.findAll();
        return this.productMapper.toDtos(fetchedProducts);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product savedProduct=this.productRepo.save(this.productMapper.toEntity(productDto));
        return this.productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product fetchedProduct = this.findProductById(id);
        Product updatedProduct=Product.builder()
                .id(id)
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .build();
        return this.productMapper.toDto(this.productRepo.save(updatedProduct));
    }

    @Override
    public void delete(Long id) {
        this.productRepo.deleteById(id);
    }

    private Product findProductById(Long id) {
        return this.productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id " + id));
    }
}
