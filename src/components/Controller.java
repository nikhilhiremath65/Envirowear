package components;

import interfaces.IController;
import model.Constants;

public abstract class  Controller implements IController {
	
    private double userSetInput;
    private double avgSen1=0;
    private double avgSen2=0;
    private int count = 0;

    Sensor sensor1;
    Sensor sensor2;
    OtherSafetyChecks otherSafetyChecks;
    Safety safety;


    public Controller(double userSetInput){

        this.userSetInput = userSetInput;
    }

    public double calcSensorAverage() throws Exception{
        double result = -1;
        count++;
        avgSen1+= sensor1.getTemp();
        avgSen2+= sensor2.getTemp();

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


}
