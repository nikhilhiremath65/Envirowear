package Controller;

import Backend.Data;
import safety.LowerBodySafety;
import Backend.Sensor;

public class LowerBodyController extends Controller {

    private float avgSen1=0,avgSen2=0;
    private int count=0;
    LowerBodySafety safety;
    Sensor lowerBodySensor1 = new Sensor(24);
    Sensor lowerBodySensor2 = new Sensor(24);

    public LowerBodyController(float userSetInput){
        super(userSetInput);
        safety = new LowerBodySafety();
    }

    public float calcSensorAverage(){
        float result = -1;
        count++;
        avgSen1+= lowerBodySensor1.getTemp();
        avgSen2+= lowerBodySensor2.getTemp();

        if(count == 5){
            avgSen1/=count;
            avgSen2/=count;
            count = 0;
            result = super.sensorAverage(avgSen1,avgSen2);
            avgSen1 = 0;
            avgSen2 = 0;
        }
        return result;
    }


    public void sensorSafetyCheck(){
        lowerBodySensor1.setTemp(safety.checkNullTemp(lowerBodySensor1.getTemp()));
        lowerBodySensor2.setTemp(safety.checkNullTemp(lowerBodySensor2.getTemp()));
        updateData();
    }


    public float otherSafetyChecks(float sensorAverage) {
        safety.checkUserSetTemp();
        return safety.checkSensorTempLimit(sensorAverage);
    }

    public void setSensorTemp(float v, float v1) {
        lowerBodySensor1.setTemp(v);
        lowerBodySensor2.setTemp(v1);
        updateData();
    }

    private void updateData(){
        Data.getInstance().setLowerSensor1(lowerBodySensor1.getTemp());
        Data.getInstance().setLowerSensor2(lowerBodySensor2.getTemp());
    }
}
