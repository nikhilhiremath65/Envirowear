package components;

import interfaces.IOtherSafetyChecks;
import data.Constants;
import data.Data;

public class OtherSafetyChecks implements IOtherSafetyChecks {


    private int limitCount;
    private double timeLimit= Constants.TIMEOUT;
    private double upperTempLimit= Constants.UPPER_TEMP_LIMIT;
    private double lowerTempLimit= Constants.LOWER_TEMP_LIMIT;
    private String controllerType;

    public OtherSafetyChecks(String controllerType) {
        this.limitCount = 0;
        this.controllerType = controllerType;
    }

    public double getAverage(double sensorTemp1, double sensorTemp2)throws Exception{

        return (sensorTemp1+sensorTemp2)/2;

    }
    public double checkAverageTempLimit(double sensorTemp)throws Exception{

        Data data = Data.getInstance();

        if(limitCount <= timeLimit){
            if(sensorTemp > upperTempLimit){
                sensorTemp = upperTempLimit;
                data.setStatusFlag(2);
                limitCount++;
            }
            else if(sensorTemp < lowerTempLimit){
                sensorTemp = lowerTempLimit;
                limitCount++;
                data.setStatusFlag(2);
            }
            else {
                limitCount=0;

            }
        }
        else{
        	data.setStatusFlag(3);
            if(controllerType.equals("LOWER"))
                data.setLowerStatus(false);
            else if(controllerType.equals("UPPER"))
                data.setUpperStatus(false);

        }
        return sensorTemp;
    }


}
