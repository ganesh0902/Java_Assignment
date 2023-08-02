package com.custom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custom.entities.Customer;

@Repository
public interface CustomRepository extends JpaRepository<Customer, Long> {

}
