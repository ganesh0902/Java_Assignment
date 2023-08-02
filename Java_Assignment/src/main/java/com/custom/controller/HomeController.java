package com.custom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.custom.entities.Customer;
import com.custom.services.CustomService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private CustomService service;

	@GetMapping("/index") // Home Page
	public String homePage(Model model) {

		List<Customer> allRecord = service.getAllRecord();
		model.addAttribute("records", allRecord);

		return "Index";
	}

	@GetMapping("/add") // click on Add Customer button
	public String addCustomer() {
		return "addCustome";
	}

	@PostMapping("/save") // Save Customer Record
	public ResponseEntity<?> save(@RequestBody Customer customer) {

		System.out.println("Hello");
		boolean addUser = service.addUser(customer);

		// return "redirect:/home/index";

		if (addUser != true) {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Something went wrong");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Successfully");
		}
	}

	@GetMapping("/addCustomer")
	public String add() {
		return "addCustome";
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id, Model m) {

		boolean deleteById = service.deleteById(id); // If record delete then get true
		String str = Long.toString(id);
		// return "redirect:/home/index";

		if (deleteById == true) {
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
		} else if (str == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UUID Not Found");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : Not Deleted");
		}
	}

	@GetMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") long id, Model m) {

		Customer customerById = service.getCustomerById(id);
		String cid = Long.toString(id);
		m.addAttribute("record", customerById);

		// return "Update";
		if (customerById != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
		} else if (cid == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UUID Not Found");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : Not Deleted");
		}
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> handleNotFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid API endpoint");
	}
}