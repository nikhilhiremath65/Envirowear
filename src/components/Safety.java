package components;

import interfaces.ISafetyInterface;
import model.Constants;
import model.Data;

public class Safety implements ISafetyInterface {
	
	private int nullCount;

	private double timeLimit= Constants.TIMEOUT;
	private double userSetInput;
    private double upperTempLimit= Constants.UPPER_TEMP_LIMIT;
    private double lowerTempLimit= Constants.LOWER_TEMP_LIMIT;
    private String controllerType;

    public Safety(String controllerType) {
        this.nullCount = 0;
        this.controllerType = controllerType;
    }

    public boolean checkUserSetTemp() throws Exception{
    	
    		Data data = Data.getInstance();
            userSetInput = data.getUserSetLower();

            return lowerTempLimit <= userSetInput && userSetInput <= upperTempLimit;
    }

    public double checkSensorNullTemp(double sensorTemp) throws Exception{

        	Data data = Data.getInstance();
            userSetInput = data.getUserSetUpper();
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
