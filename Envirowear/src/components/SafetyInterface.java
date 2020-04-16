package components;

public interface SafetyInterface {

    boolean checkUserSetTemp();
    double checkSensorNullTemp(double sensorTemp);
    double checkAverageTempLimit(double sensorTemp);
}
