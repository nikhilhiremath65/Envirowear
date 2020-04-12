package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import Envirowear.Data;
import Envirowear.Start;

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
		new Start();
//		float i = 0;
//		while(true) {
//			Data.getInstance().setCurrUpperTemp(i);
//			Data.getInstance().setCurrLowerTemp(i+1);
//			Data.getInstance().setEnvirTemp(i+2);
//			Data.getInstance().setUpperSensor1(i);
//			Data.getInstance().setUpperSensor2(i+1);
//			Data.getInstance().setLowerSensor1(i+2);
//			Data.getInstance().setLowerSensor2(i+3);
//			TimeUnit.SECONDS.sleep(1);
//			System.out.println(i);
//			 i++;
//		}
		
	}
}
