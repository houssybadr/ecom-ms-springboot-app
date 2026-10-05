package net.houssy.billingservice.services.Impl;

import net.houssy.billingservice.dto.ProductItemDto;
import net.houssy.billingservice.entities.ProductItem;
import net.houssy.billingservice.exception.exceptions.ResourceNotFoundException;
import net.houssy.billingservice.mappers.ProductItemMapper;
import net.houssy.billingservice.repository.ProductItemRepo;
import net.houssy.billingservice.services.ProductItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductItemServiceImpl implements ProductItemService {
    private final ProductItemMapper productItemMapper;
    private final ProductItemRepo productItemRepo;

    public ProductItemServiceImpl(ProductItemMapper productItemMapper, ProductItemRepo productItemRepo) {
        this.productItemMapper = productItemMapper;
        this.productItemRepo = productItemRepo;
    }


    @Override
    public List<ProductItemDto> findAll() {
        List<ProductItem> fetchedProductItems = productItemRepo.findAll();
        return this.productItemMapper.toDtoList(fetchedProductItems);
    }

    @Override
    public ProductItemDto findById(Long id) {
        ProductItem fetchedProductItem = this.findProductItemById(id);
        return this.productItemMapper.toDto(fetchedProductItem);
    }

    @Override
    public ProductItemDto update(Long id, ProductItemDto requestedProductItem) {
        this.findProductItemById(id);
        ProductItem updateProductItem = ProductItem.builder()
                .id(id)
                .price(requestedProductItem.getPrice())
                .quantity(requestedProductItem.getQuantity())
                .productId(requestedProductItem.getProductId())
                .build();

        ProductItem updatedProductItem = this.productItemRepo.save(updateProductItem);

        return this.productItemMapper.toDto(updatedProductItem);
    }

    @Override
    public void delete(Long id) {
        productItemRepo.deleteById(id);
    }

    @Override
    public ProductItemDto save(ProductItemDto productItemDto) {
        ProductItem savedProductItem = this.productItemRepo.save(this.productItemMapper.toEntity(productItemDto));
        return this.productItemMapper.toDto(savedProductItem);
    }

    private ProductItem findProductItemById(Long id) {
        return productItemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find ProductItem with id: " + id));
    }

    public List<ProductItem> findAllByBillId(Long billId) {
        return this.productItemRepo.findByBillId(billId);
    }

}
