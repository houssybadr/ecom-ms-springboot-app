package net.houssy.inventoryservice.repository;

import net.houssy.inventoryservice.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InventoryRepo  extends JpaRepository<Inventory, Long> {
}
