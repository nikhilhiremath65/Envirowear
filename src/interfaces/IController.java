package interfaces;

public interface IController {

    double calcSensorAverage() throws Exception;

    void setSensorTemp(double v, double v1);

    void sensorSafetyCheck() throws Exception;

    double otherSafetyChecks(double lowerSensorAverage) throws Exception;
}
