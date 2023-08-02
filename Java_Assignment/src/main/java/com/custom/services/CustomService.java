package com.custom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.entities.Customer;
import com.custom.repository.CustomRepository;

@Service
public class CustomService {

	@Autowired
	private CustomRepository repository;

	public boolean addUser(Customer customer) {
		Customer save = repository.save(customer);

		if (save != null) {
			return true;
		} else {
			return false;

		}
	}

	public List<Customer> getAllRecord() {
		List<Customer> findAll = repository.findAll();
		return findAll;
	}

	public boolean deleteById(long id) {

		repository.deleteById(id);

		return true;
	}

	public Customer getCustomerById(long id) {
		Optional<Customer> findById = repository.findById(id);

		Customer customer = findById.get();

		return customer;
	}
}