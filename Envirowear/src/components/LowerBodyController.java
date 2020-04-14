package components;

import model.Data;

public class LowerBodyController extends Controller {

    public LowerBodyController(float userSetInput){
    	
        super(userSetInput);
        safety = new Safety("LOWER");
        sensor2 = new Sensor(24);
        sensor1 = new Sensor(24);
    }

    public void sensorSafetyCheck(){
    	
        sensor1.setTemp(safety.checkSensorNullTemp(sensor1.getTemp()));
        sensor2.setTemp(safety.checkSensorNullTemp(sensor2.getTemp()));
        updateData();
    }


    public float otherSafetyChecks(float sensorAverage) {
    	
        safety.checkUserSetTemp();
        return safety.checkAverageTempLimit(sensorAverage);
    }

    public void setSensorTemp(float v, float v1) {
    	
        sensor1.setTemp(v);
        sensor2.setTemp(v1);
        updateData();
    }

    private void updateData(){
    	
    	Data data =  Data.getInstance();
        data.setLowerSensor1(sensor1.getTemp());
        data.setLowerSensor2(sensor2.getTemp());
    }
}
