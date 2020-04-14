package view;

import java.util.ArrayList;
import javax.swing.JTextField;

import model.Data;


/**
 * 
 * @author somesh
 * @since 04-10-2020
 */

public class TempChangeObserver {
	
	private static TempChangeObserver dataObj;
	ArrayList<JTextField> observers;
	GraphPanel graph;
	
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
	
	public void addObserver(JTextField obj) {
		observers.add(obj);
	}
	
	public void notifyObserver(int index, String value) {
		observers.get(index).setText(value);
	}
	
	public void addGraph(GraphPanel graph) {
		this.graph = graph;
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
