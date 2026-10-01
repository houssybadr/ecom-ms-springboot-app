package net.houssy.inventoryservice;

import net.houssy.inventoryservice.entities.Product;
import net.houssy.inventoryservice.repository.ProductRepo;
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
    CommandLineRunner start(ProductRepo inventoryRepo) {
        return args->{
            inventoryRepo.save(Product.builder()
                            .name("stylo")
                            .price(1)
                            .quantity(3000)
                            .build());

            inventoryRepo.save(Product.builder()
                    .name("cahier")
                    .price(10)
                    .quantity(2500)
                    .build());

            inventoryRepo.save(Product.builder()
                    .name("tablet")
                    .price(1799)
                    .quantity(900)
                    .build());
        };
    }
}
