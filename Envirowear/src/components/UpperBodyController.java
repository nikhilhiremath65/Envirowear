package components;

import model.Data;

public class UpperBodyController extends Controller{



    public UpperBodyController(double userSetInput){
        super(userSetInput);
        safety = new Safety("UPPER");
        sensor1 = new Sensor(24);
        sensor2 = new Sensor(24);
    }

    public void safetyCheckSensor(){

        sensor1.setTemp(safety.checkSensorNullTemp(sensor1.getTemp()));
        sensor2.setTemp(safety.checkSensorNullTemp(sensor2.getTemp()));
        this.updateData();
    }


    public double safetyChecks(double sensorAverage) {
        safety.checkUserSetTemp();
        return safety.checkAverageTempLimit(sensorAverage);
    }


    public void setSensorTemp(double v, double v1) {
        sensor1.setTemp(v);
        sensor2.setTemp(v1);
        this.updateData();
    }

    private void updateData(){
        Data.getInstance().setUpperSensor1(sensor1.getTemp());
        Data.getInstance().setUpperSensor2(sensor2.getTemp());
    }
}
