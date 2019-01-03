package com.example.demo.controller;
import java.util.ArrayList;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.maps.Maps;
import com.example.demo.maps.Places;
import com.example.demo.model.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.alidg.errors.annotation.ExceptionMapping;

@RestController
	@RequestMapping("/composite")
	public class FastHotelController
	{
		
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	
	//To Get The Closest Hotel Path To The customer
	@ApiOperation(value = "Get Customer by Location", response = Customer.class)
	@RequestMapping(method = RequestMethod.GET, value = "/Customer-direction")
	public String getDirectioForHotel(@RequestParam(value = "id", defaultValue ="0") int id,
			@RequestParam(value = "name", defaultValue = "yara") String name,
			@RequestParam(value = "location", defaultValue ="31.922428,35.208054") String location) 
	{
		Customer c = new Customer(id, name, location);
		// get the nearBy Hotel "The closest one
		//System.out.println("GETDierc Method returns : " +getDirection(c));
		return getDirection(c);
	}
	
		
	//Get All the Customers 
	@RequestMapping(produces = {"application/xml","application/json"},
			method = RequestMethod.GET) // REST annotation
	@ApiOperation(value = "customers", // Swagger annotation
			notes = "This operation retrieves all customers ", response = ArrayList.class)
	ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
	//Get Customer By ID
	@ApiOperation(value = "getCustomerByID", notes = "This operation retrieves a Customer by it's ID", response = Customer.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "server error"),
			@ApiResponse(code = 200, message = "request handled successfully") })
	
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}") // Rest annotation
	Customer getCustomerByID(@PathVariable int id) 
	{

		for (Customer i : customers) 
		{
			if (i.getCustomerID() == id)
				return i;
		}
		return null;
	}
	

	// Get Customer By Name
	@RequestMapping(method = RequestMethod.GET, value = "/customer")
	String findCustomerByName(@RequestParam(value = "name", defaultValue = "Yara") String name)
	{

		for (Customer i : customers)
		{
			if (i.getCustomerName().equals(name))
				return name + " exists";
		}
		return name + " does not exist!";
	}
	
	// Add A New Customer
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	HttpEntity<Customer> addCustomer(@RequestBody Customer u)
	{	System.out.println("POST METHOD IS Called");

		for (Customer i : customers)
		{
			// DP2: Exception shielding - Error codes
			// Source: https://github.com/alimate/errors-spring-boot-starter
			if (u.getCustomerID() == i.getCustomerID())
				throw new UserAlreadyExistsException();
			System.out.println("EXITSTIG CUSTOMER");

		}
		
		System.out.println("ADDED SUCCESSFULLY");

		customers.add(u);
		
		
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@ExceptionMapping(errorCode = "user.already_exists", statusCode = HttpStatus.BAD_REQUEST)
	public class UserAlreadyExistsException extends RuntimeException 
	{
				
		
	}
	
	
	// Get nearby hotels ad get the location of the closest one to the customers location
	public static String getDirection (Customer c)
	{
		ArrayList<Hotel> hotels;
		int i =100;
		do
		{
			hotels = Places.getNearByHotel(c.getLocation(), i);
			i+=100;
		}while (hotels.size()<=0);
		//System.out.println("GETDirection method returns this : "+Maps.Direction(c.getLocation(), hotels.get(0).getName()));
			return Maps.Direction(c.getLocation(), hotels.get(0).getLocation());
	}
	
	
	
	}

