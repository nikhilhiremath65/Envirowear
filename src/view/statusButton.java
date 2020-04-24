package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class statusButton extends JButton{
	public int flag = -1;
	
	public statusButton() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setPreferredSize(new Dimension(20,20));
		this.setMaximumSize(new Dimension(100,100));
		this.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	protected void paintComponent(Graphics g) {
		if(flag == -1) {
			g.setColor(Color.lightGray);
		}
		else if (flag == 0) {
            g.setColor(Color.decode("#4DBF26"));
        } 
        else if (flag == 1) {
            g.setColor(Color.YELLOW);
        }
        else if (flag == 2) {
            g.setColor(Color.YELLOW);
        } 
        else {
            g.setColor(Color.RED);
        }
        g.fillOval(2, 2, getSize().width-4, getSize().height-4);
        super.paintComponent(g);
    }
	
	protected void paintBorder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width, getSize().height);
	}
}
