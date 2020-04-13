package model;

public interface SafetyInterface {

    boolean checkUserSetTemp();
    float checkSensorNullTemp(float sensorTemp);
    float checkAverageTempLimit(float sensorTemp);
}
