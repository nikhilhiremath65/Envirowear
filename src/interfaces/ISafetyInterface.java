package interfaces;

public interface ISafetyInterface {

    boolean checkUserSetTemp() throws  Exception;
    double checkSensorNullTemp(double sensorTemp) throws Exception;
}
