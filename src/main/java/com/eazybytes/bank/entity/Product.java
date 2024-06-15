package com.eazybytes.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String productKey;
    private String description;
    private BigDecimal payRateUnit;
    private PayRate payRate;
    private String operation;
    private String type;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Loan loan;

}
