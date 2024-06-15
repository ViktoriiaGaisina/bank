package com.eazybytes.bank.dto;

import com.eazybytes.bank.entity.Customer;
import com.eazybytes.bank.entity.Loan;
import com.eazybytes.bank.entity.PayRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productKey;
    private String description;
    private BigDecimal payRateUnit;
    private PayRateDTO payRate;
    private OperationType operation;
    private String type;
    private CustomerDTO customer;
    private LoanDTO loan;
}
