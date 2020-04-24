package components;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import interfaces.IActuatorController;
import interfaces.IController;
import model.Constants;
import model.Data;
import model.Simulator;

public class StartController {

    ArrayList ls1,ls2,us1,us2;

    int upperFalseRound = 0;
    int lowerFalseRound = 0;
    double bodyTemp = 93.2;
    double time=1;

    Simulator simulator;
    IController upperBodyController;
    IController lowerBodyController;
    IActuatorController actuatorController;


    public void start() {
        try {
            int round = 0;
            double upperSensorAverage = -1, lowerSensorAverage = -1, upperOut = 0, lowerOut = 0;
            Data data = Data.getInstance();
            
            upperBodyController = new UpperBodyController(data.getUserSetUpper());
            lowerBodyController = new LowerBodyController(data.getUserSetLower());
            ls1 = new ArrayList();
            ls2 = new ArrayList();
            us1 = new ArrayList();
            us2 = new ArrayList();

            simulator = new Simulator();
            actuatorController = new ActuatorController();
            

            while (true) {
            	
            int tBr = data.gettBR();
            TimeUnit.MILLISECONDS.sleep(tBr);
            if(data.isInitialize()) {
        
            	time = time + (double)1;
             
                data.setTimeElapsed(time);
                data.setBodyTemp(bodyTemp);

                if(data.isUpperStatus()){
                    if(us1.isEmpty()) {
                        us1 = data.getUpperSensor1();
                        us2 = data.getUpperSensor2();
                    }

                    if (round < Constants.COUNTER)
                        upperBodyController.setSensorTemp((double) us1.get(round), (double) us2.get(round));
                    else
                        upperBodyController.setSensorTemp(upperOut, upperOut);

                    upperBodyController.sensorSafetyCheck();

                    upperSensorAverage = upperBodyController.calcSensorAverage();

                    if (upperSensorAverage != -1) {

                        upperSensorAverage = upperBodyController.otherSafetyChecks(upperSensorAverage);
                        upperOut = simulator.simulateJacketTempChange(data.getUserSetUpper(), upperSensorAverage, data.getEnvTemp(),true);
                        data.setUpperActuatorFlag(actuatorController.actuatorAction(data.getUserSetUpper(), upperSensorAverage, "UPPER", data.getEnvTemp(),true));
                        data.setCurrUpperTemp(upperOut);
                        bodyTemp = simulator.simulateBodyTempChange(bodyTemp, upperOut);
                    }

                }
                else{
                    if (upperFalseRound == Constants.COUNTER - 1) {
                        upperOut = simulator.simulateJacketTempChange(data.getUserSetUpper(), data.getCurrUpperTemp(), data.getEnvTemp(),false);
                        data.setUpperActuatorFlag(actuatorController.actuatorAction(data.getUserSetUpper(),upperOut,"UPPER",data.getEnvTemp(),false));
                        data.setCurrUpperTemp(upperOut);
                        bodyTemp = simulator.simulateBodyTempChange(bodyTemp, upperOut);
                        upperFalseRound = 0;
                    }
                    upperFalseRound++;
                }

                if( data.isLowerStatus()){
                    if(ls1.isEmpty()){
                        ls1 = data.getLowerSensor1();
                        ls2 = data.getLowerSensor2();

                    }
                    if (round < Constants.COUNTER)
                        lowerBodyController.setSensorTemp((double)ls1.get(round),(double) ls2.get(round));
                    else
                        lowerBodyController.setSensorTemp(lowerOut, lowerOut);

                    lowerBodyController.sensorSafetyCheck();
                    lowerSensorAverage = lowerBodyController.calcSensorAverage();

                    if (lowerSensorAverage != -1) {

                        lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                        lowerOut = simulator.simulateJacketTempChange(data.getUserSetLower(), lowerSensorAverage, data.getEnvTemp(),true);
                        data.setLowerActuatorFlag(actuatorController.actuatorAction(data.getUserSetLower(), lowerSensorAverage, "LOWER", data.getEnvTemp(),true));
                        data.setCurrLowerTemp(lowerOut);
                        bodyTemp = simulator.simulateBodyTempChange(bodyTemp, upperOut);
                    }


                }
                else {
                    if (lowerFalseRound == Constants.COUNTER - 1) {
                        lowerOut = simulator.simulateJacketTempChange(data.getUserSetLower(), data.getCurrLowerTemp(), data.getEnvTemp(),false);
                        data.setLowerActuatorFlag(actuatorController.actuatorAction(data.getUserSetLower(),lowerOut,"LOWER",data.getEnvTemp(),false));
                        data.setCurrLowerTemp(lowerOut);
                        bodyTemp = simulator.simulateBodyTempChange(bodyTemp, upperOut);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}