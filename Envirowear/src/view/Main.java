package view;

import controller.StartController;

/**
 * This is the driver class that starts the complete application.
 *  
 * @author somesh
 * 
 */

public class Main {
	public static void main(String[] args) {
		new Gui();
		StartController controller = new StartController();
		controller.start();
	}
}
