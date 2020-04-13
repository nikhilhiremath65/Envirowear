package controller;


import java.util.concurrent.TimeUnit;

import controller.LowerBodyController;
import controller.UpperBodyController;
import model.Data;

public class StartController {
    private float[] ls1 = {20,20,20,20,20};
    private float[] ls2 = {20,20,20,20,20};
    private float[] us1 = {20,20,20,20,20};
    private float[] us2 = {20,20,20,20,20};

    public void setLs1(float[] ls1) {
        this.ls1 = ls1;
    }

    public void setLs2(float[] ls2) {
        this.ls2 = ls2;
    }

    public void setUs1(float[] us1) {
        this.us1 = us1;
    }

    public void setUs2(float[] us2) {
        this.us2 = us2;
    }

    public void start(){
    	
    	int round = 0;
    	Data data = Data.getInstance();
        float upperSensorAverage=-1,lowerSensorAverage=-1,upperOut=0,lowerOut=0;
        
        UpperBodyController upperBodyController = new UpperBodyController(data.getUserSetUpper());
        LowerBodyController lowerBodyController = new LowerBodyController(data.getUserSetUpper());
        
        Actuator actuator = new Actuator();
        
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
                    upperOut = actuator.doAction(data.getUserSetUpper(), upperSensorAverage, 
                    		"UPPER",data.getEnvirTemp());
                    data.setCurrUpperTemp(upperOut);
                }

                if (data.isLowerStatus() && lowerSensorAverage != -1) {

                    lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                    lowerOut = actuator.doAction(data.getUserSetLower(), lowerSensorAverage, 
                    		"LOWER",data.getEnvirTemp());
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

                if (data.getUserSetUpper() == upperOut) {
                    //data.setCurrUpperTemp(upperOut);
                    data.setUpperStatus(false);
                }
                
                if (data.getUserSetLower() == lowerOut) {
                    //data.setCurrLowerTemp(lowerOut);
                    data.setLowerStatus(false);
                }
                round++;
            }
        }
    }
}