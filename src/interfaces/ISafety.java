package interfaces;

public interface ISafety {

    boolean checkUserSetTemp(double userSetInput) throws  Exception;
    double checkSensorNullTemp(double sensorTemp,double userSetInput) throws Exception;
}
