package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import data.Data;

/**
 * 
 * @author somesh
 * @since 04-08-2020
 */
public class StopButton extends JButton{
	
	public StopButton(){
		
		super("Stop");
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setMaximumSize(new Dimension(75,75));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setOpaque(true);
		this.setBackground(Color.RED);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Data.getInstance().setInitialize(false);
				Data.getInstance().setUpperStatus(false);
				Data.getInstance().setLowerStatus(false);
				Data.getInstance().setGraphStatus(false);
				Data.getInstance().reset();
				
				TempChangeObserver.getInstance().changeStatus(-1);
				TempChangeObserver.getInstance().changeActuatorStatus("NONE", 0);
				TempChangeObserver.getInstance().changeActuatorStatus("NONE", 1);
				
			}
		});
	}
}
