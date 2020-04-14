package components;

import model.Data;

public class ActuatorController {


//    HeatingEquation heatingEquation;
//    CoolingEquation coolingEquation;

    public ActuatorController() {
//       heatingEquation = new HeatingEquation();
//       coolingEquation = new CoolingEquation();
    }

    public String  actuatorAction(float userSetTemp, float sensorAverage, String actuatorType, float tempEnv){

        String action = "NONE";
//        float tempChange = heatingEquation.getTempInc(sensorAverage);
//        float newtonCoolingValue = coolingEquation.getCoolingTemp(tempEnv,sensorAverage);
        if (sensorAverage > userSetTemp + 0.5) {
            System.out.println("cool");
            action = "COOL";
        }
        else if (sensorAverage < userSetTemp - 0.5) {
            System.out.println("heat");
            action = "HEAT";

        }
        else if( sensorAverage <= (userSetTemp+0.5) || sensorAverage >= (userSetTemp-0.5)){
            if (actuatorType.equals("UPPER")){
                Data.getInstance().setUpperStatus(false);
            }
            else{
                Data.getInstance().setLowerStatus(false);
            }
        }
        return action;
    }
}
