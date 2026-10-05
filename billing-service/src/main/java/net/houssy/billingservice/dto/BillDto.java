package net.houssy.billingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.houssy.billingservice.model.Customer;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Long id;
    private Date billDate;
    private Long customerId;
    private List<ProductItemDto> productItems;
    private Customer customer;
}
