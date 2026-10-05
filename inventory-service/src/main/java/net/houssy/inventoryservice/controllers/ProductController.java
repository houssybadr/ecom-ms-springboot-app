package net.houssy.inventoryservice.controllers;

import net.houssy.inventoryservice.dto.ProductDto;
import net.houssy.inventoryservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventories")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(this.productService.save(productDto),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDto> update(
            @PathVariable Long id,
            @RequestBody ProductDto  productDto) {
        return new ResponseEntity<>(this.productService.update(id,productDto),HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/id"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
