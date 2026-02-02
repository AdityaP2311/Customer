package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.InvalidCustomerName;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public void validCustomer(Customer customer) {

		String nm = customer.getName().trim();

		for (int i = 0; i < nm.length(); i++) {
			if (Character.isDigit(nm.charAt(i))) {
				throw new InvalidCustomerName("Digits are not Allowed in Name!!");
			}

		}

		String mob = customer.getMob().trim();
		if (mob.length() != 13) {
			throw new InvalidMobileNumber("Mobile Number must be of 13 Characters");
		}

		if (!(mob.startsWith("+91"))) {
			throw new InvalidMobileNumber("Mobile number must start with +91");
		}

		if (mob.charAt(3) < '6') {
			throw new InvalidMobileNumber("Invalid Indian mobile number");
		}

		for (int i = 0; i < mob.length(); i++) {
			if (!Character.isDigit(mob.charAt(i))) {
				throw new InvalidMobileNumber("Only digits are allowed after +91");
			}
		}

		if (customerRepository.existsByMob(mob)) {
			throw new InvalidMobileNumber("Duplicate Mobile Number is not Allowed");
		}
	}

	@Override
	public void add(Customer customer) {
		validCustomer(customer);
		customerRepository.save(customer);
	}

	@Override
	public ResponseEntity<List<Customer>> display() {

		List<Customer> customers = customerRepository.findAll();

		if (customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	@Override
	public Customer delete(Integer id) {
		if (customerRepository.findById(id).isPresent()) {
			Customer temp = customerRepository.findById(id).get();
			customerRepository.deleteById(id);
			return temp;
		} else {
			throw new IdNotFoundException("ID not found");
		}
	}

	@Override
	public void update(Customer customer, Integer id) {
		if (!customerRepository.existsById(id))
			throw new IdNotFoundException("Id not Found!!");

		customer.setId(id);
		customerRepository.save(customer);
	}

	@Override
	public Customer search(Integer id) {
		if (customerRepository.findById(id).isPresent()) {
			Customer temp = customerRepository.findById(id).get();
			return temp;
		} else {
			throw new IdNotFoundException("ID not found");
		}
	}

	@Override
	public void addAll(List<Customer> list) {
		for (Customer customer : list) {
			validCustomer(customer);
		}
		customerRepository.saveAll(list);
	}

	@Override
	public Customer findByMob(String mob) {
		return customerRepository.findByMob(mob);
	}

}
