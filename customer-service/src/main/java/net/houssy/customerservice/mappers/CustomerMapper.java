package net.houssy.customerservice.mappers;


import net.houssy.customerservice.dto.CustomerDto;
import net.houssy.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDto dto);
    CustomerDto toDto(Customer entity);
    List<CustomerDto> toDtoList(List<Customer> entities);
    List<Customer> toEntityList(List<CustomerDto> dtos);

}