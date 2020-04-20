package view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Data;


/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class TempChangeObserver {
	
	private static TempChangeObserver dataObj;
	ArrayList<JLabel> observers;
	GraphPanel graph;
	statusButton status;
	JLabel error;
	
	private TempChangeObserver() {
		observers = new ArrayList();
		//graph = new GraphPanel();
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
		observers.get(index).setText(value.substring(0,5));
	}
	
	public void addGraph(GraphPanel graph) {
		this.graph = graph;
	}
	
	public void addStatusObserver(statusButton s) {
		this.status = s;
	}
	
	public void changeStatus(boolean flag) {
		status.flag = flag;
		status.repaint();
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
}
