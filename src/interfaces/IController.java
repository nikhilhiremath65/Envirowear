package interfaces;

import java.util.Map;

public interface IController {

    double calcSensorAverage(double temp1, double temp2) throws Exception;

    Map<Integer,Double> sensorSafetyCheck(double temp1,double temp2,double envTemp,double userSetInput) throws Exception;

    double otherSafetyChecks(double sensorAverage,double userSetInput) throws Exception;

    String getAction(double userSetInput,double sensorAvg,double envTemp,boolean power) throws Exception;
}
