package safety;

public interface Safety {



    boolean checkUserSetTemp();

    float checkNullTemp(float sensorTemp);

    float checkSensorTempLimit(float sensorTemp);

}
