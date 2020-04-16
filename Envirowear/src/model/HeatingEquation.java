package model;

public class HeatingEquation {

    public double getTempChange(double userSetTemp, double sensorTemp) {
    	
        double tempInc;
        double energyNeeded;
        double jacketMass = 1.1;
        double power = 60;
        double specficHeatCapacityJacket = 1400;

        energyNeeded = jacketMass * specficHeatCapacityJacket * (userSetTemp - sensorTemp);
        tempInc = ((energyNeeded/60) / (jacketMass * specficHeatCapacityJacket)) + sensorTemp;

        return  sensorTemp - tempInc;
    }
}
