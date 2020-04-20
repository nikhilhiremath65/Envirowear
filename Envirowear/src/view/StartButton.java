package view;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Constants;
import model.Data;

/**
 * 
 * @author somesh
 * @since 04-08-2020
 */

public class StartButton extends JButton{
	
	public StartButton(JTextField upperBody, JTextField lowerBody, ArrayList<ArrayList<JTextField>> simulationDetails){
		
		super("Start");
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setMaximumSize(new Dimension(75,75));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Data data = Data.getInstance();
					Double upperTemp = Double.parseDouble(upperBody.getText().replace(" ", ""));
					Double lowerTemp = Double.parseDouble(lowerBody.getText().replace(" ", ""));
					
					if (upperTemp < Constants.LOWER_TEMP_LIMIT || upperTemp > Constants.UPPER_TEMP_LIMIT ||
							lowerTemp < Constants.LOWER_TEMP_LIMIT || lowerTemp > Constants.UPPER_TEMP_LIMIT) {
						String message = "Please enter temperature between 20 and 40";
						JOptionPane.showMessageDialog(getParent().getParent(), message);
					}
					else {
						if(!data.isUpperStatus() && !data.isLowerStatus()) {
							data.setUpperStatus(true);
							data.setLowerStatus(true);
							data.setGraphStatus(true);
						}
						data.setUserSetUpper(upperTemp);
						data.setUserSetLower(lowerTemp);
						
						ArrayList<Double> us1List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(0)) {
							
							us1List.add(Double.parseDouble(i.getText()));
						}
						
						ArrayList<Double> us2List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(1)) {
							us2List.add(Double.parseDouble(i.getText()));
						}
						
						ArrayList<Double> ls1List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(2)) {
							ls1List.add(Double.parseDouble(i.getText()));
						}
						
						ArrayList<Double> ls2List = new ArrayList<>();
						for(JTextField i : simulationDetails.get(3)) {
							ls2List.add(Double.parseDouble(i.getText()));
						}
						
						data.setUpperSensor1(us1List);
						data.setUpperSensor2(us2List);
						data.setLowerSensor1(ls1List);
						data.setLowerSensor2(ls2List);
						data.setEnvTemp(Double.parseDouble(simulationDetails.get(4).get(0).getText()));
						data.settBR(Double.parseDouble(simulationDetails.get(5).get(0).getText()));
						
						simulationDetails.get(4).get(0).addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								data.setEnvTemp(Float.parseFloat(simulationDetails.get(4).get(0).getText()));
							}
						});
						
						simulationDetails.get(5).get(0).addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								data.settBR(Float.parseFloat(simulationDetails.get(5).get(0).getText()));
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
	protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } 
        else {
            g.setColor(Color.decode("#4DBF26"));
        }
        g.fillRect(0, 0, getSize().width, getSize().height);
        super.paintComponent(g);
    }
}
