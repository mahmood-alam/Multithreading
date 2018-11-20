// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.List;

public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			String s = getRowLetter();
			return s+seatNum;
			
		}
		
		public String getRowLetter() {
			int test = rowNum;
			String answer = "";
			while(test != 0) {
				int i = test%26;
				
				String s = i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
				answer += s;
				
				test/=26;
			}
			return answer;
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
			String s = "";
			System.out.println("-------------------------------");
			s+="-------------------------------\n";
			
			System.out.print("| Show: "+show);
			s+="| Show: "+show;
			for(int spaces = 22 - show.length();spaces>0;spaces--) {
				System.out.print(" ");
				s+=" ";
			}
			System.out.println("|");
			s+="|\n";
			
			System.out.print("| Box Office ID: "+ boxOfficeId);
			s+="| Box Office ID: "+ boxOfficeId;
			for(int spaces = 13 - boxOfficeId.length();spaces>0;spaces--) {
				System.out.print(" ");
				s+=" ";
			}
			System.out.println("|");
			s+="|\n";
			
			System.out.print("| Seat: "+ seat.toString());
			s+="| Seat: "+ seat.toString();
			for(int spaces = 22 - seat.toString().length();spaces>0;spaces--) {
				System.out.print(" ");
				s+=" ";
			}
			System.out.println("|");
			s+="|\n";
			
			System.out.print("| Client: "+client);
			s+="| Client: "+client;
			for(int spaces = 20 - String.valueOf(client).length();spaces>0;spaces--) {
				System.out.print(" ");
				s+=" ";
			}
			System.out.println("|");
			s+="|\n";
			
			System.out.println("-------------------------------");
			s+="-------------------------------\n\n";
			return s;
		}
	}
	
	private int numRows;
	private int seatsPerRow;
	private String show;
	private int availableSeats;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	
	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		this.show = show;
		availableSeats = this.numRows * this.seatsPerRow;
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
		//TODO: Implement this method
		if(availableSeats==0) {return null;}
		if(tickets.size()==0) {
			Seat newSeat = new Seat(1,1);
			return newSeat;
		} 
		Ticket last = tickets.get(tickets.size()-1);
		if(last.seat.seatNum<seatsPerRow) {
			Seat newSeat = new Seat(last.seat.rowNum, last.seat.seatNum+1);
			return newSeat;
		}
		Seat newSeat = new Seat(last.seat.rowNum+1, 1);
		return newSeat;
		
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method
		Seat best = seat;
		if(best==null) {
			return null;
		}
		Ticket newTicket = new Ticket(show, boxOfficeId, best, client);
		tickets.add(newTicket);
		availableSeats--;
		newTicket.toString();
		return newTicket;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return tickets;
	}
}
