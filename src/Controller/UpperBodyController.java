package Controller;

import Backend.Data;
import Backend.Sensor;
import safety.UpperBodySafety;

public class UpperBodyController extends Controller{

    private float avgSen1=0,avgSen2=0;
    private int count = 0;

    UpperBodySafety safety;
    Sensor upperBodySensor1 = new Sensor(24);
    Sensor upperBodySensor2 = new Sensor(24);

    public UpperBodyController(float userSetInput){
        super(userSetInput);
        safety = new UpperBodySafety();
    }
    public float calcSensorAverage(){
        float result = -1;
        count++;
        avgSen1+= upperBodySensor1.getTemp();
        avgSen2+= upperBodySensor2.getTemp();

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

    public float safetyChecks(float sensorAverage) {
        safety.checkUserSetTemp();
        return safety.checkSensorTempLimit(sensorAverage);
    }

    public void safetyCheckSensor(){

        upperBodySensor1.setTemp(safety.checkNullTemp(upperBodySensor1.getTemp()));
        upperBodySensor2.setTemp(safety.checkNullTemp(upperBodySensor2.getTemp()));
        this.updateData();
    }


    public void setSensorTemp(float v, float v1) {
        upperBodySensor1.setTemp(v);
        upperBodySensor2.setTemp(v1);
        this.updateData();
    }

    private void updateData(){
        Data.getInstance().setUpperSensor1(upperBodySensor1.getTemp());
        Data.getInstance().setUpperSensor2(upperBodySensor2.getTemp());
    }
}
