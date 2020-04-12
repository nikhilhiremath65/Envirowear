package Envirowear;

public class Actuator {
    NewtonCooling newtonCooling;
    ThermoFirstLaw thermoFirstLaw;
    public Actuator(){
         newtonCooling = new NewtonCooling();
         thermoFirstLaw = new ThermoFirstLaw();
    }

    public float checkAction(float userSetTemp,float sensorAverage,String actuatorTpe,float tempEnv){
        float tempChange = thermoFirstLaw.TempChange(sensorAverage);
        float newtonCoolingValue = newtonCooling.getCoolingTemp(tempEnv,sensorAverage);
        if (userSetTemp<sensorAverage) {
            sensorAverage = sensorAverage - tempChange + newtonCoolingValue;
        }else if (userSetTemp>sensorAverage) {
            sensorAverage = sensorAverage + tempChange - newtonCoolingValue;
        } else {
            if (actuatorTpe.equals("UPPER")){
                Data.getInstance().setUpperStatus(false);
            }
            else{
                Data.getInstance().setLowerStatus(false);
            }

        }
        return sensorAverage;
    }


}
