package View;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Backend.Data;
import Backend.TempChangeObserver;
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
		
		JLabel title = new LabelProperties("    Output");
		JLabel upperTempLabel = new LabelProperties("    Upper Body Temperature : ");
		JLabel lowerTempLabel = new LabelProperties("    Lower Body Temperature : ");
		JLabel environmentTempLabel = new LabelProperties("    Environment Temperature : ");

		TextBoxProperties upperTempOutput = new TextBoxProperties("00");
		upperTempOutput.setEditable(false);
		TempChangeObserver.getInstance().addObserver(upperTempOutput);
		
		TextBoxProperties lowerTempOutput = new TextBoxProperties("00");
		lowerTempOutput.setEditable(false);
		TempChangeObserver.getInstance().addObserver(lowerTempOutput);
		
		TextBoxProperties environmentTempOutput = new TextBoxProperties("00");
		environmentTempOutput.setEditable(false);
		TempChangeObserver.getInstance().addObserver(environmentTempOutput);
		
		JPanel graphPanel = new GraphPanel();
		
		this.add(title);
		this.add(upperTempLabel);
		this.add(upperTempOutput);
		this.add(lowerTempLabel);
		this.add(lowerTempOutput);
		this.add(environmentTempLabel);
		this.add(environmentTempOutput);
		this.add(graphPanel);
	}
}
