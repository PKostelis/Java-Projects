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
 
}