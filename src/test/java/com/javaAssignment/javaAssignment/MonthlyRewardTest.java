package com.javaAssignment.javaAssignment;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
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
import com.javaAssignment.javaAssignment.service.RewardService;

@ExtendWith(MockitoExtension.class)
public class MonthlyRewardTest {

	@InjectMocks
    private CustomerService customerService;

	@Mock
    private RewardService rewardService;
	
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;
    
    private Customer customer;
    private List<Transaction> transactions;
    Long customerId;
    int month;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        customerId = 1L;
        month = 3;
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Akash singh");
        
        transactions = List.of(
                new Transaction(1L,120.0, LocalDate.of(2025, 3, 10), 1L),
                new Transaction(2L, 75.0, LocalDate.of(2025, 3, 15), 1L)
        );
        
    }

    @Test
    void testCalculateCustomerMonthlyReward_Success() {
       

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);
        when(rewardService.calculateMonthlyRewardPoints(transactions, month)).thenReturn(90);

        
        int rewardPoints = customerService.calculateCustomerMonthlyReward(customerId, month);

        
        assertEquals(90, rewardPoints);
        verify(customerRepository, times(1)).findById(customerId);
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
        verify(rewardService, times(1)).calculateMonthlyRewardPoints(transactions, month);
    }

    @Test
    void testCalculateCustomerMonthlyReward_CustomerNotFound() {
        

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
        	customerService.calculateCustomerMonthlyReward(customerId, month);
        });

        assertEquals("Customer not found with ID: 1", exception.getMessage());
        verify(customerRepository, times(1)).findById(customerId);
        verify(transactionRepository, times(0)).findByCustomerId(any());
    }

    

    @Test
    void testCalculateCustomerMonthlyReward_NoTransactions() {
      

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(List.of()); // No transactions

        when(rewardService.calculateMonthlyRewardPoints(List.of(), month)).thenReturn(0);

        
        int rewardPoints = customerService.calculateCustomerMonthlyReward(customerId, month);

        
        assertEquals(0, rewardPoints);
        
        verify(customerRepository, times(1)).findById(customerId);
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
        verify(rewardService, times(1)).calculateMonthlyRewardPoints(List.of(), month);
    }
	
	
	
	
}
