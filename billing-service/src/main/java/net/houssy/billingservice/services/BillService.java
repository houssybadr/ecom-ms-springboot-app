package net.houssy.billingservice.services;

import net.houssy.billingservice.dto.BillDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    List<BillDto> findAll();
    BillDto findById(Long id);
    BillDto update(Long id, BillDto billDto);
    void delete(Long id);
    BillDto save(BillDto billDto);
}
