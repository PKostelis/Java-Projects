
import java.util.ArrayList;

public class CentralRegistry {
    //private static ArrayList<Airport> airports;
    //private static ArrayList<Flight> flights;
	
	private static ArrayList<Airport> airports = new ArrayList<Airport>();
	private static ArrayList<Flight> flights = new ArrayList<Flight>();
	
	public CentralRegistry() {
		CentralRegistry.airports = new ArrayList<>();
		CentralRegistry.flights = new ArrayList<>();
    }

    // Καταχώρηση δρομολογίου
    public static void addFlight(Flight aFlight) {
        flights.add(aFlight);

        Airport departureCity = aFlight.getAirportA();
        Airport destinationCity = aFlight.getAirportB();
        String airlineCompany = aFlight.getAirline();
        
        departureCity.addConnectedAirport(destinationCity);
        destinationCity.addConnectedAirport(departureCity);
        
        departureCity.addAirlineCompany(airlineCompany);
        destinationCity.addAirlineCompany(airlineCompany);
    }
         
    // Καταχώρηση αεροδρομίου
    public static void addAirport(Airport anAirport){
    	airports.add(anAirport);
    }
    
    public static Airport getLargestHub() {
    	int largestHub = airports.get(0).getConnectedAirports().size(); 
    	int indexLargestHub = 0;
    	
		for (int i = 1; i < airports.size(); i++) {
    		if (airports.get(i).getConnectedAirports().size() > largestHub) {
    			largestHub = airports.get(i).getConnectedAirports().size();
    			indexLargestHub = i;
    		}
    	}
		return airports.get(indexLargestHub);
    }
    
    public static Flight getLongestFlight() {
    	int longestDuration = flights.get(0).getDurationInMinutes();
    	int indexlongestDuration = 0;
    	
    	for (int i = 1; i < flights.size(); i++) {
    		if (flights.get(i).getDurationInMinutes() > longestDuration) {
    			longestDuration = flights.get(i).getDurationInMinutes();
    			indexlongestDuration = i;
    		}
    	}
		return flights.get(indexlongestDuration);
    }
    //Eπιστρέφει το αεροδρόμιο (Airport) που υπάρχει στην πόλη cityName 
    public static Airport getAirport(String cityName) {
    	for(Airport a : airports) {
    		if(a.getCity().equalsIgnoreCase(cityName)) {
    	    	return a;
    		}
    	}
    	return null;
    }
    //Eπιστρέφει τις πληροφορίες για κάθε πτήση που συνδέει απευθείας τα αεροδρόμια a και b
    public static String getDirectFlightsDetails(Airport a, Airport b) {
    	String string = "";
    	int count = 0;	    
    	for (Flight f : flights) {
	    	if ((f.getAirportA() == a && f.getAirportB() == b) || (f.getAirportA() == b && f.getAirportB() == a)) {
		    		count++;
		        	string += "["+count+"] "+f.toString()+"\n";
	    	}	    
    	}
    	return string;
    }
    //Eπιστρέφει τις πληροφορίες για κάθε πτήση που συνδέει τα αεροδρόμια a και b μέσω ενός ενδιάμεσου αεροδρομίου
    public static String getInDirectFlightsDetails(Airport a, Airport b) {
    	int counter = 1;
    	String string = "";
    	
		for (Airport intermedAirport : a.getConnectedAirports()) {
			if (intermedAirport.isDirectlyConnectedTo(a) && intermedAirport.isDirectlyConnectedTo(b)) {
				string += "["+counter+"]"+intermedAirport.getCity()+", "+intermedAirport.getCode()+" airport\n";
				counter++;
			}
		}
		return string; 
    }
		
}