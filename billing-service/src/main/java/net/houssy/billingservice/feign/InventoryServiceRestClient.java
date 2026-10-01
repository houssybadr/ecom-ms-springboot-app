package net.houssy.billingservice.feign;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryServiceRestClient {

    @GetMapping(path = "/products/{id}")
    @CircuitBreaker(name = "inventory-service",fallbackMethod = "getCachedProduct")
    Product getProductById(@PathVariable Long id);

    default Product getCachedProduct(Long id,Exception exception) {
        exception.printStackTrace();
        return Product.builder()
                .id(id)
                .name("cached product")
                .quantity(0)
                .price(0L)
                .build();
    }
}
