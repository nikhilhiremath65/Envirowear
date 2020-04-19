package components;

public interface SafetyInterface {

    boolean checkUserSetTemp() throws  Exception;
    double checkSensorNullTemp(double sensorTemp) throws Exception;
}
