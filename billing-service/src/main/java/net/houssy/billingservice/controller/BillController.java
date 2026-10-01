package net.houssy.billingservice.controller;


import net.houssy.billingservice.entities.Bill;
import net.houssy.billingservice.feign.CustomerServiceRestClient;
import net.houssy.billingservice.feign.InventoryServiceRestClient;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.model.Product;
import net.houssy.billingservice.repository.BillRepo;
import net.houssy.billingservice.repository.ProductItemRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bills")
public class BillController {

    private BillRepo billRepo;
    private ProductItemRepo productItemRepo;
    private CustomerServiceRestClient customerClient;
    private InventoryServiceRestClient inventoryClient;

    public BillController(
            BillRepo billRepo,
            ProductItemRepo productItemRepo,
            CustomerServiceRestClient customerClient,
            InventoryServiceRestClient inventoryClient
    ){
        this.billRepo = billRepo;
        this.productItemRepo = productItemRepo;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
    }

    @GetMapping(path = "/{id}")
    public Bill getBills(@PathVariable Long id){
        Bill bill=this.billRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Bill introuvable avec id "+id));
        Customer customer=customerClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem->{
            productItem.setProduct(inventoryClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
