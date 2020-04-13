package model;

import model.Data;

public class UpperBodySafety implements SafetyInterface {
	
	private int nullCount;
    private int limitCount;
    
	private float timeLimit;
    private float userSetInput;
    private float upperTempLimit;
    private float lowerTempLimit;

    public UpperBodySafety() {
        this.nullCount = 0;
        this.limitCount = 0;
    }

    public boolean checkUserSetTemp(){
    	
    	Data data = Data.getInstance(); 
        upperTempLimit = data.getUpperLimit();
        lowerTempLimit = data.getLowerLimit();
        userSetInput = data.getUserSetUpper();
        return lowerTempLimit <= userSetInput && userSetInput <= upperTempLimit;
    }

    public float checkSensorNullTemp(float sensorTemp){
    	
    	Data data = Data.getInstance(); 
        timeLimit = data.getTimeLimit();
        upperTempLimit = data.getUpperLimit();
        userSetInput = data.getUserSetUpper();
        if(nullCount < timeLimit){
            if(sensorTemp == 0){
                sensorTemp = userSetInput;
                nullCount++;
            }
            else{
                nullCount = 0;
            }

        }

        else{
            data.setUpperStatus(false);
        }
        return sensorTemp;
    }

    public float checkAverageTempLimit(float sensorTemp){
    	
    	Data data = Data.getInstance(); 
        int timeLimit = data.getTimeLimit();
        float upperTempLimit = data.getUpperLimit();
        float lowerTempLimit = data.getLowerLimit();
        if(limitCount <= timeLimit){
            if(sensorTemp > upperTempLimit){
                sensorTemp = upperTempLimit;
                limitCount++;
            }
            else if(sensorTemp < lowerTempLimit){
                sensorTemp = lowerTempLimit;
                limitCount++;
            }
            else {
                limitCount=0;

            }
        }
        else{
            data.setUpperStatus(false); // stop the whole device

        }
        return sensorTemp;
    }
}
