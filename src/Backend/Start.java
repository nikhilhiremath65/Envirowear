package Backend;


import java.util.concurrent.TimeUnit;

import Controller.LowerBodyController;
import Controller.UpperBodyController;

public class Start {
    private float[] ls1 = {19,20,21,22,23};
    private float[] ls2 = {20,21,22,22,23};
    private float[] us1 = {20,21,22,23,24};
    private float[] us2 = {25,21,22,23,24};

    public float[] getLs1() {
        return ls1;
    }

    public void setLs1(float[] ls1) {
        this.ls1 = ls1;
    }

    public float[] getLs2() {
        return ls2;
    }

    public void setLs2(float[] ls2) {
        this.ls2 = ls2;
    }

    public float[] getUs1() {
        return us1;
    }

    public void setUs1(float[] us1) {
        this.us1 = us1;
    }

    public float[] getUs2() {
        return us2;
    }

    public void setUs2(float[] us2) {
        this.us2 = us2;
    }

    public void runApp(){
        float upperSensorAverage=-1,lowerSensorAverage=-1,upperOut=0,lowerOut=0;

        UpperBodyController upperBodyController = new UpperBodyController(Data.getInstance().getUserSetUpper());


        LowerBodyController lowerBodyController = new LowerBodyController(Data.getInstance().getUserSetUpper());

        int i = 0;
        Actuator actuator = new Actuator();
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Data.getInstance().isUpperStatus() || Data.getInstance().isLowerStatus()) {


                if (Data.getInstance().isUpperStatus()) {
                    if (i < 5)
                        upperBodyController.setSensorTemp(us1[i], us2[i]);
                    else
                        upperBodyController.setSensorTemp(upperOut, upperOut);
                    upperBodyController.safetyCheckSensor();
                    upperSensorAverage = upperBodyController.calcSensorAverage();
                }

                if (Data.getInstance().isLowerStatus()) {
                    if (i < 5)
                        lowerBodyController.setSensorTemp(ls1[i], ls2[i]);

                    else {

                        lowerBodyController.setSensorTemp(lowerOut, lowerOut);
                    }

                    lowerBodyController.sensorSafetyCheck();
                    lowerSensorAverage = lowerBodyController.calcSensorAverage();
                }

                if (Data.getInstance().isUpperStatus() && upperSensorAverage != -1) {

                    upperSensorAverage = upperBodyController.safetyChecks(upperSensorAverage);
                    upperOut = actuator.checkAction(Data.getInstance().getUserSetUpper(), upperSensorAverage, "UPPER",Data.getInstance().getEnvirTemp());
                    Data.getInstance().setCurrUpperTemp(upperOut);
                }

                if (Data.getInstance().isLowerStatus() && lowerSensorAverage != -1) {

                    lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                    lowerOut = actuator.checkAction(Data.getInstance().getUserSetLower(), lowerSensorAverage, "LOWER",Data.getInstance().getEnvirTemp());
                    Data.getInstance().setCurrLowerTemp(lowerOut);
                }
                if (upperSensorAverage != -1 && (Data.getInstance().isLowerStatus() || Data.getInstance().isUpperStatus())) {
                    System.out.println("Upper Controller Status: " + Data.getInstance().isUpperStatus() + " Lower Controller Status: " + Data.getInstance().isLowerStatus());
                    System.out.println("Upper Sensor Average: " + upperSensorAverage + " Lower Sensor Average: " + lowerSensorAverage);
                    System.out.println("Upper output: " + upperOut + "  Lower output: " + lowerOut);
                    System.out.println(i);
                }

                if (Data.getInstance().getUserSetUpper() == upperOut) {
                    Data.getInstance().setCurrUpperTemp(upperOut);
                    Data.getInstance().setUpperStatus(false);
                }
                if (Data.getInstance().getUserSetLower() == lowerOut) {
                    Data.getInstance().setCurrLowerTemp(lowerOut);
                    Data.getInstance().setLowerStatus(false);
                }


                i++;
            }


        }
    }
}