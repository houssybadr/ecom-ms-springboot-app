package net.houssy.billingservice.mappers;


import net.houssy.billingservice.dto.ProductItemDto;
import net.houssy.billingservice.entities.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductItemMapper {

    ProductItem toEntity(ProductItemDto dto);

    @Mapping(target = "product", source = "product")
    ProductItemDto toDto(ProductItem entity);
    List<ProductItemDto> toDtoList(List<ProductItem> entities);
    List<ProductItem> toEntityList(List<ProductItemDto> dtos);

}
