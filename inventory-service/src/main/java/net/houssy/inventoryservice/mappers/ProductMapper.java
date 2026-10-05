package net.houssy.inventoryservice.mappers;

import net.houssy.inventoryservice.dto.ProductDto;
import net.houssy.inventoryservice.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    public ProductDto toDto(Product product);
    public Product toEntity(ProductDto productDto);
    public List<ProductDto> toDtos(List<Product> products);
    public List<Product> toEntitys(List<ProductDto> productDtos);
}
