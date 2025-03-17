package com.javaAssignment.javaAssignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import com.javaAssignment.javaAssignment.service.RewardService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceForTotalRewardTest {
	
	    @Mock
	    private CustomerRepository customerRepository;

	    @Mock
	    private TransactionRepository transactionRepository;

	    @Mock
	    private RewardService rewardService;

	    @InjectMocks
	    private CustomerService customerService;

	    private Customer customer;
	    private List<Transaction> transactions;

	    @BeforeEach
	    void setUp() {
	    	
	    	MockitoAnnotations.openMocks(this);
	    	
	        customer = new Customer();
	        customer.setId(1L);
	        customer.setName("Kshitiz Mani");

	        transactions = Arrays.asList(
	            new Transaction(1L, 100.0 , LocalDate.now() , 1L),
	            new Transaction(1L, 200.0 , LocalDate.now() , 1L)
	        );
	    }

	    @Test
	    void testCalculateCustomerTotalReward_Success() {
	        // Mock behavior
	        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
	        when(transactionRepository.findByCustomerId(1L)).thenReturn(transactions);
	        when(rewardService.calculateTotalRewardPoints(transactions)).thenReturn(250);

	        // Execute the method
	        int totalReward = customerService.calculateCustomerTotalReward(1L);

	        // Verify result
	        assertEquals(250, totalReward);

	        // Verify interactions
	        verify(customerRepository, times(1)).findById(1L);
	        verify(transactionRepository, times(1)).findByCustomerId(1L);
	        verify(rewardService, times(1)).calculateTotalRewardPoints(transactions);
	    }

	    @Test
	    void testCalculateCustomerTotalReward_CustomerNotFound() {
	        // Mock behavior for customer not found
	        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

	        // Verify exception is thrown
	        Exception exception = Assertions.assertThrows(
	            CustomerNotFoundException.class,
	            () -> customerService.calculateCustomerTotalReward(1L)
	        );

	        assertEquals("Customer not found with ID: 1", exception.getMessage());

	        // Verify interactions
	        verify(customerRepository, times(1)).findById(1L);
	        verify(transactionRepository, never()).findByCustomerId(anyLong());
	        verify(rewardService, never()).calculateTotalRewardPoints(anyList());
	    }

}
