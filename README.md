# FastHotel API

 ## Motivation
   * The problem domain is the transportation of customers to the closest (nearby ) hotel.
      The specific SOA solution was to make the customer able to find the closest hotel to his location. It also orders a taxi to give         him a ride to the hotel, and it takes care of the payment process with all its details.

 * Why use service computing?
    - Less cost.
    - Availability.
    - Better maintenance.
    - Reusability.
  
 * Why use composition?
      - We use composition because provides services for new software applications or Api´s.
  
 * Why these third parties?
    - Easy to use.
    - More documentation.
    - Free keys.
    - Stable and secure.

# Service Design

  ## SOA Diagram

   ![websoa _diagram 2](https://user-images.githubusercontent.com/37571215/50399502-9b106380-0788-11e9-9b06-b0f369fb2bea.png)
   
# Details

 ## 3_Tire Client Server Architecture :
  * Client :
    - Advanced REST Client.
    
  * Services :
    - Direction (Hotel / Customer).
    - place (Customer).
    - Payment.
  
 
 ## Services : 
  * Composite : 
    - FastHotel Controller.
  * Atomic : 
    - Customer Controller.
    - Hotel Controller.
    - Taxi Controller.
    - PayPal Controller.
  * Third Party : 
    - Geolocation Map
    
 ## Users can use FastHotel API functionalities provided by Web services API in the following order:
 
   1-  User sends a request(with his current location ) to FastHotel API.
   
   2-  FastHotel API gets the current location of the cusomer and finds the path (Direction) to the customer.
   
   4-  FastHotel API finds the closest hotel path (direction) to the customer using Google Places and Maps, and sends the (customer             location , hotel path) to a taxi.
   
   5-  The taxi picks up the customer, give him a drive to the hotel.
   
   6-  FastHotel API takes care of the financial work using PayPal.
   



  ## BPMN 2.0 Diagram

   ![fffff](https://user-images.githubusercontent.com/44376115/50550208-2d76a280-0c74-11e9-8981-f5ac940d2a96.png)
   
 ## Details
 ### Start : 
   * A cutomer needs to find a close hotel.
   
     - FastHotel API does the following :
     
         1- Gets the customer current location from the DataBase.
        
         2- Send the customer's current location to the Taxi service.
        
         #### 3- Parallel Search " FastHotel API and Taxi service : 
        
              * FastHotel searches for hotels that are close to the customer's current location.
          
              - Uses Places API and searches by raduis.
              
              - Uses Google Maps API to calculate the shortest hotel path " The closest hotel to the customer location".
              - Loop : If the result of searching by the raduis was null "no hotels within this raduis", then increase the raduis and                           search again until you find at least one hotel.
              
           * Taxi Service Searches for the path "Direction" to the customer in order to pick Him/Her up.
              - Uses Google Maps API.
              
        4- FastHotel Sends the directions of the hotel to the Taxi service.
        
        5- The Taxi picks up the customer and gives Him/Her a drive to the hotel.
        
        6- The customer makes a payment request, FastHotel sends the customer information to PayPal Api.
        
        7- PayPal API makes all the payment functions.
        
  ### End
    
   

# Implementation Details
  We developed our service using the RESTful service development paradigm, which is based on the HTTP protocol that is an RPC- based       sychronous communication protocol.

# Composite Service Algorithm According To BPMN 2.0

  ## FastHotelControllerApi


Method | HTTP request | Description
------------- | ------------- | -------------
[**addCustomerUsingPOST1**](FastHotelControllerApi.md#addCustomerUsingPOST1) | **POST** /composite/customer | addCustomer
[**findCustomerByNameUsingGET1**](FastHotelControllerApi.md#findCustomerByNameUsingGET1) | **GET** /composite/customer | findCustomerByName
[**getCustomerByIDUsingGET1**](FastHotelControllerApi.md#getCustomerByIDUsingGET1) | **GET** /composite/customer/{id} | getCustomerByID
[**getCustomersUsingGET1**](FastHotelControllerApi.md#getCustomersUsingGET1) | **GET** /composite | customers
[**getDirectioForHotelUsingGET**](FastHotelControllerApi.md#getDirectioForHotelUsingGET) | **GET** /composite/Customer-direction | Get Customer by Location

  ## CustomerControllerApi


Method | HTTP request | Description
------------- | ------------- | -------------
[**addCustomerUsingPOST**](CustomerControllerApi.md#addCustomerUsingPOST) | **POST** /customer/customer | addCustomer
[**findCustomerByNameUsingGET**](CustomerControllerApi.md#findCustomerByNameUsingGET) | **GET** /customer/customer | findCustomerByName
[**getCustomerByIDUsingGET**](CustomerControllerApi.md#getCustomerByIDUsingGET) | **GET** /customer/customer/{id} | getCustomerByID
[**getCustomersUsingGET**](CustomerControllerApi.md#getCustomersUsingGET) | **GET** /customer | customers


  ## CustomerModelClass

   ## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**customerID** | **Integer** | 100 | 
**customerName** | **String** | yara | 
**location** | **String** | loc | 

 ## TaxiControllerApi



Method | HTTP request | Description
------------- | ------------- | -------------
[**getCustomerlocationUsingGET**](TaxiControllerApi.md#getCustomerlocationUsingGET) | **GET** /taxies/taxies-Customer-Location | Get Location of The customer
[**getPathToCustomerUsingGET**](TaxiControllerApi.md#getPathToCustomerUsingGET) | **GET** /taxies/taxies-Direction-OfCustomer | Get Location of The customer

 
