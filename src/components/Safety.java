package components;

import interfaces.ISafety;
import data.Constants;
import data.Data;

public class Safety implements ISafety {
	
	private int nullCount;

	private double timeLimit= Constants.TIMEOUT;
    private double upperTempLimit= Constants.UPPER_TEMP_LIMIT;
    private double lowerTempLimit= Constants.LOWER_TEMP_LIMIT;
    private String controllerType;

    public Safety(String controllerType) {
        this.nullCount = 0;
        this.controllerType = controllerType;
    }

    public boolean checkUserSetTemp(double userSetInput) throws Exception{


            return lowerTempLimit <= userSetInput && userSetInput <= upperTempLimit;
    }

    public double checkSensorNullTemp(double sensorTemp,double userSetInput) throws Exception{

        	Data data = Data.getInstance();
            if(nullCount < timeLimit){
                if(sensorTemp == -999){
                    sensorTemp = userSetInput;
                    data.setStatusFlag(1);
                    nullCount++;
                }
                else{
                    nullCount = 0;
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
