# FastHotel API

# Motivation
* The problem domain is the transportation of customers to the closest (nearby ) hotel.
The specific SOA solution was to make the customer able to find the closest hotel to his location. It also orders a taxi to give him a ride to the hotel, and it takes care of the payment process with all its details.

* Why use service computing?
  -Less cost.
  -Availability.
  -Better maintenance.
  -Reusability.
  
*Why use composition?
  -We use composition because provides services for new software applications or Api´s.
  
*Why did you choose these third parties?
 -Easy to use.
 -More documentation.
 -Free keys.
 -Stable and secure.





# SOA Diagram

![websoa _diagram 2](https://user-images.githubusercontent.com/37571215/50399502-9b106380-0788-11e9-9b06-b0f369fb2bea.png)

# BPMN 2.0 Diagram

![bpmn 2 0_diagramf 1](https://user-images.githubusercontent.com/44376115/50547892-ec6a9800-0c4b-11e9-99a9-b4c16ac84b44.png)


# Implementation Details
We developed our service using the RESTful service development paradigm, which is based on the HTTP protocol that is an RPC- based sychronous communication protocol.

#  Composite Service Algorithm According To BPMN 2.0

# FastHotelControllerApi


Method | HTTP request | Description
------------- | ------------- | -------------
[**addCustomerUsingPOST1**](FastHotelControllerApi.md#addCustomerUsingPOST1) | **POST** /composite/customer | addCustomer
[**findCustomerByNameUsingGET1**](FastHotelControllerApi.md#findCustomerByNameUsingGET1) | **GET** /composite/customer | findCustomerByName
[**getCustomerByIDUsingGET1**](FastHotelControllerApi.md#getCustomerByIDUsingGET1) | **GET** /composite/customer/{id} | getCustomerByID
[**getCustomersUsingGET1**](FastHotelControllerApi.md#getCustomersUsingGET1) | **GET** /composite | customers
[**getDirectioForHotelUsingGET**](FastHotelControllerApi.md#getDirectioForHotelUsingGET) | **GET** /composite/Customer-direction | Get Customer by Location

# CustomerControllerApi




Method | HTTP request | Description
------------- | ------------- | -------------
[**addCustomerUsingPOST**](CustomerControllerApi.md#addCustomerUsingPOST) | **POST** /customer/customer | addCustomer
[**findCustomerByNameUsingGET**](CustomerControllerApi.md#findCustomerByNameUsingGET) | **GET** /customer/customer | findCustomerByName
[**getCustomerByIDUsingGET**](CustomerControllerApi.md#getCustomerByIDUsingGET) | **GET** /customer/customer/{id} | getCustomerByID
[**getCustomersUsingGET**](CustomerControllerApi.md#getCustomersUsingGET) | **GET** /customer | customers


# CustomerModelClass

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**customerID** | **Integer** | 100 | 
**customerName** | **String** | yara | 
**location** | **String** | loc | 

# TaxiControllerApi



Method | HTTP request | Description
------------- | ------------- | -------------
[**getCustomerlocationUsingGET**](TaxiControllerApi.md#getCustomerlocationUsingGET) | **GET** /taxies/taxies-Customer-Location | Get Location of The customer
[**getPathToCustomerUsingGET**](TaxiControllerApi.md#getPathToCustomerUsingGET) | **GET** /taxies/taxies-Direction-OfCustomer | Get Location of The customer

 
