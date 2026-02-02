package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@ NoArgsConstructor
public class Customer {

	@Id
	private Integer id;
	private String name;
	private String mob;
	private String address;
	private float salary;
}
