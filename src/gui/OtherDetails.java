package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *  
 * @author somesh
 * @since 04-08-2020
 */

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
	    JPanel upperSensor1 = createPanelSensors("Sensor 1 : ", "71");
	    JPanel upperSensor2 = createPanelSensors("Sensor 2 : ", "71");

	    upperPanel.add(upperBodyTitle);
	    upperPanel.add(upperSensor1);
	    upperPanel.add(upperSensor2);
	    
	    JPanel lowerPanel = new JPanel();
	    lowerPanel.setLayout(new GridLayout(3,1));
	    JLabel lowerBodyTitle = new LabelProperties("Lower Body Jacket Sensors");  
	    JPanel lowerSensor1 = createPanelSensors("Sensor 1 : ", "82");
	    JPanel lowerSensor2 = createPanelSensors("Sensor 2 : ", "82");

	    lowerPanel.add(lowerBodyTitle);
	    lowerPanel.add(lowerSensor1);
	    lowerPanel.add(lowerSensor2);
	    
	    //JLabel othersLabel = new LabelProperties("Other Details");
	    JPanel EnvTemp = createPanelOthers("Environment Temp : ", "78", "degF");
	    JPanel timeBR = createPanelOthers("Time Between Rounds : ", "20","ms");
	    
	    JPanel timeElapsedPanel = new JPanel();
	    timeElapsedPanel.setLayout(new FlowLayout());
	    JLabel timeElapsed = new LabelProperties("Time Elapsed");
	    JLabel timeElapsedValue = new LabelProperties("00");
	    timeElapsedValue.setFont(new Font("Verdana", Font.PLAIN, 20));
	    TempChangeObserver.getInstance().addObserver(timeElapsedValue);
	    JLabel timeElapsedUnit = new LabelProperties("s");
	    
	    timeElapsedPanel.add(timeElapsed);
	    timeElapsedPanel.add(timeElapsedValue);
	    timeElapsedPanel.add(timeElapsedUnit);
	    
	    this.add(title);
	    this.add(upperPanel);
	    this.add(lowerPanel);
	    //this.add(othersLabel);
	    this.add(EnvTemp);
	    this.add(timeBR);
	    this.add(timeElapsedPanel);
	    
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
	
	private JPanel createPanelOthers(String name, String value, String unit) {
		
	    JPanel other = new JPanel();
	    other.setLayout(new FlowLayout());
	    
	    ArrayList<JTextField> values = new ArrayList<JTextField>(); 
	    
	    JLabel sensorLabel = new JLabel(name);
	    
	    JTextField sensorValue = new JTextField(value);
	    sensorValue.setPreferredSize(new Dimension(75,30));
	    values.add(sensorValue);
	    
	    this.simulationValues.add(values);
	    JLabel degF = new JLabel(unit);
	    
	    other.add(sensorLabel);
	    other.add(sensorValue);
	    other.add(degF);
	    
	    return other;
	}
}
