package com.javaAssignment.javaAssignment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaAssignment.javaAssignment.entity.Customer;
import com.javaAssignment.javaAssignment.entity.Transaction;
import com.javaAssignment.javaAssignment.exception.CustomerNotFoundException;
import com.javaAssignment.javaAssignment.exception.InvalidAmountException;
import com.javaAssignment.javaAssignment.repository.CustomerRepository;
import com.javaAssignment.javaAssignment.repository.TransactionRepository;

@Service
public class CustomerService {
	
	
	    @Autowired
	    private RewardService rewardService;

	    @Autowired
	    private TransactionRepository transactionRepository;

	    @Autowired
	    private CustomerRepository customerRepository;

//	    public int calculateCustomerMonthlyReward(Long customerId, int year, int month) {
//	    	List<Customer> list=customerRepository.findAll();
//	    	System.out.println(list.toString());
//	    	
//	        Customer customer = customerRepository.findById(customerId)
//	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
//            System.out.println(customer.toString());
//	        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
//	        return rewardService.calculateMonthlyRewardPoints(transactions, year, month);
//	    }

	    public int calculateCustomerMonthlyReward(Long customerId, int month) {
	    	
	    	List<Customer> list=customerRepository.findAll();
	    	System.out.println(list.toString());
	    	
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
	        
            
	        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
	        
	        return rewardService.calculateMonthlyRewardPoints(transactions, month);
	    }

	    public int calculateCustomerTotalReward(Long customerId) {
	    	
	    	List<Customer> list=customerRepository.findAll();
	    	System.out.println(list.toString());
	    	
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
	        System.out.println(customer.toString());
	        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
	        return rewardService.calculateTotalRewardPoints(transactions);
	    }

	    public String addTransaction(Long customerId, Transaction transaction) {
	    	
	    	String transactionData="";
	    	
	        if (transaction.getAmount() <= 0) {
	            throw new InvalidAmountException("Transaction amount must be greater than zero.");
	        }

	        List<Customer> list=customerRepository.findAll();
	    	System.out.println(list.toString());
	    	
	        Optional<Customer> customer = customerRepository.findById(customerId);
	        if (customer.isPresent()) {
	            transaction.setCustomerId(customerId);
	            transactionRepository.save(transaction);
	            transactionData="Transaction Record saved successfully";
	            
	        } else {
	            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
	        }
	        
	        return transactionData;
	    }

		public String addCustomer(String customerName) {
			String data="";
			
			try {
			Customer cust=new Customer();
			cust.setName(customerName);
			customerRepository.save(cust);
			data="Customer saved successful";
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return data;
		}

}
