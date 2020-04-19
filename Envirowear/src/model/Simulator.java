package model;

public class Simulator {
	
    CoolingEquation coolingEquation;
    HeatingEquation heatingEquation;
    
    public Simulator(){
         coolingEquation = new CoolingEquation();
         heatingEquation = new HeatingEquation();
    }

    public double simulateTempChange(double userSetTemp, double sensorAverage, double tempEnv){
    	Data data = Data.getInstance();

    	double newTemp = sensorAverage;
        double tempDiff = heatingEquation.getTempChange(userSetTemp,sensorAverage);
        double newtonCoolingTempDiff = coolingEquation.getTempChange(tempEnv,sensorAverage);

        newTemp -= newtonCoolingTempDiff;
        if(data.isUpperStatus() || data.isLowerStatus()) {
            if (sensorAverage >= userSetTemp + 0.5) {
                newTemp = sensorAverage - tempDiff - newtonCoolingTempDiff;
            } else if (sensorAverage <= userSetTemp - 0.5) {
                newTemp = sensorAverage - tempDiff - newtonCoolingTempDiff;
            }
        }

        return newTemp;
    }
}
