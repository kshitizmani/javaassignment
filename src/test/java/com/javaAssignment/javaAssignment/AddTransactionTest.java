package com.javaAssignment.javaAssignment;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javaAssignment.javaAssignment.entity.Customer;
import com.javaAssignment.javaAssignment.entity.Transaction;
import com.javaAssignment.javaAssignment.exception.CustomerNotFoundException;
import com.javaAssignment.javaAssignment.repository.CustomerRepository;
import com.javaAssignment.javaAssignment.repository.TransactionRepository;
import com.javaAssignment.javaAssignment.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class AddTransactionTest {
	
	    @InjectMocks
	    private CustomerService customerService;

	    @Mock
	    private CustomerRepository customerRepository;

	    @Mock
	    private TransactionRepository transactionRepository;
	    
	    private Customer customer;
	    private Transaction transaction;
	    Long customerId;
	    int month;

	    @BeforeEach
	    void setUp() {
	    	
	        MockitoAnnotations.openMocks(this);
	        
	        customerId = 1L;
	        customer = new Customer();
	        customer.setId(customerId);
	        customer.setName("Akash Singh");
	        
	        transaction = new Transaction();
	        transaction.setAmount(100.0);
	        transaction.setDate(LocalDate.now());

	    }

	    @Test
	    void testAddTransaction_Success() {

	        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
	        when(transactionRepository.save(transaction)).thenReturn(transaction);

	        
	        String result = customerService.addTransaction(customerId, transaction);

	        
	        assertEquals("Transaction Record saved successfully", result);
	        assertEquals(customerId, transaction.getCustomerId());
	        
	        verify(customerRepository, times(1)).findById(customerId);
	        verify(transactionRepository, times(1)).save(transaction);
	    }

	    @Test
	    void testAddTransaction_CustomerNotFound() {
	      
	        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

	       
	        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
	        	customerService.addTransaction(customerId, transaction);
	        });

	        assertEquals("Customer not found with ID: 1", exception.getMessage());
	        
	        verify(customerRepository, times(1)).findById(customerId);
	        verify(transactionRepository, times(0)).save(any());
	    }

	 

}
