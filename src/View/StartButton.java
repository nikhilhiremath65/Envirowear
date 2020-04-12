package View;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.Data;

/**
 * 
 * @author somesh
 * @since 04-08-2020
 */

public class StartButton extends JButton{
	
	public StartButton(JTextField upperBody, JTextField lowerBody){
		
		super("Start");
		this.setLayout(null);
		this.setBorder(new EmptyBorder(0, 0, 5, 5));
		this.setMaximumSize(new Dimension(75,75));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					float upperTemp = Float.parseFloat(upperBody.getText().replace(" ", ""));
					float lowerTemp = Float.parseFloat(lowerBody.getText().replace(" ", ""));
					
					if (upperTemp < 20 || upperTemp > 40 || lowerTemp < 20 || lowerTemp > 40) {
						String message = "Please enter temperature between 20 and 40";
						JOptionPane.showMessageDialog(getParent().getParent(), message);
					}
					else {
						Data data = Data.getInstance();
						if(!data.isUpperStatus() && !data.isLowerStatus()) {
							data.setUpperStatus(true);
							data.setLowerStatus(true);
							//new Start();
						}
						data.setUserSetUpper(upperTemp);
						data.setUserSetLower(lowerTemp);
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
