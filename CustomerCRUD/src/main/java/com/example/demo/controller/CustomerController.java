package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("add")
	public String add(@RequestBody Customer customer) {
		customerService.add(customer);
		return "Customer Added Successfully!!!";
	}

	@PostMapping("addAll")
	public String addAll(@RequestBody List<Customer> list) {
		customerService.addAll(list);
		return "Customers Added Successfully!!!";
	}

	@GetMapping("display")
	public ResponseEntity<List<Customer>> display() {
		return customerService.display();
	}

	@DeleteMapping("delete/{id}")
	public Customer delete(Integer id) {
		return customerService.delete(id);
	}

	@PostMapping("search/{id}")
	public Customer search(Integer id) {
		return customerService.search(id);
	}

	@PostMapping("searchByMob/{mob}")
	public Customer searchMob(@PathVariable String mob) {
		return customerService.findByMob(mob);
	}
}
