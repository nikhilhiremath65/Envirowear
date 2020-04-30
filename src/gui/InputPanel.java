package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * 
 * @author somesh
 * @since 04-08-2020
 */

public class InputPanel extends JPanel{

	public InputPanel(){
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new LabelProperties("    User Inputs");	
		JLabel upperTempLabel = new LabelProperties("    Input Upper Body Jacket Temperature : ");
		JLabel lowerTempLabel = new LabelProperties("    Input Lower Body Jacket Temperature : ");
		
		JPanel upperTempInPanel = new JPanel();
		upperTempInPanel.setLayout(new FlowLayout());
		upperTempInPanel.setMaximumSize(new Dimension(200,50));
		JTextField upperTempInput = new TextBoxProperties("68");
		JLabel degF1 = new JLabel("degF");
		upperTempInPanel.add(upperTempInput);
		upperTempInPanel.add(degF1);
		
		JPanel lowerTempInPanel = new JPanel();
		lowerTempInPanel.setLayout(new FlowLayout());
		lowerTempInPanel.setMaximumSize(new Dimension(200,50));
		JTextField lowerTempInput = new TextBoxProperties("92");
		JLabel degF2 = new JLabel("degF");
		lowerTempInPanel.add(lowerTempInput);
		lowerTempInPanel.add(degF2);
		
		OtherDetails otherDetails = new OtherDetails();
		
		StartButton startButton = new StartButton(upperTempInput, lowerTempInput, otherDetails.getSimulationValues());
		StopButton stopButton = new StopButton();
		
		//Adding buttons in JPanel to make sure they are in same line.
		JPanel buttons = new JPanel();
		buttons.setMaximumSize(new Dimension(200, 40));
		GridLayout buttonsLayout = new GridLayout(1,2);
		buttonsLayout.setHgap(20);
		buttons.setLayout(buttonsLayout);
		
		buttons.add(startButton);
		buttons.add(stopButton);
		
		this.add(title);
		this.add(upperTempLabel);
		this.add(upperTempInPanel);
		this.add(lowerTempLabel);
		this.add(lowerTempInPanel);
		this.add(Box.createVerticalStrut(20)); /*creating space between buttons and Enter lower body 
											   temperature input text box.*/
		this.add(buttons);
		this.add(Box.createVerticalStrut(20)); /*creating space between buttons and other details button*/
		this.add(otherDetails);
		
		this.setVisible(true);
	}
}
