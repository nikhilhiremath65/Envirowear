package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import Backend.Start;

/**
 *  
 * @author somesh
 * @since 04-08-2020
 */

public class Frame extends JFrame{
	public Frame(){
		
		Dimension screenSize = new Dimension(800,600);
		
		this.setTitle("Enviro Wear");
		this.setMinimumSize(screenSize);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setLayout(new GridLayout(1,2));
	    
	    InputPanel inputPanel = new InputPanel();
	    OutputPanel outputPanel = new OutputPanel();
	    

	    this.add(inputPanel);
	    this.add(outputPanel);
	    this.setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException{
		new Frame();
		new Start().runApp();
		
	}
}
