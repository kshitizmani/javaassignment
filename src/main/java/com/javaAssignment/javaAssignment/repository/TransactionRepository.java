package com.javaAssignment.javaAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaAssignment.javaAssignment.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByCustomerId(Long customerId);

}
