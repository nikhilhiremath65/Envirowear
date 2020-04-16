package components;

import model.Data;

public class Safety implements SafetyInterface {
	
	private int nullCount;
    private int limitCount;
	
	private double timeLimit;
	private double userSetInput;
    private double upperTempLimit;
    private double lowerTempLimit;
    private String controllerType;

    public Safety(String controllerType) {
        this.nullCount = 0;
        this.limitCount = 0;
        this.controllerType = controllerType;
    }

    public boolean checkUserSetTemp(){
    	
    		Data data = Data.getInstance();
            upperTempLimit = data.getUpperLimit();
            lowerTempLimit = data.getLowerLimit();
            userSetInput = data.getUserSetLower();
            return lowerTempLimit <= userSetInput && userSetInput <= upperTempLimit;
    }

    public double checkSensorNullTemp(double sensorTemp){
        	
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
                if(controllerType.equals("LOWER"))
                    data.setLowerStatus(false);
                else if(controllerType.equals("UPPER"))
                    data.setUpperStatus(false);
            }
            return sensorTemp;
    }

    public double checkAverageTempLimit(double sensorTemp){
        	
        	Data data = Data.getInstance(); 
            int timeLimit = data.getTimeLimit();
            double upperTempLimit = data.getUpperLimit();
            double lowerTempLimit = data.getLowerLimit();
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
