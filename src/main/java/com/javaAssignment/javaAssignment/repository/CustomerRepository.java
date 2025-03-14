package com.javaAssignment.javaAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaAssignment.javaAssignment.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
