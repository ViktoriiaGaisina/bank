package com.eazybytes.bank.mapper;

import com.eazybytes.bank.dto.CustomerDTO;
import com.eazybytes.bank.dto.LoanDTO;
import com.eazybytes.bank.dto.OperationType;
import com.eazybytes.bank.dto.PayRateDTO;
import com.eazybytes.bank.dto.ProductDTO;
import com.eazybytes.bank.entity.Customer;
import com.eazybytes.bank.entity.Loan;
import com.eazybytes.bank.entity.PayRate;
import com.eazybytes.bank.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-15T16:44:30+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.productKey( product.getProductKey() );
        productDTO.description( product.getDescription() );
        productDTO.payRateUnit( product.getPayRateUnit() );
        productDTO.payRate( payRateToPayRateDTO( product.getPayRate() ) );
        if ( product.getOperation() != null ) {
            productDTO.operation( Enum.valueOf( OperationType.class, product.getOperation() ) );
        }
        productDTO.type( product.getType() );
        productDTO.customer( customerToCustomerDTO( product.getCustomer() ) );
        productDTO.loan( loanToLoanDTO( product.getLoan() ) );

        return productDTO.build();
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productKey( productDTO.getProductKey() );
        product.description( productDTO.getDescription() );
        product.payRateUnit( productDTO.getPayRateUnit() );
        product.payRate( payRateDTOToPayRate( productDTO.getPayRate() ) );
        if ( productDTO.getOperation() != null ) {
            product.operation( productDTO.getOperation().name() );
        }
        product.type( productDTO.getType() );
        product.customer( customerDTOToCustomer( productDTO.getCustomer() ) );
        product.loan( loanDTOToLoan( productDTO.getLoan() ) );

        return product.build();
    }

    protected PayRateDTO payRateToPayRateDTO(PayRate payRate) {
        if ( payRate == null ) {
            return null;
        }

        PayRateDTO.PayRateDTOBuilder payRateDTO = PayRateDTO.builder();

        payRateDTO.unit( payRate.getUnit() );
        payRateDTO.value( payRate.getValue() );

        return payRateDTO.build();
    }

    protected List<ProductDTO> productListToProductDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( toProductDTO( product ) );
        }

        return list1;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.id( customer.getId() );
        customerDTO.productList( productListToProductDTOList( customer.getProductList() ) );
        customerDTO.account( customer.getAccount() );

        return customerDTO.build();
    }

    protected LoanDTO loanToLoanDTO(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanDTO.LoanDTOBuilder loanDTO = LoanDTO.builder();

        loanDTO.id( loan.getId() );
        loanDTO.principal( loan.getPrincipal() );
        loanDTO.monthsUntilMaturity( loan.getMonthsUntilMaturity() );

        return loanDTO.build();
    }

    protected PayRate payRateDTOToPayRate(PayRateDTO payRateDTO) {
        if ( payRateDTO == null ) {
            return null;
        }

        PayRate payRate = new PayRate();

        payRate.setUnit( payRateDTO.getUnit() );
        payRate.setValue( payRateDTO.getValue() );

        return payRate;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( toProduct( productDTO ) );
        }

        return list1;
    }

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setProductList( productDTOListToProductList( customerDTO.getProductList() ) );
        customer.setAccount( customerDTO.getAccount() );

        return customer;
    }

    protected Loan loanDTOToLoan(LoanDTO loanDTO) {
        if ( loanDTO == null ) {
            return null;
        }

        Loan loan = new Loan();

        loan.setId( loanDTO.getId() );
        loan.setPrincipal( loanDTO.getPrincipal() );
        loan.setMonthsUntilMaturity( loanDTO.getMonthsUntilMaturity() );

        return loan;
    }
}
