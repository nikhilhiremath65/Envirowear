package components;

import model.Data;

public class UpperBodyController extends Controller {


    public UpperBodyController(double userSetInput) {
        super(userSetInput);
        safety = new Safety("UPPER");
        otherSafetyChecks = new OtherSafetyChecks("UPPER");
        sensor1 = new Sensor(24);
        sensor2 = new Sensor(24);
    }


    @Override
    public void sensorSafetyCheck() throws Exception {
        sensor1.setTemp(safety.checkSensorNullTemp(sensor1.getTemp()));
        sensor2.setTemp(safety.checkSensorNullTemp(sensor2.getTemp()));

    }


    @Override
    public double otherSafetyChecks(double sensorAverage) throws Exception {
        safety.checkUserSetTemp();
        return otherSafetyChecks.checkAverageTempLimit(sensorAverage);
    }


    public void setSensorTemp(double v, double v1) {
        sensor1.setTemp(v);
        sensor2.setTemp(v1);
    }


}
