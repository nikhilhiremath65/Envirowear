package view;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OtherDetailsButton extends JButton{
	
	public OtherDetailsButton(JPanel otherDetails) {
		
		super("Sensor Data");
		this.setLayout(null);
		this.setMaximumSize(new Dimension(100,40));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					otherDetails.setVisible(!otherDetails.isVisible());
			}
		});
	}
}
