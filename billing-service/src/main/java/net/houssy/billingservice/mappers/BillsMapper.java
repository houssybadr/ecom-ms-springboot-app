package net.houssy.billingservice.mappers;


import net.houssy.billingservice.dto.BillDto;
import net.houssy.billingservice.entities.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductItemMapper.class)
public interface BillsMapper {

    Bill toEntity(BillDto dto);

    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "productItems", source = "productItems")
    BillDto toDto(Bill entity);
    List<BillDto> toDtoList(List<Bill> entities);
    List<Bill> toEntityList(List<BillDto> dtos);

}
