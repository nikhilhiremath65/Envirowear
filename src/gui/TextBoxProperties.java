package gui;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class TextBoxProperties extends JTextField implements Observer{
	
	private String value;
	public TextBoxProperties(String value){
		
		super(value);
		this.value = value;
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setMaximumSize(new Dimension(100, 30));
		this.setPreferredSize(new Dimension(75,30));
	}

	@Override
	public void update(Observable o, Object value) {

		this.value = (String) value;
		this.setText(this.value);
	}
}
