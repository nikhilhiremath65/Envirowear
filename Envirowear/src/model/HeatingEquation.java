package model;

public class HeatingEquation {

    public float  getTempInc(float oldTemp) {
    	
        double tempInc;
        double energyReleased = 406;
        double jacketMass = 1.1;
        double specficHeatCapacityJacket = 1400;
        
        tempInc = (energyReleased / (jacketMass * specficHeatCapacityJacket)) + oldTemp;
        return (float) tempInc;
    }
}
