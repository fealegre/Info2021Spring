package com.info2021.ecommerce.controller;
import com.info2021.ecommerce.domain.Customer;
import java.util.List;
import javax.validation.Valid;
import com.info2021.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

   
    //LIST ALL CUSTOMERS
	@GetMapping("/customer")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	//CREATE CUSTOMER
	@PostMapping("/createCustomer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	//FIND CUSTOMER BY ID	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	//UPDATE CUSTOMER
	@PutMapping("/updateCustomer/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") Long id,@Valid @RequestBody Customer customerDetails) {
		Customer customer=customerRepository.findById(id).orElse(null);
		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		customer.setAddress(customerDetails.getAddress());
		customer.setCustName(customerDetails.getUCustName());
		Customer customerUpdated=customerRepository.save(customer);
		return customerUpdated;
	}
	
	//DELETE CUSTOMER
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id){
		
		Customer xcustomer= customerRepository.findById(id).orElse(null);
		customerRepository.delete(xcustomer);
		return ResponseEntity.ok().build();
	}
    
}
