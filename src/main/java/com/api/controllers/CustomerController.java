package com.api.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entities.Customer;
import com.api.exception.ConflictException;
import com.api.exception.NotExistException;
import com.api.exception.UnknowException;
import com.api.services.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController extends BaseController{

	@Autowired
	private CustomerService customerService;
		
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>>  getAll()
	{
		try 
		{
			List<Customer> customers = customerService.findAll();
			
			return new ResponseEntity<>(customers, HttpStatus.OK);
			
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("customer/{id}")
	public ResponseEntity<Customer> findId(@PathVariable int id)
	{
		try 
		{
			Optional<Customer> customer = customerService.find(id);

			return new ResponseEntity<>(customer.get(),HttpStatus.OK);
		} 
		catch (NoSuchElementException e) {
			log.error(e.getMessage());
			throw new NotExistException(String.format(e.getMessage()));
		}
		catch (Throwable e) {
			log.error(e.getMessage());
			throw new UnknowException(String.format(e.getMessage()));
		}
		
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer)
	{
		try 
		{
			
			if (customerService.find(customer.getCustomerNumber()) != null ) 
			{
				throw new ConflictException("Already exist Customer number");
			}
			
			customerService.save(customer);
			
			return new ResponseEntity<>(customer,HttpStatus.CREATED);
			
		} 
		catch (Throwable e) 
		{
			log.error(e.getMessage());
			throw new UnknowException(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer,@PathVariable int id)
	{
		try 
		{
			log.debug("Update Customer");
			Optional<Customer> c_check = customerService.find(id);

			
			if (c_check.get() != null) 
			{
				customerService.save(customer);
				return new ResponseEntity<>(customer,HttpStatus.OK);
			}
			
		} 
		catch (NoSuchElementException e)
		{
			log.error("ERROR :: "+e.getMessage(),e);
			throw new NotExistException(String.format("Customer Not found"));
		}
		catch (Throwable e)
		{
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(e.getMessage());
		}
		return null;
	}
	
	@DeleteMapping("customer/{id}")
	public ResponseEntity<Customer> delete(@PathVariable int id) throws ConstraintViolationException
	{
		
		try 
		{
			Optional<Customer> customer = customerService.find(id);
			
			customerService.delete(customer);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			throw new NotExistException(e.getMessage());
		}
		
		
	}
	
	
}
