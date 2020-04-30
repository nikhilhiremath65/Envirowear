package utility;

public class HeatingEquation {

    public double getJacketTempChange(double userSetTemp, double sensorTemp) {
    	
        double tempInc;
        double jacketMass = 2.425;

        double specificHeatCapacityJacket = 0.3111;

        double energyNeeded = jacketMass*specificHeatCapacityJacket*(userSetTemp - sensorTemp);

        tempInc = ((energyNeeded/75) / (jacketMass * specificHeatCapacityJacket)) + sensorTemp;


        return  (sensorTemp - tempInc);
    }
}