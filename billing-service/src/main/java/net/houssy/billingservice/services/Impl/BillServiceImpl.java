package net.houssy.billingservice.services.Impl;

import net.houssy.billingservice.dto.BillDto;
import net.houssy.billingservice.entities.Bill;
import net.houssy.billingservice.entities.ProductItem;
import net.houssy.billingservice.exception.exceptions.ResourceNotFoundException;
import net.houssy.billingservice.feign.CustomerServiceRestClient;
import net.houssy.billingservice.feign.InventoryServiceRestClient;
import net.houssy.billingservice.mappers.BillsMapper;
import net.houssy.billingservice.model.Customer;
import net.houssy.billingservice.repository.BillRepo;
import net.houssy.billingservice.services.BillService;
import net.houssy.billingservice.services.ProductItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    private final BillsMapper billsMapper;
    private final BillRepo billRepo;
    private final ProductItemService productItemService;
    private final CustomerServiceRestClient customerServiceRestClient;
    private final InventoryServiceRestClient inventoryServiceRestClient;

    public BillServiceImpl(
            BillsMapper billsMapper,
            BillRepo billRepo,
            CustomerServiceRestClient customerServiceRestClient,
            ProductItemService productItemService,
            InventoryServiceRestClient inventoryServiceRestClient
    ) {
        this.billsMapper = billsMapper;
        this.billRepo = billRepo;
        this.customerServiceRestClient = customerServiceRestClient;
        this.productItemService = productItemService;
        this.inventoryServiceRestClient = inventoryServiceRestClient;
    }


    @Override
    public List<BillDto> findAll() {
        List<Bill> fetchedBills = billRepo.findAll();
        return this.billsMapper.toDtoList(fetchedBills);
    }

    @Override
    public BillDto findById(Long id) {
        Bill fetchedBill = this.findBillById(id);
        Customer fetchedCustomer = customerServiceRestClient.getCustomerById(fetchedBill.getCustomerId());
        fetchedBill.getProductItems().forEach(
                p -> {
                                p.setProduct(
                                        inventoryServiceRestClient.getProductById(p.getProductId())
                                );
                            });
        fetchedBill.setCustomer(fetchedCustomer);
        return this.billsMapper.toDto(fetchedBill);
    }

    @Override
    public BillDto update(Long id, BillDto requestedBill) {
        this.findBillById(id);
        Bill updateBill = Bill.builder()
                .id(id)
                .billDate(requestedBill.getBillDate())
                .customerId(requestedBill.getCustomerId())
                .build();

        Bill updatedBill = this.billRepo.save(updateBill);

        return this.billsMapper.toDto(updatedBill);
    }

    @Override
    public void delete(Long id) {
        billRepo.deleteById(id);
    }

    @Override
    public BillDto save(BillDto billDto) {
        Bill savedBill = this.billRepo.save(this.billsMapper.toEntity(billDto));
        return this.billsMapper.toDto(savedBill);
    }

    private Bill findBillById(Long id) {
        return billRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find Bill with id: " + id));
    }

}
