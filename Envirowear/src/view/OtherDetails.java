package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OtherDetails extends JPanel{
	private ArrayList<ArrayList<JTextField>> simulationValues; 
	
	public ArrayList<ArrayList<JTextField>> getSimulationValues() {
		return simulationValues;
	}

	public OtherDetails() {
		
		this.setMaximumSize(new Dimension(400,600));
	    
	    this.setVisible(true);
	    this.simulationValues = new ArrayList();
	    
	    JLabel title = new JLabel("Simulation Inputs");
	    title.setFont(new Font("Verdana", Font.PLAIN, 30));
	    
	    JPanel upperPanel = new JPanel();
	    upperPanel.setLayout(new GridLayout(3,1));
	    JLabel upperBodyTitle = new LabelProperties("Upper Body Jacket Sensors");
	    JPanel upperSensor1 = createPanelSensors("Sensor 1 : ", "17");
	    JPanel upperSensor2 = createPanelSensors("Sensor 2 : ", "18");

	    upperPanel.add(upperBodyTitle);
	    upperPanel.add(upperSensor1);
	    upperPanel.add(upperSensor2);
	    
	    JPanel lowerPanel = new JPanel();
	    lowerPanel.setLayout(new GridLayout(3,1));
	    JLabel lowerBodyTitle = new LabelProperties("Lower Body Jacket Sensors");  
	    JPanel lowerSensor1 = createPanelSensors("Sensor 1 : ", "27");
	    JPanel lowerSensor2 = createPanelSensors("Sensor 2 : ", "28");

	    lowerPanel.add(lowerBodyTitle);
	    lowerPanel.add(lowerSensor1);
	    lowerPanel.add(lowerSensor2);
	    
	    JPanel others =  new JPanel();
	    others.setLayout(new GridLayout(3,1));
	    JLabel othersLabel = new LabelProperties("Other Details");
	    JPanel EnvTemp = createPanelOthers("Env Temp : ", "00");
	    JPanel timeBR = createPanelOthers("Time Between Rounds : ", "00");
	    
	    others.add(othersLabel);
	    others.add(EnvTemp);
	    others.add(timeBR);
	    
	    this.add(title);
	    this.add(upperPanel);
	    this.add(lowerPanel);
	    this.add(others);
	    
	}
	
	private JPanel createPanelSensors(String name, String value) {
				
	    JPanel sensor = new JPanel();
	    sensor.setLayout(new FlowLayout());
	    ArrayList<JTextField> values = new ArrayList<JTextField>(); 
	    
	    JLabel sensorLabel = new LabelProperties(name);
	    
	    
	    JTextField sensorValue1 = new TextBoxProperties(value);
	    sensorValue1.setPreferredSize(new Dimension(75,30));
	    values.add(sensorValue1);
	    
	    JTextField sensorValue2 = new TextBoxProperties(value);
	    sensorValue2.setPreferredSize(new Dimension(75,30));
	    values.add(sensorValue2);
	    
	    JTextField sensorValue3 = new TextBoxProperties(value);
	    sensorValue3.setPreferredSize(new Dimension(75,30));
	    values.add(sensorValue3);
	    
	    JLabel degF = new JLabel("degF");
	    
	    this.simulationValues.add(values);
	    
	    sensor.add(sensorLabel);
	    sensor.add(sensorValue1);
	    sensor.add(sensorValue2);
	    sensor.add(sensorValue3);
	    sensor.add(degF);
	    
	    return sensor;
	}
	
	private JPanel createPanelOthers(String name, String value) {
		
	    JPanel other = new JPanel();
	    other.setLayout(new FlowLayout());
	    ArrayList<JTextField> values = new ArrayList<JTextField>(); 
	    
	    JLabel sensorLabel = new JLabel(name);
	    
	    JTextField sensorValue = new JTextField(value);
	    sensorValue.setPreferredSize(new Dimension(75,30));
	    values.add(sensorValue);
	    
	    this.simulationValues.add(values);
	    JLabel degF = new JLabel("degF");
	    
	    other.add(sensorLabel);
	    other.add(sensorValue);
	    other.add(degF);
	    
	    return other;
	}
}
