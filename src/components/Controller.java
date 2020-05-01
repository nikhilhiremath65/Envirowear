package components;

import interfaces.IActuatorController;
import interfaces.IController;
import data.Constants;

import java.util.HashMap;
import java.util.Map;

public  class  Controller implements IController {

    private double userSetInput;
    private double avgSen1=0;
    private double avgSen2=0;
    private int count = 0;


    OtherSafetyChecks otherSafetyChecks;
    Safety safety;
    IActuatorController actuatorController;



    public Controller(double userSetInput,String type){

        this.userSetInput = userSetInput;
        safety = new Safety(type);
        otherSafetyChecks = new OtherSafetyChecks(type);
        actuatorController = new ActuatorController(type);
    }

    public double calcSensorAverage(double temp1, double temp2) throws Exception{
        double result = -1;
        count++;
        avgSen1+= temp1;
        avgSen2+= temp2;

        if(count == Constants.COUNTER){
            avgSen1/=count;
            avgSen2/=count;
            count = 0;
            result = otherSafetyChecks.getAverage(avgSen1,avgSen2);
            avgSen1 = 0;
            avgSen2 = 0;
        }
        return result;
    }

    @Override
    public Map<Integer,Double> sensorSafetyCheck(double sensorTemp1,double sensorTemp2,double envTemp,double userSetInput) throws Exception {
        Map<Integer,Double> map = new HashMap<>();
        map.put(1,safety.checkSensorNullTemp(sensorTemp1,userSetInput));
        map.put(2,safety.checkSensorNullTemp(sensorTemp2,userSetInput));
        map.put(3,safety.checkSensorNullTemp(envTemp,77));

        return map;
    }


    @Override
    public double otherSafetyChecks(double sensorAverage,double userSetInput) throws Exception {
        safety.checkUserSetTemp(userSetInput);
        return otherSafetyChecks.checkAverageTempLimit(sensorAverage);
    }

    public String getAction(double userSetInput,double sensorAvg,double envTemp,boolean power) throws Exception{
       return actuatorController.actuatorAction(userSetInput,sensorAvg,envTemp,power);
    }


}
