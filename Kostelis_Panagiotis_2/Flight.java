public class Flight {
	
    private Airport departureCity;
    private Airport destinationCity;
    private int durationInMinutes;
    private String airline;

    public Flight(Airport departureCity, Airport destinationCity, int durationInMinutes, String airline) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.durationInMinutes = durationInMinutes;
        this.airline = airline;
    }
    
    public int getDurationInMinutes() {
		return durationInMinutes;
	}

    public Airport getAirportA() {
    	return departureCity;
    }
    
    public Airport getAirportB() {
    	return destinationCity;
    }
      
    public String getAirline() {
    	return airline;
    }
}
