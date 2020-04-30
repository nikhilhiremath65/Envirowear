package interfaces;

public interface IActuatorController {

    public String actuatorAction (double userSetTemp, double sensorAverage, double tempEnv,boolean power) throws Exception;

}
