package model;

import java.util.ArrayList;

import view.TempChangeObserver;

public class Data {
    private static Data dataObj;
    
    private int timeOut;
    
    private float envTemp;
    private float userSetUTemp;
    private float userSetLTemp;
    private float upperBodyTemp;
    private float lowerBodyTemp;
    private float lowerTempLimit;
    private float upperTempLimit;
    private float upperBodySensor1;
    private float upperBodySensor2;
    private float lowerBodySensor1;
    private float lowerBodySensor2;
    
    private boolean upperControllerStatus;
    private boolean lowerControllerStatus;
    
    private ArrayList<Double> upperBodyTempList;
    private ArrayList<Double> lowerBodyTempList;

    private Data() {
    	this.timeOut = 100;
    	
    	this.envTemp = 20;
    	this.lowerTempLimit = 16;
    	this.upperTempLimit = 36;
    	
    	
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
    
	public void setUpperSensor1(float upperSensor1) {
		this.upperBodySensor1 = upperSensor1;
		String value = Float.toString(upperSensor1);
        TempChangeObserver.getInstance().notifyObserver(0, value);
	}

	public float getUpperSensor2() {
		return upperBodySensor2;
	}

	public void setUpperSensor2(float upperSensor2) {
		this.upperBodySensor2 = upperSensor2;
		String value = Float.toString(upperSensor2);
        TempChangeObserver.getInstance().notifyObserver(1, value);
	}

	public float getLowerSensor1() {
		return lowerBodySensor1;
	}

	public void setLowerSensor1(float lowerSensor1) {
		this.lowerBodySensor1 = lowerSensor1;
		String value = Float.toString(lowerSensor1);
        TempChangeObserver.getInstance().notifyObserver(2, value);
	}

	public float getLowerSensor2() {
		return lowerBodySensor2;
	}

	public void setLowerSensor2(float lowerSensor2) {
		this.lowerBodySensor2 = lowerSensor2;
		String value = Float.toString(lowerBodySensor1);
        TempChangeObserver.getInstance().notifyObserver(3, value);
	}
	
    public void setCurrUpperTemp(float currUpperTemp) {
    	this.upperBodyTemp = currUpperTemp;
    	String value = Float.toString(currUpperTemp);
        TempChangeObserver.getInstance().notifyObserver(4, value);
        
        this.upperBodyTempList.add((double) currUpperTemp);
        TempChangeObserver.getInstance().notifyGraphs(this.upperBodyTempList, true);
    }

    public float getCurrLowerTemp() {
        return lowerBodyTemp;
    }

    public void setCurrLowerTemp(float currLowerTemp) {
        this.lowerBodyTemp = currLowerTemp;
        String value = Float.toString(currLowerTemp);
        TempChangeObserver.getInstance().notifyObserver(5, value);
        
        this.lowerBodyTempList.add((double) currLowerTemp);
        TempChangeObserver.getInstance().notifyGraphs(this.lowerBodyTempList, false);
    }

    public float getEnvirTemp() {
        return envTemp;
    }

    public void setEnvirTemp(float envirTemp) {
        envTemp = envirTemp;
        String value = Float.toString(envirTemp);
        TempChangeObserver.getInstance().notifyObserver(6, value);
    }

    public float getUserSetUpper() {
        return userSetUTemp;

    }

    public void setUserSetUpper(float userSetUpper) {
        this.userSetUTemp = userSetUpper;
    }

    public float getUserSetLower() {
        return userSetLTemp;
    }

    public void setUserSetLower(float userSetLower) {
        this.userSetLTemp = userSetLower;
    }

    public int getTimeLimit() {
        return timeOut;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeOut = timeLimit;
    }

    public float getLowerLimit() {
        return lowerTempLimit;
    }

    public void setLowerLimit(float lowerLimit) {
        this.lowerTempLimit = lowerLimit;
    }

    public float getUpperLimit() {
        return upperTempLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperTempLimit = upperLimit;
    }

    public float getCurrUpperTemp() {
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

	public float getUpperSensor1() {
		return upperBodySensor1;
	}
}