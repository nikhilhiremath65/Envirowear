package model;

public class Simulator {
	
    CoolingEquation coolingFactor;
HeatingEquation heatingFactor;
    
    public Simulator(){
         coolingFactor = new CoolingEquation();
         heatingFactor = new HeatingEquation();
    }

    public double simulateJacketTempChange(double userSetTemp, double sensorAverage, double tempEnv,boolean power){
    	Data data = Data.getInstance();

    	double newTemp = sensorAverage;
        double tempDiff = heatingFactor.getJacketTempChange(userSetTemp,sensorAverage);
        double newtonCoolingTempDiff = coolingFactor.getTempChange(tempEnv,sensorAverage);
        System.out.println(" Temp Diff:"+tempDiff+" Newton:"+newtonCoolingTempDiff+" sensor avg"+sensorAverage);
        newTemp -= newtonCoolingTempDiff;
        if(power) {
            if (sensorAverage >= userSetTemp + 0.25) {
                newTemp = sensorAverage - tempDiff ;
            } else if (sensorAverage < userSetTemp - 0.25){
                newTemp = sensorAverage - tempDiff ;
            }
        }

        return newTemp;
    }

    public double simulateBodyTempChange(double bodyTemp,double jacketTemp){
        double newTemp = bodyTemp;
        double tempDiff = heatingFactor.getBodyTempChange(bodyTemp,jacketTemp);

        newTemp += tempDiff;
        return newTemp;
    }
}