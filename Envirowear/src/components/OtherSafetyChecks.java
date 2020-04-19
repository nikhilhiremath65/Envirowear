package components;

import model.Constants;
import model.Data;

public class OtherSafetyChecks {


    private int limitCount;
    private double timeLimit= Constants.TIMEOUT;
    private double upperTempLimit= Constants.UPPER_TEMP_LIMIT;
    private double lowerTempLimit= Constants.LOWER_TEMP_LIMIT;
    private String controllerType;

    public OtherSafetyChecks(String controllerType) {
        this.limitCount = 0;
        this.controllerType = controllerType;
    }

    public double getAverage(double senosrTemp1, double sensorTemp2)throws Exception{

        return (senosrTemp1+sensorTemp2)/2;

    }
    public double checkAverageTempLimit(double sensorTemp)throws Exception{

        Data data = Data.getInstance();

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
            if(controllerType.equals("LOWER"))
                data.setLowerStatus(false);
            else if(controllerType.equals("UPPER"))
                data.setUpperStatus(false);

        }
        return sensorTemp;
    }


}
