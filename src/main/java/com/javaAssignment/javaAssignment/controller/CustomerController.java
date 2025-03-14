package com.javaAssignment.javaAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaAssignment.javaAssignment.entity.Transaction;
import com.javaAssignment.javaAssignment.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	
	    @Autowired
	    private CustomerService customerService;

	    
	    @GetMapping("/{customerId}/rewards/{month}")
	    public ResponseEntity<Integer> getMonthlyRewardPoints(@PathVariable Long customerId, @PathVariable int month) {
	    	
	    	 int monthlyReward= customerService.calculateCustomerMonthlyReward(customerId,month);
	         return new ResponseEntity<>(monthlyReward, HttpStatus.OK);
	    }
	       

	    @GetMapping("/{customerId}/rewards/total")
	    public ResponseEntity<Integer> getTotalRewardPoints(@PathVariable Long customerId) {
	    	
	       int totalrewards= customerService.calculateCustomerTotalReward(customerId);
	       
	       return new ResponseEntity<>(totalrewards, HttpStatus.OK);
	    }

	    @PostMapping("/{customerId}/transactions")
	    public ResponseEntity<String> addTransaction(@PathVariable Long customerId, @RequestBody Transaction transaction) {
	    	
	       String transactionData= customerService.addTransaction(customerId, transaction);
	       
	       return new ResponseEntity<>(transactionData, HttpStatus.OK);
	    }
	    
	    @PostMapping("/addCustomer/{customerName}")
	    public ResponseEntity<String> addCustomer(@PathVariable String customerName) {
	    	
	       System.out.println(customerName);
	       
	       String data= customerService.addCustomer(customerName);
	       
	       return  new ResponseEntity<>(data, HttpStatus.OK);
	    }

}
