package controller;

import model.Data;
import model.CoolingEquation;
import model.HeatingEquation;

public class Actuator {
	
    CoolingEquation coolingEquation;
    HeatingEquation heatingEquation;
    
    public Actuator(){
         coolingEquation = new CoolingEquation();
         heatingEquation = new HeatingEquation();
    }

    public float doAction(float userSetTemp,float sensorAverage,String actuatorTpe,float tempEnv){
    	
    	float updatedTemp = sensorAverage;
        float tempChange = heatingEquation.getTempInc(sensorAverage);
        float newtonCoolingValue = coolingEquation.getCoolingTemp(tempEnv,sensorAverage);
        
        if (userSetTemp<sensorAverage) {
        	updatedTemp = sensorAverage - tempChange + newtonCoolingValue;
        }
        else if (userSetTemp>sensorAverage) {
        	updatedTemp = sensorAverage + tempChange - newtonCoolingValue;
        }
        else {
            if (actuatorTpe.equals("UPPER")){
                Data.getInstance().setUpperStatus(false);
            }
            else{
                Data.getInstance().setLowerStatus(false);
            }
        }
        return updatedTemp;
    }
}
