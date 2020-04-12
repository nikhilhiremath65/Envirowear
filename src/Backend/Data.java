package Backend;

public class Data {
    private static Data dataObj;
    private boolean upperStatus;
    private boolean lowerStatus;
    private float userSetUpper,userSetLower;
    private int timeLimit = 100;
    private float lowerLimit = 20;
    private float upperLimit = 40;
    private float currUpperTemp;
    private float currLowerTemp;
    private float EnvirTemp = 20;
    private float upperSensor1 = 0;
    private float upperSensor2 = 0;
    private float lowerSensor1 = 0;
    private float lowerSensor2 = 0;

    private Data() {
        this.upperStatus = false;
        this.lowerStatus = false;
    }

    public static Data getInstance() {
        if (dataObj == null) {
            dataObj = new Data();
        }
        return dataObj;
    }

    public boolean isUpperStatus() {
		return upperStatus;
	}

	public void setUpperStatus(boolean upperStatus) {
		this.upperStatus = upperStatus;
	}

	public boolean isLowerStatus() {
		return lowerStatus;
	}

	public void setLowerStatus(boolean lowerStatus) {
		this.lowerStatus = lowerStatus;
	}

	public float getUpperSensor1() {
		return upperSensor1;
	}

	public void setUpperSensor1(float upperSensor1) {
		this.upperSensor1 = upperSensor1;
		String value = Float.toString(upperSensor1);
        TempChangeObserver.getInstance().notifyObserver(0, value);
	}

	public float getUpperSensor2() {
		return upperSensor2;
	}

	public void setUpperSensor2(float upperSensor2) {
		this.upperSensor2 = upperSensor2;
		String value = Float.toString(upperSensor2);
        TempChangeObserver.getInstance().notifyObserver(1, value);
	}

	public float getLowerSensor1() {
		return lowerSensor1;
	}

	public void setLowerSensor1(float lowerSensor1) {
		this.lowerSensor1 = lowerSensor1;
		String value = Float.toString(lowerSensor1);
        TempChangeObserver.getInstance().notifyObserver(2, value);
	}

	public float getLowerSensor2() {
		return lowerSensor2;
	}

	public void setLowerSensor2(float lowerSensor2) {
		this.lowerSensor2 = lowerSensor2;
		String value = Float.toString(lowerSensor1);
        TempChangeObserver.getInstance().notifyObserver(3, value);
	}

    public float getUserSetUpper() {
        return userSetUpper;

    }

    public void setUserSetUpper(float userSetUpper) {
        this.userSetUpper = userSetUpper;
    }

    public float getUserSetLower() {
        return userSetLower;
    }

    public void setUserSetLower(float userSetLower) {
        this.userSetLower = userSetLower;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public float getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(float lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float getCurrUpperTemp() {
        return currUpperTemp;
    }

    public void setCurrUpperTemp(float currUpperTemp) {
    	this.currUpperTemp = currUpperTemp;
    	String value = Float.toString(currUpperTemp);
        TempChangeObserver.getInstance().notifyObserver(4, value);
    }

    public float getCurrLowerTemp() {
        return currLowerTemp;
    }

    public void setCurrLowerTemp(float currLowerTemp) {
        this.currLowerTemp = currLowerTemp;
        String value = Float.toString(currLowerTemp);
        TempChangeObserver.getInstance().notifyObserver(5, value);
    }

    public float getEnvirTemp() {
        return EnvirTemp;
    }

    public void setEnvirTemp(float envirTemp) {
        EnvirTemp = envirTemp;
        String value = Float.toString(envirTemp);
        TempChangeObserver.getInstance().notifyObserver(6, value);
    }
    
    
}