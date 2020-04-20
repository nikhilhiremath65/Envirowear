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


    Simulator simulator;
    IController upperBodyController;
    IController lowerBodyController;
    IActuatorController actuatorController;


    public void start() {
        try {
            int round = 0;
            Data data = Data.getInstance();
            double upperSensorAverage = -1, lowerSensorAverage = -1, upperOut = 0, lowerOut = 0;

            upperBodyController = new UpperBodyController(data.getUserSetUpper());
            lowerBodyController = new LowerBodyController(data.getUserSetLower());
            ls1 = new ArrayList();
            ls2 = new ArrayList();
            us1 = new ArrayList();
            us2 = new ArrayList();

            simulator = new Simulator();
            actuatorController = new ActuatorController();

            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    //do nothing
                }

                if (data.isUpperStatus() || data.isLowerStatus()) {
                    if(ls1.isEmpty()){
                        ls1 = data.getLowerSensor1();
                        ls2 = data.getLowerSensor2();
                        us1 = data.getUpperSensor1();
                        us2 = data.getUpperSensor2();
                    }

                    if (data.isUpperStatus()) {
                        if (round < Constants.COUNTER)
                            upperBodyController.setSensorTemp((double) us1.get(round), (double) us2.get(round));
                        else
                            upperBodyController.setSensorTemp(upperOut, upperOut);

                        upperBodyController.sensorSafetyCheck();
                        upperSensorAverage = upperBodyController.calcSensorAverage();
                    }

                    if (data.isLowerStatus()) {
                        if (round < Constants.COUNTER)
                            lowerBodyController.setSensorTemp((double)ls1.get(round),(double) ls2.get(round));
                        else
                            lowerBodyController.setSensorTemp(lowerOut, lowerOut);

                        lowerBodyController.sensorSafetyCheck();
                        lowerSensorAverage = lowerBodyController.calcSensorAverage();
                    }

                    if (data.isUpperStatus() && upperSensorAverage != -1) {

                        upperSensorAverage = upperBodyController.otherSafetyChecks(upperSensorAverage);
                        upperOut = simulator.simulateTempChange(data.getUserSetUpper(), upperSensorAverage, data.getEnvTemp());
                        actuatorController.actuatorAction(data.getUserSetUpper(), upperSensorAverage, "UPPER", data.getEnvTemp());
                        data.setCurrUpperTemp(upperOut);
                    }

                    if (data.isLowerStatus() && lowerSensorAverage != -1) {

                        lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                        lowerOut = simulator.simulateTempChange(data.getUserSetLower(), lowerSensorAverage, data.getEnvTemp());
                        actuatorController.actuatorAction(data.getUserSetLower(), lowerSensorAverage, "LOWER", data.getEnvTemp());
                        data.setCurrLowerTemp(lowerOut);
                    }

                    if (upperSensorAverage != -1 && (data.isLowerStatus() || data.isUpperStatus())) {
                        System.out.println("Upper Controller Status: " + data.isUpperStatus() +
                                " Lower Controller Status: " + data.isLowerStatus());
                        System.out.println("Upper Sensor Average: " + upperSensorAverage +
                                " Lower Sensor Average: " + lowerSensorAverage);
                        System.out.println("Upper output: " + upperOut + "  Lower output: " + lowerOut);
                        System.out.println(round);
                    }


                    round++;
                }

                if (!data.isUpperStatus()) {
                    if (upperFalseRound == Constants.COUNTER - 1) {
                        upperOut = simulator.simulateTempChange(data.getUserSetUpper(), data.getCurrUpperTemp(), data.getEnvTemp());
                        actuatorController.actuatorAction(data.getUserSetUpper(), upperSensorAverage, "UPPER", data.getEnvTemp());
                        data.setCurrUpperTemp(upperOut);
                        upperFalseRound = 0;
                    }
                    upperFalseRound++;
                }
                if (!data.isLowerStatus()) {
                    if (lowerFalseRound == Constants.COUNTER - 1) {
                        lowerOut = simulator.simulateTempChange(data.getUserSetLower(), data.getCurrLowerTemp(), data.getEnvTemp());
                        actuatorController.actuatorAction(data.getUserSetLower(), lowerSensorAverage, "LOWER", data.getEnvTemp());
                        data.setCurrLowerTemp(lowerOut);
                        lowerFalseRound = 0;
                    }
                    lowerFalseRound++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}