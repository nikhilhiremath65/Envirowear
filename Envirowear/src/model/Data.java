package model;

import java.util.ArrayList;

import view.TempChangeObserver;

public class Data {
    private static Data dataObj;
    

    private double envTemp;
    private double userSetUTemp;
    private double userSetLTemp;
    private double upperBodyTemp;
    private double lowerBodyTemp;

    private double upperBodySensor1;
    private double upperBodySensor2;
    private double lowerBodySensor1;
    private double lowerBodySensor2;
    
    private boolean graphStatus;
	private boolean upperControllerStatus;
    private boolean lowerControllerStatus;
    
    private ArrayList<Double> upperBodyTempList;
    private ArrayList<Double> lowerBodyTempList;

    private Data() {

    	this.envTemp = 25;

    	
    	this.graphStatus = false;
        this.upperControllerStatus = false;
        this.lowerControllerStatus = false;
        
        this.upperBodyTempList = new ArrayList<Double>();
        this.lowerBodyTempList = new ArrayList<Double>();    
    }

    public static Data getInstance() {
        if (dataObj == null) {
            dataObj = new Data();
        }
        return dataObj;
    }
    
	public void setUpperSensor1(double upperSensor1) {
		this.upperBodySensor1 = upperSensor1;
		String value = Double.toString(upperSensor1);
        TempChangeObserver.getInstance().notifyObserver(0, value);
	}

	public double getUpperSensor2() {
		return upperBodySensor2;
	}

	public void setUpperSensor2(double upperSensor2) {
		this.upperBodySensor2 = upperSensor2;
		String value = Double.toString(upperSensor2);
        TempChangeObserver.getInstance().notifyObserver(1, value);
	}

	public double getLowerSensor1() {
		return lowerBodySensor1;
	}

	public void setLowerSensor1(double lowerSensor1) {
		this.lowerBodySensor1 = lowerSensor1;
		String value = Double.toString(lowerSensor1);
        TempChangeObserver.getInstance().notifyObserver(2, value);
	}

	public double getLowerSensor2() {
		return lowerBodySensor2;
	}

	public void setLowerSensor2(double lowerSensor2) {
		this.lowerBodySensor2 = lowerSensor2;
		String value = Double.toString(lowerBodySensor1);
        TempChangeObserver.getInstance().notifyObserver(3, value);
	}
	
    public void setCurrUpperTemp(double currUpperTemp) {
    	if(this.graphStatus) {
    		this.upperBodyTemp = currUpperTemp;
        	String value = Double.toString(currUpperTemp);
            TempChangeObserver.getInstance().notifyObserver(4, value);
            
            this.upperBodyTempList.add((double) currUpperTemp);
            TempChangeObserver.getInstance().notifyGraphs(this.upperBodyTempList, true);
    	}
    }

    public double getCurrLowerTemp() {
        return lowerBodyTemp;
    }

    public void setCurrLowerTemp(double currLowerTemp) {
    	if(this.graphStatus) {
    		this.lowerBodyTemp = currLowerTemp;
            String value = Double.toString(currLowerTemp);
            TempChangeObserver.getInstance().notifyObserver(5, value);
            
            this.lowerBodyTempList.add((double) currLowerTemp);
            TempChangeObserver.getInstance().notifyGraphs(this.lowerBodyTempList, false);
    	}
    }

    public double getEnvirTemp() {
        return envTemp;
    }

    public void setEnvirTemp(Double envirTemp) {
        envTemp = envirTemp;
        String value = Double.toString(envirTemp);
        TempChangeObserver.getInstance().notifyObserver(6, value);
    }

    public double getUserSetUpper() {
        return userSetUTemp;

    }

    public void setUserSetUpper(Double userSetUpper) {
        this.userSetUTemp = userSetUpper;
    }

    public double getUserSetLower() {
        return userSetLTemp;
    }

    public void setUserSetLower(Double userSetLower) {
        this.userSetLTemp = userSetLower;
    }


    public double getCurrUpperTemp() {
        return upperBodyTemp;
    }   
    
    public boolean isUpperStatus() {
		return upperControllerStatus;
	}

	public void setUpperStatus(boolean upperStatus) {
		this.upperControllerStatus = upperStatus;
	}

	public boolean isLowerStatus() {
		return lowerControllerStatus;
	}

	public void setLowerStatus(boolean lowerStatus) {
		this.lowerControllerStatus = lowerStatus;
	}

	public double getUpperSensor1() {
		return upperBodySensor1;
	}
	
	public boolean isGraphStatus() {
		return graphStatus;
	}

	public void setGraphStatus(boolean graphStatus) {
		this.graphStatus = graphStatus;
	}
}