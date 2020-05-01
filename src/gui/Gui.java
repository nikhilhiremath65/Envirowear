package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 *  
 * @author somesh
 * @since 04-08-2020
 */

public class Gui extends JFrame{
	public Gui(){
		
		Dimension screenSize = new Dimension(800,800);
		
		this.setTitle("Envirowear");
		this.setMinimumSize(screenSize);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setLayout(new GridLayout(1,2));
	    
	    InputPanel inputPanel = new InputPanel();
	    OutputPanel outputPanel = new OutputPanel();
	    

	    this.add(inputPanel);
	    this.add(outputPanel);
	    this.setVisible(true);
	}
}
