package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *  
 * @author somesh
 * @since 04-08-2020
 */

public class ActuatorButton extends JButton{
	private String flag = "NONE";

	public ActuatorButton() {

		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setPreferredSize(new Dimension(20,20));
		this.setMaximumSize(new Dimension(100,100));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setEnabled(false);
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        if (flag == "NONE") {
        	g.setColor(Color.LIGHT_GRAY);
        	
        } 
        else if (flag == "COOL") {
        	g.setColor(Color.BLUE);
        	
        }
        else if (flag == "HEAT") {
        	 g.setColor(Color.RED);
        	
        }
        g.fillOval(2, 2, getSize().width-4, getSize().height-4);
        
    }
	
	protected void paintBorder(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, getSize().width, getSize().height);
	}
}
