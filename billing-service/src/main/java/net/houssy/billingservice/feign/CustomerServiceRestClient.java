package net.houssy.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {
    @GetMapping(path = "/customers/{id}")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "getCachedCustomer")
    Customer getCustomerById(@PathVariable Long id);

    default Customer getCachedCustomer(Long id,Exception exception){
        exception.printStackTrace();
        // Suposed to get customer from cache
        return Customer.builder()
                .id(id)
                .email("cached.email@gmail.com")
                .name("cached customer name")
                .build();
                
    }
}
