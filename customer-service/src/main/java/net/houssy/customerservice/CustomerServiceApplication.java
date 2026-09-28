package net.houssy.customerservice;

import net.houssy.customerservice.entities.Customer;
import net.houssy.customerservice.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner star(CustomerRepo customerRepo) {
        return args -> {
            customerRepo.save(Customer.builder()
                    .name("John")
                    .email("john.doe@gmail.com")
                    .build());

            customerRepo.save(Customer.builder()
                    .name("badr")
                    .email("badr.houssy@gmail.com")
                    .build());

            customerRepo.save(Customer.builder()
                    .name("simo")
                    .email("simo.darwish@gmail.com")
                    .build());
        };
    }
}
