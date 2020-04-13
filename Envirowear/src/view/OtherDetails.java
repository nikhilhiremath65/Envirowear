package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OtherDetails extends JPanel{
	
	public OtherDetails() {
		
		this.setMaximumSize(new Dimension(400,200));
	    this.setLayout(new GridLayout(2,1));
	    
	    this.setVisible(false);
	    
	    JPanel upperPanel = new JPanel();
	    upperPanel.setLayout(new GridLayout(3,1));
	    JLabel upperBodyTitle = new LabelProperties("    Upper Body");
	    JPanel upperSensor1 = createPanel("    Sensor 1 : ", "00");
	    JPanel upperSensor2 = createPanel("    Sensor 2 : ", "00");

	    upperPanel.add(upperBodyTitle);
	    upperPanel.add(upperSensor1);
	    upperPanel.add(upperSensor2);
	    
	    JPanel lowerPanel = new JPanel();
	    lowerPanel.setLayout(new GridLayout(3,1));
	    JLabel lowerBodyTitle = new LabelProperties("    Lower Body");  
	    JPanel lowerSensor1 = createPanel("    Sensor 1 : ", "00");
	    JPanel lowerSensor2 = createPanel("    Sensor 2 : ", "00");

	    lowerPanel.add(lowerBodyTitle);
	    lowerPanel.add(lowerSensor1);
	    lowerPanel.add(lowerSensor2);
	    
	    this.add(upperPanel);
	    this.add(lowerPanel);
	}
	
	private JPanel createPanel(String name, String value) {
				
	    JPanel sensor = new JPanel();
	    sensor.setLayout(new FlowLayout());
	    JLabel sensorLabel = new LabelProperties(name);
	    JTextField sensorValue = new TextBoxProperties(value);
	    sensorValue.setPreferredSize(new Dimension(75,30));
	    sensorValue.setEditable(false);
	    TempChangeObserver.getInstance().addObserver(sensorValue);
	    
	    sensor.add(sensorLabel);
	    sensor.add(sensorValue);
	    
	    return sensor;
	}
}
