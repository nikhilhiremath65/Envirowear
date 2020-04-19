package model;

public class HeatingEquation {

    public double getTempChange(double userSetTemp, double sensorTemp) {
    	
        double tempInc;
        double energyNeeded;
        double jacketMass = 1.1;
        double power = 150;
        double time = 1;
        double specficHeatCapacityJacket = 1400;


        energyNeeded = jacketMass * specficHeatCapacityJacket * (userSetTemp - sensorTemp);
        System.out.println(energyNeeded/power);
//            tempInc = ((energyNeeded/power) / (jacketMass * specficHeatCapacityJacket)) + sensorTemp;
        if(energyNeeded<0)
            power *= -1;

        if(Math.abs(energyNeeded) >= power*time)
            tempInc = ((power*time) / (jacketMass * specficHeatCapacityJacket)) + sensorTemp;
        else
            tempInc = ((energyNeeded/time) / (jacketMass * specficHeatCapacityJacket)) + sensorTemp;


        return  sensorTemp - tempInc;
    }
}
