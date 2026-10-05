package net.houssy.billingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.houssy.billingservice.model.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDto {
    private Long id;
    private double price;
    private int quantity;
    private Long productId;
    private Product product;
}
