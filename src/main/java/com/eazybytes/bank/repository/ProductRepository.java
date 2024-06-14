package com.eazybytes.bank.repository;

import com.eazybytes.bank.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
