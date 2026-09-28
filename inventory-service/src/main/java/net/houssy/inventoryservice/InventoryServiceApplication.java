package net.houssy.inventoryservice;

import net.houssy.inventoryservice.entities.Inventory;
import net.houssy.inventoryservice.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InventoryRepo inventoryRepo) {
        return args->{
            inventoryRepo.save(Inventory.builder()
                            .name("stylo")
                            .price(1)
                            .quantity(3000)
                            .build());

            inventoryRepo.save(Inventory.builder()
                    .name("cahier")
                    .price(10)
                    .quantity(2500)
                    .build());

            inventoryRepo.save(Inventory.builder()
                    .name("tablet")
                    .price(1799)
                    .quantity(900)
                    .build());
        };
    }
}
