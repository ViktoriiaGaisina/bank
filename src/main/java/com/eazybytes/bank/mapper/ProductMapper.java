package com.eazybytes.bank.mapper;

import com.eazybytes.bank.dto.ProductDTO;
import com.eazybytes.bank.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);
}