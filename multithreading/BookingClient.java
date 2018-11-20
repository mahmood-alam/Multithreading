// Insert header here
package assignment6;

import java.util.Map;

import assignment6.Theater.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Thread;

public class BookingClient {
	
	private Map<String, Integer> office;
	private Theater theater;
	private List<Thread> simulation;
	private int client = 1;
	
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
	  this.office = office;
	  this.theater = theater;
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
		//TODO: Implement this method
		List<Thread> thread = new ArrayList<Thread>();
		for(Map.Entry<String,Integer> entry : office.entrySet()) {
			Thread object = new Thread(new MultithreadingDemo(entry.getKey(), entry.getValue())); 
			thread.add(object);
            object.start(); 
		}
		return thread;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("BX1", 3);
        myMap.put("BX2", 4);
        myMap.put("BX3", 3);
        myMap.put("BX4", 3);
        myMap.put("BX5", 3);
        Theater theater = new Theater(3,5,"Ouija");
        BookingClient newClient = new BookingClient(myMap, theater);
        List<Thread> thread = newClient.simulate();
        for(Thread t: thread) {
        	t.join();
        }
        System.out.println("Sorry, we are sold out!");
	}
	
	class MultithreadingDemo implements Runnable 
	{ 
		private boolean done = false;
		private String id;
		private int customers;
		
		public MultithreadingDemo(String id, int customers) {
			this.id = id;
			this.customers = customers;
		}
		
	    public void run() 
	    { 
		        try
		        { 
		        	
		        	while(customers>0) {
		        		Ticket ticket = null;
		        		
		        		synchronized (theater) {
		        			if(theater.bestAvailableSeat()==null) { customers=0; }
		        			ticket = theater.printTicket(id, theater.bestAvailableSeat(), client);
		        			if(ticket!=null) {
			        			client++;
			        			customers--;
			        		}
		        		};	
		        		
		        	}
		  
		        } 
		        catch (Exception e) 
		        { 
		            // Throwing an exception 
		            System.out.println ("Exception is caught"); 
		            System.out.println(e);
		        } 

	    } 
	    
	} 

	
}

