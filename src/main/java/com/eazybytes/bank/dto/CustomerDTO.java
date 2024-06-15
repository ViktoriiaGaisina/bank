package com.eazybytes.bank.dto;

import com.eazybytes.bank.entity.Account;
import com.eazybytes.bank.entity.Product;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private List<ProductDTO> productList;
    private Account account;
}
