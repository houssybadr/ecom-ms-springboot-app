package net.houssy.billingservice.services;

import net.houssy.billingservice.dto.ProductItemDto;
import net.houssy.billingservice.entities.ProductItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductItemService {
    List<ProductItemDto> findAll();
    ProductItemDto findById(Long id);
    ProductItemDto update(Long id, ProductItemDto productItemDto);
    void delete(Long id);
    ProductItemDto save(ProductItemDto productItemDto);
    List<ProductItem> findAllByBillId(Long billId);
}
