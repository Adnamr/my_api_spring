package com.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.Customer;
import com.api.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll()
	{
		List<Customer> customers = new ArrayList<Customer>();
		
		customerRepository.findAll().forEach(customers::add);
		
		return customers;
	}
	
	public Optional<Customer> find(int id)
	{
		return customerRepository.findById(id);
	}
	
	
	public Customer save(Customer customer)
	{
		return customerRepository.save(customer);
	}
	
	public void delete(Customer customer)
	{
		customerRepository.delete(customer);
	}
}
