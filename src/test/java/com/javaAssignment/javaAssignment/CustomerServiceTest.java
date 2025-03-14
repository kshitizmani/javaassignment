package com.javaAssignment.javaAssignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javaAssignment.javaAssignment.entity.Customer;
import com.javaAssignment.javaAssignment.repository.CustomerRepository;
import com.javaAssignment.javaAssignment.service.CustomerService;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository; // Mocking the repository

    @InjectMocks
    private CustomerService custservice;  // Injecting the mocked repository into Custservice

    @Test
    void testAddCustomer() {
        // Prepare mock data
        Customer customer = new Customer();
        customer.setName("Akash");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Call the method under test
        String result = custservice.addCustomer("Akash");

        // Assert the result
        assertEquals("Customer saved successful", result);

        // Test additional names
        customer.setName("Kshitiz");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        
        result = custservice.addCustomer("Kshitiz");
        assertEquals("Customer saved successful", result);
    }
    
    
   
    
    
    
    
    
    
}