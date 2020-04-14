package model;

import model.Data;
import model.CoolingEquation;
import model.HeatingEquation;

public class Simulation {
	
    CoolingEquation coolingEquation;
    HeatingEquation heatingEquation;
    
    public Simulation(){
         coolingEquation = new CoolingEquation();
         heatingEquation = new HeatingEquation();
    }

    public float simulateTempChange(float userSetTemp, float sensorAverage, float tempEnv){
    	
    	float updatedTemp = sensorAverage;
        float tempChange = heatingEquation.getTempInc(sensorAverage);
        float newtonCoolingValue = coolingEquation.getCoolingTemp(tempEnv,sensorAverage);
        System.out.println("+++++++++++");
        updatedTemp = newtonCoolingValue;
        if (sensorAverage >= userSetTemp + 0.5) {
        	updatedTemp = sensorAverage - tempChange + newtonCoolingValue;
        	System.out.println("---------------");
        }
        else if (sensorAverage < userSetTemp - 0.5) {
        	updatedTemp = sensorAverage + tempChange - newtonCoolingValue;
        	System.out.println("-***************");
        }


        return updatedTemp;
    }
}
