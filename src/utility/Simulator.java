package utility;

import data.Constants;
import data.Data;

public class Simulator {
	
    CoolingEquation coolingFactor;
    HeatingEquation heatingFactor;
    
    public Simulator(){
         coolingFactor = new CoolingEquation();
         heatingFactor = new HeatingEquation();
    }

    public double simulateJacketTempChange(double userSetTemp, double sensorAverage, double tempEnv,boolean power){

    	double newTemp = sensorAverage;
        double tempDiff = heatingFactor.getJacketTempChange(userSetTemp,sensorAverage);
        double newtonCoolingTempDiff = coolingFactor.getTempChange(tempEnv,sensorAverage);
        System.out.println(" Temp Diff:"+tempDiff+" Newton:"+newtonCoolingTempDiff+" sensor avg"+sensorAverage);
        newTemp -= newtonCoolingTempDiff;
        if(power) {
            if (sensorAverage >= userSetTemp + Constants.OFF_RANGE) {
                newTemp = sensorAverage - tempDiff ;
            } else if (sensorAverage < userSetTemp - Constants.OFF_RANGE){
                newTemp = sensorAverage - tempDiff ;
            }
        }

        return newTemp;
    }
}