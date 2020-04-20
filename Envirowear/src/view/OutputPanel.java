package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Data;
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
		JLabel upperTempLabel = new LabelProperties("    Upper Body Jacket Temperature : ");
		JLabel lowerTempLabel = new LabelProperties("    Lower Body Jacket Temperature : ");
		JLabel statusTempLabel = new LabelProperties("status : ");
		statusTempLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel degF1 = new JLabel("degF");
		degF1.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel degF2 = new JLabel("degF");
		degF2.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JPanel upperTempOutPanel = new JPanel();
		upperTempOutPanel.setLayout(new FlowLayout());
		upperTempOutPanel.setMaximumSize(new Dimension(200,50));
		
		JLabel upperTempOutput = new JLabel("00");
		upperTempOutput.setFont(new Font("Verdana", Font.PLAIN, 30));
		TempChangeObserver.getInstance().addObserver(upperTempOutput);
		
		upperTempOutPanel.add(upperTempOutput);
		upperTempOutPanel.add(degF1);
		
		JPanel lowerTempOutPanel = new JPanel();
		lowerTempOutPanel.setLayout(new FlowLayout());
		lowerTempOutPanel.setMaximumSize(new Dimension(200,50));
		
		JLabel lowerTempOutput = new JLabel("00");
		lowerTempOutput.setFont(new Font("Verdana", Font.PLAIN, 30));
		TempChangeObserver.getInstance().addObserver(lowerTempOutput);
		
		lowerTempOutPanel.add(lowerTempOutput);
		lowerTempOutPanel.add(degF2);
		
		statusButton status = new statusButton();
		TempChangeObserver.getInstance().addStatusObserver(status);
		
		JLabel error = new JLabel();
		TempChangeObserver.getInstance().addErrorLabel(error);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setMaximumSize(new Dimension(200,30));
		statusPanel.setLayout(new FlowLayout());
		statusPanel.add(statusTempLabel);
		statusPanel.add(status);
		
		GraphPanel graphPanel = new GraphPanel();
		TempChangeObserver.getInstance().addGraph(graphPanel);
		
		this.add(statusPanel);
		this.add(error);
		this.add(title);
		this.add(upperTempLabel);
		this.add(upperTempOutPanel);
		this.add(lowerTempLabel);
		this.add(lowerTempOutPanel);
		this.add(graphPanel);
	}
}
