import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FindAiportFrame extends JFrame implements ActionListener {
	
	JButton findButton;
	JTextField textField;
	
	private ArrayList<Airport> airports = new ArrayList<>();
  
	FindAiportFrame() {	

		findButton = new JButton();

		findButton.addActionListener(this);
		findButton.setText("Find");

		findButton.addActionListener(e -> dispose());
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		
		this.pack();
		this.add(textField);
		this.setVisible(true);
		this.setSize(300, 150);
		this.setTitle("Find Airport");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(findButton);
		this.setLayout(new FlowLayout());
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cityName = textField.getText();
	    
		Airport airportFound = CentralRegistry.getAirport(cityName);
		if (airportFound != null) {	
			AirportPageFrame airportPageFrame = new AirportPageFrame();
		
			airportPageFrame.setReceivedObject(airportFound);

        }
		else
			JOptionPane.showMessageDialog(null, ""+cityName+" does not have an airport", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

}
