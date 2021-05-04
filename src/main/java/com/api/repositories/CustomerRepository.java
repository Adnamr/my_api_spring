package com.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	

}
