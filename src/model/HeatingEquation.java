package model;

import view.DegCtoDegF;

public class HeatingEquation {
    DegCtoDegF converter = new DegCtoDegF();

    public double getJacketTempChange(double userSetTemp, double sensorTemp) {
    	
        double tempInc;
        double jacketMass = 2.425;

        double time = 1;
        double specficHeatCapacityJacket = 0.3111;

//        userSetTemp = converter.degreeFtoC(userSetTemp);
//        sensorTemp = converter.degreeFtoC(sensorTemp);
        double energyNeeded = jacketMass*specficHeatCapacityJacket*(userSetTemp - sensorTemp);
        double k = 0.2;
        double A = 2.06;
        double d =  0.015;
        double rate = k *A * (userSetTemp - sensorTemp)/d;
//        if(portion.equals("UPPER")) {
//            rate *= 0.47;
//        }
//        else if (portion.equals("LOWER")){
//            rate *= 0.19;
//        }

        tempInc = ((energyNeeded/75) / (jacketMass * specficHeatCapacityJacket)) + sensorTemp;

//        sensorTemp = converter.degreeCtoF(sensorTemp);
//        tempInc = converter.degreeCtoF(tempInc);
        return  (sensorTemp - tempInc);
    }

    public double getBodyTempChange(double bodyTemp, double jacketTemp){
        double tempInc;

        double bodySkinMass = 8;

        double time = (double)(Data.getInstance().gettBR()*Constants.COUNTER)/1000;
        double specficHeatCapacityBody = 3300;
        double k = 0.5;
        double A = 2.06;
        double d =  0.2;
        bodyTemp = converter.degreeFtoC(bodyTemp);
        jacketTemp = converter.degreeFtoC(jacketTemp);
        double rate = k * (A) * (jacketTemp - bodyTemp) / d;

        tempInc = ((rate*time) /( bodySkinMass  * specficHeatCapacityBody)) + bodyTemp;


        bodyTemp = converter.degreeCtoF(bodyTemp);
        tempInc= converter.degreeCtoF(tempInc);
        return  ( tempInc - bodyTemp);

    }
}