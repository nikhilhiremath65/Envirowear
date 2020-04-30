package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class DisplayButton extends JButton{
	private String part;
	public DisplayButton(String part) {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setPreferredSize(new Dimension(20,20));
		this.setMaximumSize(new Dimension(100,100));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.part = part;
	}
	
	protected void paintComponent(Graphics g) {
		if(part == "UPPER")
			g.setColor(new Color(114, 147, 203, 180));
		else
			g.setColor(new Color(62, 150, 81, 180));
   
        g.fillOval(2, 2, getSize().width-4, getSize().height-4);
        super.paintComponent(g);
    }
	
	protected void paintBorder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width, getSize().height);
	}
}
