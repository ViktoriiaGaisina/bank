package com.eazybytes.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
