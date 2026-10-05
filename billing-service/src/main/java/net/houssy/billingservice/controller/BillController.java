package net.houssy.billingservice.controller;


import net.houssy.billingservice.dto.BillDto;
import net.houssy.billingservice.entities.Bill;
import net.houssy.billingservice.feign.CustomerServiceRestClient;
import net.houssy.billingservice.feign.InventoryServiceRestClient;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.model.Product;
import net.houssy.billingservice.repository.BillRepo;
import net.houssy.billingservice.repository.ProductItemRepo;
import net.houssy.billingservice.services.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bills")
public class BillController {

    private BillService billService;
    public BillController( BillService billService) {
        this.billService = billService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BillDto> getBills(@PathVariable Long id){
        return new ResponseEntity<>(this.billService.findById(id), HttpStatus.OK);
    }
}
