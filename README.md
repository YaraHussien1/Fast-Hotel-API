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
   
   4-  FastHotel API finds the closest hotel path (direction) to the customer using Google Places and Maps,                                      and sends the (customer location , hotel path) to a taxi.
   
   5-  The taxi picks up the customer, give him a drive to the hotel.
   
   6-  FastHotel API takes care of the financial work using PayPal.
   



  ## BPMN 2.0 Diagram

   ![fffff](https://user-images.githubusercontent.com/44376115/50550208-2d76a280-0c74-11e9-8981-f5ac940d2a96.png)
   
 ## Details
 ### Start : 
   * A customer needs to find a close hotel.
   
     - FastHotel API does the following :
     
      1- Gets the customer's current location from the DataBase.
        
      2- Send the customer's current location to the Taxi service.
         
      3- Parallel Search " FastHotel API and Taxi service"  : 
         
       #### A- FastHotel searches for hotels that are close to the customer's current location.
          
        - Uses Places API and searches by raduis.
               
        - Uses Google Maps API to calculate the shortest hotel path " The closest hotel to the customer location" .
        - Loop :
                -  If the result of searching by the raduis was null "no hotels within this raduis" , then increase the raduis and                           search again until you find at least one hotel.
              
       #### B- Taxi Service Searches for the path "Direction" to the customer in order to pick Him/Her up.
       
        - Uses Google Maps API.
              
      4- FastHotel Sends the directions of the hotel to the Taxi service.
        
      5- The Taxi picks up the customer and gives Him/Her a drive to the hotel.
        
      6- The customer makes a payment request, FastHotel sends the customer information to PayPal Api.
        
      7- PayPal API makes all the payment functions.
        
 ### End
    
   

# Implementation Details
  We developed our service using the RESTful service development paradigm, which is based on the HTTP protocol that is an RPC- based       sychronous communication protocol.

# Composite Service Algorithm According To BPMN 2.0
 ## FastHotel Composite
 
     '''java @RestController
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
			if (u.getCustomerID() == i.getCustomerID())
				throw new UserAlreadyExistsException();
		}	
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
	}'''
 
 
 
  ## Applied Technologies
  
  * Development Environment : STS , Eclipse.
  
  * Programming Languages : (JDK) java development kit 8.
  
  * Frameworks and libraries : swagger 2.0 , Spring Boot.
  
  * Deployment platforms : Google App Engine (GAE), which is a Google Cloud Platform(GCP) service provided by Google.
  
  * Application Server : Apache Tomcat ( Localhost 8080 ).
  
  ## Servise Deployment And Monitering
  
  * ##   Log File
  
    *  [   **Our Log File**  ](Team11LogFile.csv)
   
  # Conclusion and Discussion
  - The main idea of our API is to give the customer the closest hotel location, direction and to order a taxi to drive him to the           destination.
   - One of the problems was the lack of time, if we had more time we would produce a better work for sure.
   - some technical difficulties came across too, like trying to connect to the local host which eventually became successful, and             deploying the project to Google app engine made a lot of unexpected errors, which took a lot of time from us to try to solve them.
   - If we had more time I’m sure we will manage to launch a well structured and very helpful API.
   

   

