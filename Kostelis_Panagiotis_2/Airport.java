import java.util.ArrayList;

public class Airport {
    private String name;
    private String code;
    private String city;
    private String country;
    private ArrayList<String> servedAirlinesCompanies;
    private ArrayList<Airport> connectedAirports;

    public Airport(String name, String code, String city, String country) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.country = country;
        this.servedAirlinesCompanies = new ArrayList<>();
        this.connectedAirports = new ArrayList<>();
    }
    
    // Δημιουργία getter
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public ArrayList<Airport> getConnectedAirports() {
		return connectedAirports;
	}

	// Έλεγχος αν είναι απευθείας συνδεδεμένα
    public boolean isDirectlyConnectedTo(Airport anAirport) {
        return connectedAirports.contains(anAirport);
    }

    // Έλεγχος αν είναι συνδεδεμένα μέσω ενός ενδιάμεσου αεροδρομίου
    public boolean isInDirectlyConnectedTo(Airport anAirport) {
        for (Airport c : connectedAirports) {
            if (!c.isDirectlyConnectedTo(anAirport)) {
                return true;
            }
        }
        return false;
    }

    // Επιστροφή κοινών συνδέσεων
    public ArrayList<Airport> getCommonConnections(Airport anAirport) {
        ArrayList<Airport> commonConnections = new ArrayList<>();
        
        for (Airport c : connectedAirports) {
            if (c.isDirectlyConnectedTo(anAirport)) {
                commonConnections.add(c);
            }
        }
        return commonConnections;
    }

    // Εκτύπωση των ονομάτων των εταιρειών που εξυπηρετεί το αεροδρόμιο
    public void printCompanies() {
    	for (int i = 0; i < servedAirlinesCompanies.size(); i++) {
    		System.out.println(servedAirlinesCompanies.get(i));
    	}
    }
    
    // Προσθήκη συνδεδεμένου αεροδρομίου
    public void addConnectedAirport(Airport anAirport) {
    	connectedAirports.add(anAirport);
    }
    
    //Προσθήκη αεροπορικής εταιρείας που εξυπηρετεί το αεροδρόμιο
    public void addAirlineCompany(String anAirlineCompany) {
    	servedAirlinesCompanies.add(anAirlineCompany);
    }
}