package net.houssy.billingservice.feign;


import net.houssy.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface ProductServiceRestClient {

    @GetMapping(path = "/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);
}
