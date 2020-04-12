package Envirowear;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class TempChangeObserver {
	
	private static TempChangeObserver dataObj;
	ArrayList<JTextField> observers;
	
	private TempChangeObserver() {
		observers = new ArrayList();
	}
	
	public static TempChangeObserver getInstance() {
        if (dataObj == null) {
            dataObj = new TempChangeObserver();
        }
        return dataObj;
    }
	
	public void addObserver(JTextField obj) {
		observers.add(obj);
	}
	
	public void notifyObserver(int index, String value) {
		observers.get(index).setText(value);
	}
}
