import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AirportPageFrame extends JFrame implements ActionListener {
	
	private JFrame frame = new JFrame();

	Airport airportFound;
	
	public void setReceivedObject(Airport airportFound) {
		this.airportFound = airportFound;
		textFieldName.setText("" +airportFound.getName());
		textFieldCode.setText("" +airportFound.getCode());
		textFieldCity.setText("" +airportFound.getCity());
		textFieldCountry.setText("" +airportFound.getCountry());
		textAreaAirlines.setText(""+ airportFound.getServedAirlinesCompanies());

		//System.out.println("string:\n"+airportFound.getServedAirlinesCompanies());
	}
		//JPanel panel = new JPanel(new FlowLayout());
	private JPanel panel = new JPanel(new GridLayout(2, 1)); // 2 γραμμές, 1 στήλη
	private JPanel upperPanel = new JPanel(new GridLayout(1, 5)); // 1 γραμμή, 4 στήλες
	private JPanel middlePanel = new JPanel(new FlowLayout()); // Πάνελ για το κεντράρισμα
	private JPanel lowerPanel = new JPanel(new FlowLayout()); // Πάνελ για το κεντράρισμα
	private JPanel bottomPanel = new JPanel(new FlowLayout());
	
	private JButton findFlightsButton = new JButton("Find Flights");
	private JButton backToSearchScreenButton = new JButton("Back to Search Screen");
	
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldCode = new JTextField();
	private JTextField textFieldCity = new JTextField();
	private JTextField textFieldCountry = new JTextField();
	
	private JTextArea textAreaAirlines = new JTextArea();
	
	private JTextField textFieldDestinationCity = new JTextField();
	
	private JTextArea textAreaDirectFlightsDetails = new JTextArea();
	private JTextArea textAreaInDirectFlightsDetails = new JTextArea();
	
	AirportPageFrame() {
		
		frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setTitle("Airport Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		frame.add(panel);
		frame.add(upperPanel);
		frame.add(middlePanel);
		frame.add(lowerPanel);
		frame.add(bottomPanel);

		findFlightsButton.addActionListener(this);
		backToSearchScreenButton.addActionListener(this);
		
		textFieldName.setPreferredSize(new Dimension(150, 20));
		textFieldCode.setPreferredSize(new Dimension(150, 20));
		textFieldCity.setPreferredSize(new Dimension(150, 20));
		textFieldCountry.setPreferredSize(new Dimension(150, 20));
		textAreaAirlines.setPreferredSize(new Dimension(50, 50));
		textFieldDestinationCity.setPreferredSize(new Dimension(200, 20));
		textAreaDirectFlightsDetails.setPreferredSize(new Dimension(450, 300));
		textAreaInDirectFlightsDetails.setPreferredSize(new Dimension(450, 300));
		
		upperPanel.add(textFieldName);
		upperPanel.add(textFieldCode);
		upperPanel.add(textFieldCity);
		upperPanel.add(textFieldCountry);
		upperPanel.add(textAreaAirlines);
		
		middlePanel.add(textFieldDestinationCity);
		middlePanel.add(findFlightsButton, BorderLayout.LINE_END);
		
		lowerPanel.add(textAreaDirectFlightsDetails);
		lowerPanel.add(textAreaInDirectFlightsDetails);
		
		bottomPanel.add(backToSearchScreenButton);
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == findFlightsButton) {

			String destCity = textFieldDestinationCity.getText();
			Airport destAirport = CentralRegistry.getAirport(destCity);
			
			if(airportFound != destAirport) {
				String directDetails = CentralRegistry.getDirectFlightsDetails(airportFound, destAirport);
				textAreaDirectFlightsDetails.setText("DIRECT FLIGHTS DETAILS\n"+directDetails);
				
				
				String indirectDetails = CentralRegistry.getInDirectFlightsDetails(airportFound, destAirport);
				textAreaInDirectFlightsDetails.setText("INDIRECT FLIGHTS through...\n"+indirectDetails);
			}
		
			else {
				JOptionPane.showMessageDialog(null, "Arrival and departure cannot be the same!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == backToSearchScreenButton) {
			FindAiportFrame findAiportFrame = new FindAiportFrame();
			frame.dispose();
		}
	}
	
}
