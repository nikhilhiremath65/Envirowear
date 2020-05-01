package gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.Data;


/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class TempChangeObserver {
	
	private static TempChangeObserver dataObj;
	ArrayList<JLabel> observers;
	ArrayList<ActuatorButton> actuatorList;
	ArrayList<JLabel> actuatorListText;
	GraphPanel graph;
	statusButton status;
	JLabel error;
	
	private TempChangeObserver() {
		observers = new ArrayList();
		actuatorList = new ArrayList();
		actuatorListText = new ArrayList();
	}
	
	public static TempChangeObserver getInstance() {
        if (dataObj == null) {
            dataObj = new TempChangeObserver();
        }
        return dataObj;
    }
	
	public void addObserver(JLabel obj) {
		observers.add(obj);
	}
	
	public void notifyObserver(int index, String value) {
		if(value.length() > 5) {
			observers.get(index).setText(value.substring(0,5));
		}
		else {
			observers.get(index).setText(value);
		}
	}
	
	public void addGraph(GraphPanel graph) {
		this.graph = graph;
	}
	
	public void addStatusObserver(statusButton s) {
		this.status = s;
	}
	
	public void changeStatus(int flag) {
		Data data = Data.getInstance();
		if(flag == 3 && data.isInitialize()) {
			data.setInitialize(false);
			status.flag = flag;
			status.repaint();
			String message = "Sensor failure!!! System Shutdown.";
			JOptionPane.showMessageDialog(status.getParent().getParent().getParent(), message);
		}
		else{
			status.flag = flag;
			status.repaint();
		}
	}
	
	public void addErrorLabel(JLabel e) {
		this.error = e;
	}
	
	public void changeErrorLabel(String error) {
		this.error.setText(error);;
	}
	
	
	
	public void notifyGraphs(ArrayList<Double> values,boolean part) {
		if(part) {
			this.graph.setUpperTempList(values);
		}
		else {
			this.graph.setLowerTempList(values);
		}
		this.graph.repaint();
	}

	public void addActuatorObserver(ActuatorButton button) {
		actuatorList.add(button);
	}
	
	public void addActuatorObserverText(JLabel label) {
		actuatorListText.add(label);
	}
	
	public void changeActuatorStatus(String statusFlag, int index) {
		ActuatorButton actuator = actuatorList.get(index);
		actuator.setFlag(statusFlag);
		actuatorListText.get(index).setText(statusFlag);
		actuator.repaint();
	
	}
}
