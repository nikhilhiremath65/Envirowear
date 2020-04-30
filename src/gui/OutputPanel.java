package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import data.Data;
/**
 * 
 * @author somesh
 * @since 04-08-2020
 */

public class OutputPanel extends JPanel {
		
	public OutputPanel() {
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Data observable = Data.getInstance();
		
		JLabel upperTempLabel = new LabelProperties("Upper Body Jacket Temperature : ");
		JLabel lowerTempLabel = new LabelProperties("Lower Body Jacket Temperature : ");
		JLabel statusTempLabel = new LabelProperties("status : ");
		statusTempLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		JLabel upperBodyStatusLabel = new LabelProperties("Actuator : ");
		JLabel lowerBodyStatusLabel = new LabelProperties("Actuator : ");
		
		JLabel degF1 = new JLabel("degF");
		degF1.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel degF2 = new JLabel("degF");
		degF2.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel degF3 = new JLabel("degF");
		degF2.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel upperTempOutput = new JLabel("00");
		upperTempOutput.setFont(new Font("Verdana", Font.PLAIN, 30));
		TempChangeObserver.getInstance().addObserver(upperTempOutput);
		
		JPanel upperBodyDisplay = createPanelTemp(upperTempLabel, upperTempOutput,degF1);
		
		JLabel lowerTempOutput = new JLabel("00");
		lowerTempOutput.setFont(new Font("Verdana", Font.PLAIN, 30));
		TempChangeObserver.getInstance().addObserver(lowerTempOutput);
		
		JPanel lowerBodyDisplay = createPanelTemp(lowerTempLabel, lowerTempOutput,degF2);
		
		
		statusButton status = new statusButton();
		TempChangeObserver.getInstance().addStatusObserver(status);
		
		JLabel error = new JLabel();
		TempChangeObserver.getInstance().addErrorLabel(error);
		
		JPanel statusPanel = createPanelTemp(statusTempLabel, status, error);
		statusPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		ActuatorButton upperBodyStatus = new ActuatorButton();
		TempChangeObserver.getInstance().addActuatorObserver(upperBodyStatus);
		TempChangeObserver.getInstance().addActuatorObserverText(upperBodyStatusLabel);
		JPanel upperBodyStatusPanel = createPanelTemp(upperBodyStatusLabel, upperBodyStatus, new JLabel());
		upperBodyStatusPanel.setMaximumSize(new Dimension(200,30));
		
		ActuatorButton lowerBodyStatus = new ActuatorButton();
		TempChangeObserver.getInstance().addActuatorObserver(lowerBodyStatus);
		TempChangeObserver.getInstance().addActuatorObserverText(lowerBodyStatusLabel);
		JPanel lowerBodyStatusPanel = createPanelTemp(lowerBodyStatusLabel, lowerBodyStatus, new JLabel());
		lowerBodyStatusPanel.setMaximumSize(new Dimension(200,30));
		
		GraphPanel graphPanel = new GraphPanel();
		TempChangeObserver.getInstance().addGraph(graphPanel);
		
		JPanel upperLineDescription = createPanelTemp(new JLabel("Upper Jacket Temperature"), new DisplayButton("UPPER"), new JLabel());
		JPanel lowerLineDescription = createPanelTemp(new JLabel("Lower Jacket Temperature"), new DisplayButton("Lower"), new JLabel());
		
		
		this.add(statusPanel);
		this.add(upperBodyDisplay);
		this.add(upperBodyStatusPanel);
		this.add(lowerBodyDisplay);
		this.add(lowerBodyStatusPanel);
		this.add(graphPanel);
		this.add(upperLineDescription);
		this.add(lowerLineDescription);
		
	}
	
	private JPanel createPanelTemp(JLabel label, JComponent value, JLabel unit) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setMaximumSize(new Dimension(400,50));
		panel.add(label);
		panel.add(value);
		panel.add(unit);
		return panel;
	}
}
