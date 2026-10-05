package net.houssy.customerservice.services;

import net.houssy.customerservice.dto.CustomerDto;
import net.houssy.customerservice.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(Long id);
    CustomerDto update(Long id,CustomerDto  customerDto);
    void delete(Long id);
    CustomerDto save(CustomerDto customerDto);
}
