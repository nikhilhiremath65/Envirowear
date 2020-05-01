package gui;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import data.Constants;
import data.Data;

/**
 * 
 * @author somesh
 * @since 04-08-2020
 */

public class StartButton extends JButton{
	
	private double upperLimit = 0;
	private double lowerLimit = 0;
	private double envULimit = 0;
	private double envLLimit = 0;
	
	public StartButton(JTextField upperBody, JTextField lowerBody, ArrayList<ArrayList<JTextField>> simulationDetails){
		
		super("Start");
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setMaximumSize(new Dimension(75,75));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setOpaque(true);
		this.setBackground(Color.GREEN);
		
		this.upperLimit = Constants.UPPER_TEMP_LIMIT;
		this.lowerLimit = Constants.LOWER_TEMP_LIMIT;
		this.envULimit = Constants.ENV_UPPER_TEMP_LIMIT;
		this.envLLimit = Constants.ENV_LOWER_TEMP_LIMIT;
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Data data = Data.getInstance();
					Double upperTemp = Double.parseDouble(upperBody.getText().replace(" ", ""));
					Double lowerTemp = Double.parseDouble(lowerBody.getText().replace(" ", ""));
					double envTemp = Double.parseDouble(simulationDetails.get(4).get(0).getText());
					
					if (upperTemp < lowerLimit || upperTemp > upperLimit ||
							lowerTemp < lowerLimit || lowerTemp > upperLimit) {
						String message = "Please enter temperature between" + lowerLimit + " and " + upperLimit;
						JOptionPane.showMessageDialog(getParent().getParent(), message);
					}
					else if(envTemp == -999) {
						String message = "Envvironment Temperature sensor failure, shuting down system!!!";
						JOptionPane.showMessageDialog(getParent().getParent(), message);
					}
					else if(envTemp < envLLimit || envTemp > envULimit) {
						String message = "Please enter the environment temperature between" + 
										envLLimit + " and " + envULimit;
						JOptionPane.showMessageDialog(getParent().getParent(), message);
					}
					else {
						
						data.setUpperStatus(true);
						data.setLowerStatus(true);
						data.setGraphStatus(true);
						data.setUserSetUpper(upperTemp);
						data.setUserSetLower(lowerTemp);
						data.setStatusFlag(0);
						data.setInitialize(true);

						ArrayList<Double> us1List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(0)) {
							double temp = Double.parseDouble(i.getText());
							us1List.add(temp);
						}

						ArrayList<Double> us2List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(1)) {
							double temp = Double.parseDouble(i.getText());
							us2List.add(temp);
						}

						ArrayList<Double> ls1List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(2)) {
							double temp = Double.parseDouble(i.getText());
							ls1List.add(temp);
						}

						ArrayList<Double> ls2List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(3)) {
							double temp = Double.parseDouble(i.getText());
							ls2List.add(temp);
						}

						data.setUpperSensor1(us1List);
						data.setUpperSensor2(us2List);
						data.setLowerSensor1(ls1List);
						data.setLowerSensor2(ls2List);
						data.setEnvTemp(envTemp);
						data.settBR(Integer.parseInt(simulationDetails.get(5).get(0).getText()));
						
						simulationDetails.get(4).get(0).addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								double envTemp = Double.parseDouble(simulationDetails.get(4).get(0).getText());
								if(envTemp == -999) {
									data.setInitialize(false);
									String message = "Environment Temperature sensor failure, shuting down system!!!";
									JOptionPane.showMessageDialog(getParent().getParent(), message);
								}
								else if(envTemp < envLLimit || envTemp > envULimit) {
									String message = "Please enter the environment temperature between" + 
													envLLimit + " and " + envULimit;
									JOptionPane.showMessageDialog(getParent().getParent(), message);
								}
								else {
									data.setEnvTemp(envTemp);
								}
							}
						});
						
						simulationDetails.get(5).get(0).addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								data.settBR(Integer.parseInt(simulationDetails.get(5).get(0).getText()));
							}
						});
						
					}
				}catch(NumberFormatException excep) {
					String message = "Please enter temperature in number format, ex: 25 or 23.7";
					JOptionPane.showMessageDialog(getParent().getParent(), message);
				}
			}
		});
		
		
	}
}
