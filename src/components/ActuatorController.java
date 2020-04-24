package components;

import interfaces.IActuatorController;
import model.Data;

public class ActuatorController implements IActuatorController {

double range = 0;


    public String actuatorAction (double userSetTemp, double sensorAverage, String actuatorType, double tempEnv,boolean power) throws Exception{
        String action = "NONE";

        if(power)
            range = 0.25;
        else
            range = 2;
            if (sensorAverage > (userSetTemp + range)){
                action = "COOL";
                System.out.println(action);
                if (actuatorType.equals("UPPER")) {
                    System.out.println("upper:"+action);
                    Data.getInstance().setUpperStatus(true);
                } else {
                    System.out.println("lower:"+action);
                    Data.getInstance().setLowerStatus(true);
                }


            } else if (sensorAverage < (userSetTemp - range)) {
                action = "HEAT";

                if (actuatorType.equals("UPPER")) {
                    System.out.println("upper:"+action);

                    Data.getInstance().setUpperStatus(true);
                } else {
                    System.out.println("lower:"+action);
                    Data.getInstance().setLowerStatus(true);
                }

            } else if (sensorAverage <= (userSetTemp + range) && sensorAverage >= (userSetTemp - range)) {
                if (actuatorType.equals("UPPER")) {
                    Data.getInstance().setUpperStatus(false);
                } else {
                    Data.getInstance().setLowerStatus(false);
                }
            }
        return action;


        }
    }