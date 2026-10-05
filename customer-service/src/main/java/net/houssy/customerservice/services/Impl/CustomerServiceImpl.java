package net.houssy.customerservice.services.Impl;

import net.houssy.customerservice.dto.CustomerDto;
import net.houssy.customerservice.entities.Customer;
import net.houssy.customerservice.exception.exceptions.DuplicateResourceException;
import net.houssy.customerservice.exception.exceptions.ResourceNotFoundException;
import net.houssy.customerservice.mappers.CustomerMapper;
import net.houssy.customerservice.repository.CustomerRepo;
import net.houssy.customerservice.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepo customerRepo) {
        this.customerMapper = customerMapper;
        this.customerRepo = customerRepo;
    }


    @Override
    public List<CustomerDto> findAll() {
        List<Customer> fetchedCustomers = customerRepo.findAll();
        return  this.customerMapper.toDtoList(fetchedCustomers);
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer fetcherCustomer = this.findCustomerById(id);
        return this.customerMapper.toDto(fetcherCustomer);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto requestedCustomer) {
        Customer fetcherCustomer = this.findCustomerById(id);
        Customer UpdateCustomer = Customer.builder()
                .id(id)
                .name(requestedCustomer.getName())
                .email(requestedCustomer.getEmail())
                .build();

        Customer updatedCostomer= this.customerRepo.save(UpdateCustomer);

        return this.customerMapper.toDto(updatedCostomer);
    }

    @Override
    public void delete(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        if (this.customerRepo.existsByEmail(customerDto.getEmail())) {
            throw new DuplicateResourceException("Customer with email " + customerDto.getEmail() + " already exists");
        }

        Customer savedCustomer= this.customerRepo.save(this.customerMapper.toEntity(customerDto));
        return this.customerMapper.toDto(savedCustomer);
    }

    private Customer findCustomerById(Long id){
        return  customerRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Couldn't find Customer with id: "+id));
    }

}
