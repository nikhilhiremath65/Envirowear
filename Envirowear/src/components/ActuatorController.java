package components;

import model.Data;

public class ActuatorController {


//    HeatingEquation heatingEquation;
//    CoolingEquation coolingEquation;

    public ActuatorController() {
//       heatingEquation = new HeatingEquation();
//       coolingEquation = new CoolingEquation();
    }

    public String  actuatorAction(double userSetTemp, double sensorAverage, String actuatorType, double tempEnv){

        String action = "NONE";
//        double tempChange = heatingEquation.getTempInc(sensorAverage);
//        double newtonCoolingValue = coolingEquation.getCoolingTemp(tempEnv,sensorAverage);
        if (sensorAverage > (userSetTemp + 0.5)) {
            System.out.println("cool "+userSetTemp+" "+sensorAverage);
            action = "COOL";
        }
        else if (sensorAverage < (userSetTemp - 0.5)) {
            System.out.println("heat "+userSetTemp+" "+sensorAverage);
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
