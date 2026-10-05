package net.houssy.billingservice.repository;

import net.houssy.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource
public interface ProductItemRepo extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findByBillId(Long billId);
}
