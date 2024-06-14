package com.eazybytes.bank.service.impl;

import com.eazybytes.bank.entity.Product;
import com.eazybytes.bank.repository.ProductRepository;
import com.eazybytes.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final String ACCOUNT = "ACCOUNT";
    private static final String LOAN = "LOAN";
    private static final String PROCENT_RATE = "0.03";
    private static final String MOUNTLY_PAYMENT = "0.02";

    @Override
    public void calculateDailyFees() {
        productRepository.findAll()
                .forEach(product ->calculateAndDebitFee(product));
    }

    private BigDecimal calculateAndDebitFee(Product product) {
        if (ACCOUNT.equals(product.getType())) {
            return calculateAccountFee(product);
        }
        if (LOAN.equals(product.getType())) {
            return calculateLoanFee(product);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateAccountFee(Product product) {
        return product.getCustomer().getAccount().getBalance().multiply(new BigDecimal(PROCENT_RATE));
    }

    private BigDecimal calculateLoanFee(Product product) {
        BigDecimal principal = product.getLoan().getPrincipal();
        int mounth = product.getLoan().getMonthsUntilMaturity();
        BigDecimal monthlyPayment = principal.divide(new BigDecimal(mounth), 2, BigDecimal.ROUND_HALF_UP);
        return monthlyPayment.multiply(new BigDecimal(MOUNTLY_PAYMENT));
    }
}
