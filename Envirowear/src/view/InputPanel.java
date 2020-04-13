package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

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
		JLabel upperTempLabel = new LabelProperties("    Input Upper Body Temperature : ");
		JLabel lowerTempLabel = new LabelProperties("    Input Lower Body Temperature : ");
		
		JTextField upperTempInput = new TextBoxProperties("24");
		JTextField lowerTempInput = new TextBoxProperties("24");
		
		StartButton startButton = new StartButton(upperTempInput, lowerTempInput);
		StopButton stopButton = new StopButton();
		
		//Adding buttons in JPanel to make sure they are in same line.
		JPanel buttons = new JPanel();
		buttons.setMaximumSize(new Dimension(200, 40));
		GridLayout buttonsLayout = new GridLayout(1,2);
		buttonsLayout.setHgap(20);
		buttons.setLayout(buttonsLayout);
		
		buttons.add(startButton);
		buttons.add(stopButton);
		
		JPanel otherDetails = new OtherDetails();
		JButton otherDetailsButton = new OtherDetailsButton(otherDetails);
		
		this.add(title);
		this.add(upperTempLabel);
		this.add(upperTempInput);
		this.add(lowerTempLabel);
		this.add(lowerTempInput);
		this.add(Box.createVerticalStrut(20)); /*creating space between buttons and Enter lower body 
											   temperature input text box.*/
		this.add(buttons);
		this.add(Box.createVerticalStrut(20)); /*creating space between buttons and other details button*/
		this.add(otherDetailsButton);
		this.add(otherDetails);
		
		this.setVisible(true);
	}
}
