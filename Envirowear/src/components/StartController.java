package components;


import java.util.concurrent.TimeUnit;

import model.Data;
import model.Simulator;

public class StartController {
    private double[] ls1 = {16,16,16,16,16};
    private double[] ls2 = {16,16,16,16,16};
    private double[] us1 = {35,35,35,35,35};
    private double[] us2 = {35,35,35,35,35};
    
    int upperFalseRound = 0;
    int lowerFalseRound = 0;

    Simulator simulator;
    UpperBodyController upperBodyController;
    LowerBodyController lowerBodyController;
    ActuatorController actuatorController;

    public void setLs1(double[] ls1) {
        this.ls1 = ls1;
    }

    public void setLs2(double[] ls2) {
        this.ls2 = ls2;
    }

    public void setUs1(double[] us1) {
        this.us1 = us1;
    }

    public void setUs2(double[] us2) {
        this.us2 = us2;
    }

    public void start(){
    	
    	int round = 0;
    	Data data = Data.getInstance();
        double upperSensorAverage=-1,lowerSensorAverage=-1,upperOut=0,lowerOut=0;
        
        upperBodyController = new UpperBodyController(data.getUserSetUpper());
        lowerBodyController = new LowerBodyController(data.getUserSetLower());
        
        simulator = new Simulator();
        actuatorController = new ActuatorController();
        
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                //do nothing
            }
            
            if(data.isUpperStatus() || data.isLowerStatus()) {
                
            	if (data.isUpperStatus()) {
                    if (round < 5)
                        upperBodyController.setSensorTemp(us1[round], us2[round]);
                    else
                        upperBodyController.setSensorTemp(upperOut, upperOut);
                    
                    upperBodyController.safetyCheckSensor();
                    upperSensorAverage = upperBodyController.calcSensorAverage();
                }

                if (data.isLowerStatus()) {
                    if (round < 5)
                        lowerBodyController.setSensorTemp(ls1[round], ls2[round]);
                    else
                        lowerBodyController.setSensorTemp(lowerOut, lowerOut);

                    lowerBodyController.sensorSafetyCheck();
                    lowerSensorAverage = lowerBodyController.calcSensorAverage();
                }

                if (data.isUpperStatus() && upperSensorAverage != -1) {

                    upperSensorAverage = upperBodyController.safetyChecks(upperSensorAverage);
                    upperOut = simulator.simulateTempChange(data.getUserSetUpper(), upperSensorAverage,data.getEnvirTemp());
                    actuatorController.actuatorAction(data.getUserSetUpper(), upperSensorAverage,"UPPER",data.getEnvirTemp());
                    data.setCurrUpperTemp(upperOut);
                }

                if (data.isLowerStatus() && lowerSensorAverage != -1) {

                    lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                    lowerOut = simulator.simulateTempChange(data.getUserSetLower(), lowerSensorAverage,data.getEnvirTemp());
                    actuatorController.actuatorAction(data.getUserSetLower(), lowerSensorAverage,"LOWER",data.getEnvirTemp());
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
            
            if(!data.isUpperStatus()){
            	if(upperFalseRound == 4) {
            		upperOut = simulator.simulateTempChange(data.getUserSetUpper(), data.getCurrUpperTemp(),data.getEnvirTemp());
                    actuatorController.actuatorAction(data.getUserSetUpper(), upperSensorAverage,"UPPER",data.getEnvirTemp());
                    data.setCurrUpperTemp(upperOut);
                    upperFalseRound = 0;
            	}
            	upperFalseRound++;
            }
            if(!data.isLowerStatus()) {
            	if(lowerFalseRound == 4) {
            		lowerOut = simulator.simulateTempChange(data.getUserSetLower(), data.getCurrLowerTemp(),data.getEnvirTemp());
                    actuatorController.actuatorAction(data.getUserSetLower(), lowerSensorAverage,"LOWER",data.getEnvirTemp());
                    data.setCurrLowerTemp(lowerOut);
                    lowerFalseRound = 0;
            	}
            	lowerFalseRound++;
            }
        }
    }
}