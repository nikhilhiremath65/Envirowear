package components;

import interfaces.IActuatorController;
import data.Constants;
import data.Data;

public class ActuatorController implements IActuatorController {

double range = 0;
String type;

    public ActuatorController(String type) {
        this.type = type;
    }

    public String actuatorAction (double userSetTemp, double sensorAverage, double tempEnv, boolean power) throws Exception{
        String action = "NONE";

        if(power)
            range = Constants.OFF_RANGE;
        else
            range = Constants.ON_RANGE;
            if (sensorAverage > (userSetTemp + range)){
                action = "COOL";
                if (type.equals("UPPER")) {
                    Data.getInstance().setUpperStatus(true);
                } else {
                    System.out.println("lower:"+action);
                    Data.getInstance().setLowerStatus(true);
                }


            } else if (sensorAverage < (userSetTemp - range)) {
                action = "HEAT";

                if (type.equals("UPPER")) {
                    Data.getInstance().setUpperStatus(true);
                } else {
                    Data.getInstance().setLowerStatus(true);
                }

            } else if (sensorAverage <= (userSetTemp + range) && sensorAverage >= (userSetTemp - range)) {
                if (type.equals("UPPER")) {
                    Data.getInstance().setUpperStatus(false);
                } else {
                    Data.getInstance().setLowerStatus(false);
                }
            }
        return action;


        }
    }