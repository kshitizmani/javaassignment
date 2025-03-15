package com.javaAssignment.javaAssignment.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaAssignment.javaAssignment.entity.Transaction;

@Service
public class RewardService {     
	
	
	  // Calculate reward points for a list of transactions for a particular month
	
    public int calculateMonthlyRewardPoints(List<Transaction> transactions, int month) {
        return transactions.stream()
                .filter(transaction -> transaction.getDate().getMonthValue() == month)
                .mapToInt(this::calculateRewardPoints)
                .sum();
    }
	

    // Calculate reward points for a specific transaction
    private int calculateRewardPoints(Transaction transaction) {
        double amount = transaction.getAmount();  
    	int points = 0;  
        if (amount > 100) {
            points += (amount - 100) * 2; // 2 points for every $ above 100
            points += 50; // 1 point for each dollar between 50-100
        } else if (amount > 50) {
            points += (amount - 50); // 1 point for each dollar between 50-100
        }
        return points;
        
    }

    // Calculate total reward points for all transactions
    public int calculateTotalRewardPoints(List<Transaction> transactions) {
        return transactions.stream()
                .mapToInt(this::calculateRewardPoints)
                .sum();
    }
	
	
	
	

}
