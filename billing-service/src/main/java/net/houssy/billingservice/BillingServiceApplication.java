package net.houssy.billingservice;

import net.houssy.billingservice.entities.Bill;
import net.houssy.billingservice.entities.ProductItem;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.model.Product;
import net.houssy.billingservice.repository.BillRepo;
import net.houssy.billingservice.repository.ProductItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductItemRepo productItemRepo, BillRepo billRepo) {
        return args->{
            List<Long> customersIds = List.of(1L,2L,3L);
            List<Long> productsIds = List.of(1L, 2L, 3L);

            customersIds.forEach(customerId -> {
                Bill bill = Bill.builder()
                        .customerId(customerId)
                        .billDate(new Date())
                        .build();

                billRepo.save(bill);

                productsIds.forEach(productId -> {
                    ProductItem productItem  = ProductItem.builder()
                            .price(new Random().nextDouble(3000L))
                            .quantity(1+ new Random().nextInt(20))
                            .bill(bill)
                            .productId(productId)
                            .build();

                    productItemRepo.save(productItem);
                });

            });
        };
    }
}
