package net.houssy.customerservice.repository;

import net.houssy.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Boolean existsByEmail(String email);
}
