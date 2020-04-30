package gui;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class LabelProperties extends JLabel{
	public LabelProperties(String string) {
		super(string);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setMaximumSize(new Dimension(400, 50));
	}
}
