package com.eazybytes.bank.service.impl;

import com.eazybytes.bank.dto.ProductDTO;
import com.eazybytes.bank.entity.PayRate;
import com.eazybytes.bank.entity.Product;
import com.eazybytes.bank.mapper.ProductMapper;
import com.eazybytes.bank.repository.ProductRepository;
import com.eazybytes.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final String ACCOUNT = "ACCOUNT";
    private static final String LOAN = "LOAN";
    private static final String PROCENT_RATE = "0.03";
    private static final String MOUNTLY_PAYMENT = "0.02";
    private static final String UPDATE = "U";
    private static final String NEW = "N";

    @Override
    public void calculateDailyFees() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            ProductDTO productDTO = productMapper.toProductDTO(product);
            BigDecimal result = calculateAndDebitFee(productDTO);
            productDTO.setPayRateUnit(result);
            saveOrUpdateProduct(productDTO);
        });
    }

    private BigDecimal calculateAndDebitFee(ProductDTO productDTO) {
        if (ACCOUNT.equals(productDTO.getType())) {
            return calculateAccountFee(productDTO);
        }
        if (LOAN.equals(productDTO.getType())) {
            return calculateLoanFee(productDTO);
        }
        return BigDecimal.ZERO;
    }

    private void saveOrUpdateProduct(ProductDTO productDTO) {
        if (productDTO.getOperation().name().equals(UPDATE)) {
            updateProduct(productDTO);
        }
        if (productDTO.getOperation().name().equals(NEW)) {
            Product productEntity = productMapper.toProduct(productDTO);
            productRepository.save(productEntity);
        }
    }

    private void updateProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .operation(productDTO.getOperation().name())
                .productKey(productDTO.getProductKey())
                .payRate(new PayRate(productDTO.getPayRate().getUnit(), productDTO.getPayRate().getValue()))
                .build();
        productRepository.save(product);
    }

    private BigDecimal calculateAccountFee(ProductDTO productDTO) {
        BigDecimal fee = productDTO.getCustomer().getAccount().getBalance().multiply(new BigDecimal(PROCENT_RATE));
        productDTO.getCustomer().getAccount().setBalance(
                productDTO.getCustomer().getAccount().getBalance().subtract(fee)
        );
        return fee;
    }

    private BigDecimal calculateLoanFee(ProductDTO productDTO) {
        BigDecimal principal = productDTO.getLoan().getPrincipal();
        int months = productDTO.getLoan().getMonthsUntilMaturity();
        BigDecimal monthlyPayment = principal.divide(new BigDecimal(months), 2, BigDecimal.ROUND_HALF_UP);
        return monthlyPayment.multiply(new BigDecimal(MOUNTLY_PAYMENT));
    }
}