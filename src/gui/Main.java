package gui;

import components.Envirowear;

/**
 * This is the driver class that starts the complete application.
 *  
 * @author somesh
 * 
 */

public class Main {
	public static void main(String[] args) {
		new Gui();
		Envirowear controller = new Envirowear();
		controller.start();
	}
}
