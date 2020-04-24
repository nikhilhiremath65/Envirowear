package interfaces;

public interface IActuatorController {

    public String actuatorAction (double userSetTemp, double sensorAverage, String actuatorType, double tempEnv,boolean power) throws Exception;

}
