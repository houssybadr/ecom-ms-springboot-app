package net.houssy.customerservice.controller;

import net.houssy.customerservice.dto.CustomerDto;
import net.houssy.customerservice.services.CustomerService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/customers")
@RefreshScope
public class CustomerController {

    private CustomerService customerService;

    @Value("${customer.params.x}")
    private Integer customerParamX;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return new ResponseEntity<>(this.customerService.findAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/params")
    public ResponseEntity<Map<String, Integer>> getCustomerParams() {
        return ResponseEntity.ok(Map.of("x", customerParamX));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(this.customerService.save(customerDto),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> update(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(this.customerService.update(id,customerDto),HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/id"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
