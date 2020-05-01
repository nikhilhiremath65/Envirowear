package components;


import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import interfaces.IController;
import data.Constants;
import data.Data;
import utility.Simulator;

public class Envirowear {

    ArrayList ls1,ls2,us1,us2;

    int upperFalseRound = 0;
    int lowerFalseRound = 0;
    double time=1;

    Simulator simulator;
    Sensor lowerBodySensor1,lowerBodySensor2,upperBodySensor1,upperBodySensor2;
    IController upperBodyController;
    IController lowerBodyController;


    public void start() {
        try {
            int round = 0;
            double upperSensorAverage = -1, lowerSensorAverage = -1, upperOut = 0, lowerOut = 0;
            Data data = Data.getInstance();

            ls1 = new ArrayList();
            ls2 = new ArrayList();
            us1 = new ArrayList();
            us2 = new ArrayList();

            upperBodyController = new Controller(data.getUserSetUpper(),"UPPER");
            lowerBodyController = new Controller(data.getUserSetLower(),"LOWER");
            lowerBodySensor1 = new Sensor(24);
            lowerBodySensor2 = new Sensor(24);
            upperBodySensor1 = new Sensor(24);
            upperBodySensor2 = new Sensor(24);
            simulator = new Simulator();
            

            while (true) {
            	
            int tBr = data.gettBR();
            TimeUnit.MILLISECONDS.sleep(tBr);
            if(data.isInitialize()) {
        
            	time = time + (double)1;
             
                data.setTimeElapsed(time);

                if(data.isUpperStatus()){
                    if(us1.isEmpty()) {
                        us1 = data.getUpperSensor1();
                        us2 = data.getUpperSensor2();
                    }

                    if (round < Constants.COUNTER) {
                        upperBodySensor1.setTemp((double)us1.get(round));
                        upperBodySensor2.setTemp((double)us2.get(round));
                    }
                    else {
                        upperBodySensor1.setTemp(upperOut);
                        upperBodySensor2.setTemp(upperOut);
                    }
                    Map<Integer,Double> sensor =  upperBodyController.sensorSafetyCheck(upperBodySensor1.getTemp(),upperBodySensor2.getTemp(),data.getEnvTemp(),data.getUserSetUpper());
                    upperBodySensor1.setTemp(sensor.get(1));
                    upperBodySensor2.setTemp(sensor.get(2));
                    data.setEnvTemp(sensor.get(3));
                    upperSensorAverage = upperBodyController.calcSensorAverage(upperBodySensor1.getTemp(),upperBodySensor2.getTemp());

                    if (upperSensorAverage != -1) {

                        upperSensorAverage = upperBodyController.otherSafetyChecks(upperSensorAverage,data.getUserSetUpper());
                        upperOut = simulator.simulateJacketTempChange(data.getUserSetUpper(), upperSensorAverage, data.getEnvTemp(),true);
                        data.setUpperActuatorFlag(upperBodyController.getAction(data.getUserSetUpper(),upperSensorAverage,data.getEnvTemp(),true));
                        data.setCurrUpperTemp(upperOut);
                    }

                }
                else{
                    if (upperFalseRound == Constants.COUNTER - 1) {
                        upperOut = simulator.simulateJacketTempChange(data.getUserSetUpper(), data.getCurrUpperTemp(), data.getEnvTemp(),false);
                        data.setUpperActuatorFlag(upperBodyController.getAction(data.getUserSetUpper(),upperOut,data.getEnvTemp(),false));
                        data.setCurrUpperTemp(upperOut);
                        upperFalseRound = 0;
                    }
                    upperFalseRound++;
                }

                if( data.isLowerStatus()){
                    if(ls1.isEmpty()){
                        ls1 = data.getLowerSensor1();
                        ls2 = data.getLowerSensor2();

                    }
                    if (round < Constants.COUNTER) {
                        lowerBodySensor1.setTemp((double)ls1.get(round));
                        lowerBodySensor2.setTemp((double)ls2.get(round));

                    }
                    else {
                        lowerBodySensor1.setTemp(lowerOut);
                        lowerBodySensor2.setTemp(lowerOut);
                    }

                    Map<Integer,Double> sensor =  lowerBodyController.sensorSafetyCheck(lowerBodySensor1.getTemp(),lowerBodySensor2.getTemp(),data.getEnvTemp(),data.getUserSetLower());
                    lowerBodySensor1.setTemp(sensor.get(1));
                    lowerBodySensor2.setTemp(sensor.get(2));
                    data.setEnvTemp(sensor.get(3));
                    lowerSensorAverage = lowerBodyController.calcSensorAverage(lowerBodySensor1.getTemp(),lowerBodySensor1.getTemp());

                    if (lowerSensorAverage != -1) {

                        lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage,data.getUserSetLower());
                        lowerOut = simulator.simulateJacketTempChange(data.getUserSetLower(), lowerSensorAverage, data.getEnvTemp(),true);
                        data.setLowerActuatorFlag(lowerBodyController.getAction(data.getUserSetLower(),lowerSensorAverage,data.getEnvTemp(),true));
                        data.setCurrLowerTemp(lowerOut);
                    }


                }
                else {
                    if (lowerFalseRound == Constants.COUNTER - 1) {
                        lowerOut = simulator.simulateJacketTempChange(data.getUserSetLower(), data.getCurrLowerTemp(), data.getEnvTemp(),false);
                        data.setLowerActuatorFlag(lowerBodyController.getAction(data.getUserSetLower(),lowerOut,data.getEnvTemp(),false));
                        data.setCurrLowerTemp(lowerOut);
                        lowerFalseRound = 0;
                    }
                    lowerFalseRound++;
                }


                    System.out.println("Upper Controller Status: " + data.isUpperStatus() +
                            " Lower Controller Status: " + data.isLowerStatus());
                    System.out.println("Upper Sensor Average: " + upperSensorAverage +
                            " Lower Sensor Average: " + lowerSensorAverage);
                    System.out.println("Upper output: " + upperOut + "  Lower output: " + lowerOut);
                    System.out.println(round);


                round++;

            }
            else{
                us1.clear();
                us2.clear();
                ls1.clear();
                ls2.clear();
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}