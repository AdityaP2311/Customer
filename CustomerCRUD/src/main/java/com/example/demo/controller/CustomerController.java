package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;


@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Customer customer) {
		try {
			customerService.add(customer);
			return ResponseEntity.ok("Customer Added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PostMapping("/addAll")
	public ResponseEntity<?> addAll(@RequestBody List<Customer> list) {
		customerService.addAll(list);
		return ResponseEntity.ok("Customers Added Successfully");
	}

	@GetMapping("display")
	public ResponseEntity<List<Customer>> display() {
		return customerService.display();
	}

	@DeleteMapping("/delete/{id}")
	public Customer delete(@PathVariable Integer id) {
		return customerService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Customer customer) {
	    try {
	        Customer updated = customerService.update(customer, id);
	        return ResponseEntity.ok(updated);
	    } 
	    catch (IdNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } 
	    catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update Failed");
	    }
	}
	
	@PostMapping("/search/{id}")
	public Customer search(@PathVariable Integer id) {
		return customerService.search(id);
	}

	@PostMapping("searchByMob/{mob}")
	public Customer searchMob(@PathVariable String mob) {
		return customerService.findByMob(mob);
	}
}
