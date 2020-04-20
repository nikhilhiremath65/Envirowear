package components;

import interfaces.IActuatorController;
import model.Data;

public class ActuatorController implements IActuatorController {




    public String actuatorAction (double userSetTemp, double sensorAverage, String actuatorType, double tempEnv) throws Exception{
        String action = "NONE";


            if (sensorAverage > (userSetTemp + 0.5)) {
                action = "COOL";
            } else if (sensorAverage < (userSetTemp - 0.5)) {
                action = "HEAT";

            } else if (sensorAverage <= (userSetTemp + 0.5) || sensorAverage >= (userSetTemp - 0.5)) {
                if (actuatorType.equals("UPPER")) {
                    Data.getInstance().setUpperStatus(false);
                } else {
                    Data.getInstance().setLowerStatus(false);
                }
            }
        return action;


        }
    }

