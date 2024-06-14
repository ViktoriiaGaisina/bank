package com.eazybytes.bank.schedule;

import com.eazybytes.bank.entity.Product;
import com.eazybytes.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeCalculationSchedule {
    private final ProductService productService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Prague")
    public void calculateFee() {
        productService.calculateDailyFees();
    }
}
