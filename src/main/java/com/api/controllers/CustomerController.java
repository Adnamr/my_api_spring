package com.api.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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
public class CustomerController extends BaseController {

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
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(e.getMessage());
		}
		
	}
	
	@GetMapping("customer/{id}")
	public ResponseEntity<Customer> findId(@PathVariable int id)
	{
		try 
		{
			Customer customer = customerService.find(id).orElseThrow(() -> new NotExistException("Customer "+ id +"not found"));

			return new ResponseEntity<>(customer,HttpStatus.OK);
		} 
		catch (NoSuchElementException e) {
			log.error("ERROR :: "+e.getMessage(),e);
			throw new NotExistException(String.format(e.getMessage()));
		}
		catch (Throwable e) {
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(String.format(e.getMessage()));
		}
		
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer)
	{
		try 
		{
			
			if (customerService.find(customer.getCustomerNumber()) == null ) 
			{
				customerService.save(customer);
				return new ResponseEntity<>(customer,HttpStatus.CREATED);

			}
			else
			{
				throw new ConflictException(customer.getCustomerNumber() +" Already exist");
			}
			
		} 
		catch (Throwable e) 
		{
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(e.getMessage());
		}
	}
	
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer,@PathVariable int id)
	{
		try 
		{
			log.debug("Update Customer");
			customerService.find(id).orElseThrow(() -> new NotExistException(id + "Customer not found"));

			customerService.save(customer);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		} 
		catch (Throwable e)
		{
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(e.getMessage());
		}
	}
	
	@DeleteMapping("customer/{id}")
	public ResponseEntity<Customer> delete(@PathVariable int id) throws ConstraintViolationException
	{
		
		try 
		{
			Customer customer = customerService.find(id).orElseThrow(() -> new NotExistException(id + "Customer not found"));
			customerService.delete(customer);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} 
		catch (Throwable e)
		{
			log.error("ERROR :: "+e.getMessage(),e);
			throw new UnknowException(e.getMessage());
		}
		
		
	}
	
	
}
