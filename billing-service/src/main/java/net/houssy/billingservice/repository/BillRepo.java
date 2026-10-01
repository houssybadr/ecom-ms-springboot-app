package net.houssy.billingservice.repository;

import net.houssy.billingservice.entities.Bill;
import net.houssy.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BillRepo extends JpaRepository<Bill,Long> {
    List<ProductItem> findByCustomerId(Long customerId);
}
